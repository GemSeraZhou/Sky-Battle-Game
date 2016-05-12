package levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import actors.ActiveActorDestructible;
import actors.FighterPlane;
import actors.UserPlane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.EmptyNode;
import view.GameOverImage;

public abstract class LevelParent extends Observable {

	private static final int MILLISECOND_DELAY = 17;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private Group root;
	private Timeline timeline;
	private UserPlane user;
	private Scene scene;
	private ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	public LevelParent(Image backgroundImage, double screenHeight, double screenWidth) {
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new ImageView(backgroundImage);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - 150;
		initializeUser();
		initializeTimeline();
	}

	public Scene initializeScene() {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		initializeBackground();
		initializeFriendlyUnits();
		return scene;
	}

	protected abstract void initializeFriendlyUnits();
	
	protected abstract void goToNextLevel();

	protected abstract void checkIfGameOver();
	
	protected abstract void spawnEnemyUnits();

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}
	
	private void updateScene() {
		spawnEnemyUnits();
		updateActorPositions();
		generateEnemyFire();
		handleEnemyPenetration();
		handleProjectileCollisions(enemyUnits, userProjectiles);
		handleProjectileCollisions(friendlyUnits, enemyProjectiles);
		handlePlaneCollisions();
		removeAllDestroyedActors();
		checkIfGameOver();
	}
	
	private void initializeUser() {
		user = new UserPlane();
		friendlyUnits.add(user);
	}
	
	protected void initializeTimeline() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	protected void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP)
					user.moveUp();
				if (kc == KeyCode.DOWN)
					user.moveDown();
				if (kc == KeyCode.SPACE)
					fireProjectile();
				if (kc == KeyCode.L)
					user.destroy();
				if (kc == KeyCode.ESCAPE)
					Platform.exit(); 
				if (kc == KeyCode.N)
					goToNextLevel();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
					user.stop();
				}

			}
		});
		root.getChildren().add(background);
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	protected void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	protected void updateActorPositions() {
		friendlyUnits.forEach(plane -> plane.updatePosition());
		enemyUnits.forEach(enemy -> enemy.updatePosition());
		userProjectiles.forEach(projectile -> projectile.updatePosition());
		enemyProjectiles.forEach(projectile -> projectile.updatePosition());
	}

	protected void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	protected void handleProjectileCollisions(List<ActiveActorDestructible> planes,
			List<ActiveActorDestructible> projectiles) {
		for (ActiveActorDestructible projectile : projectiles) {
			for (ActiveActorDestructible plane : planes) {
				if (projectile.getBoundsInParent().intersects(plane.getBoundsInParent())) {
					projectile.destroy();
					((FighterPlane) plane).takeDamage();
				}
			}
		}
	}

	protected void handlePlaneCollisions() {
		for (ActiveActorDestructible friendlyPlane : friendlyUnits) {
			for (ActiveActorDestructible enemyPlane : enemyUnits) {
				if (friendlyPlane.getBoundsInParent().intersects(enemyPlane.getBoundsInParent())) {
					((FighterPlane) friendlyPlane).takeDamage();
					((FighterPlane) enemyPlane).takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}
	
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}
	
	protected void endGame() {
		timeline.stop();
		GameOverImage gameOverImage = new GameOverImage();
		root.getChildren().add(gameOverImage);
	}
	
	protected ActiveActorDestructible getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}
	
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}
	
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}
	
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}
	
	protected double getScreenWidth() {
		return screenWidth;
	}

}
