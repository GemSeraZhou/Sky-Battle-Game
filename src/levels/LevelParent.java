package levels;

import java.util.*;
import java.util.stream.Collectors;
import actors.*;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;
import view.LevelView;

/**
 * Abstract class for all levels in Sky Battle
 * @author Stephen
 */
public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 17;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	
	private int currentNumberOfEnemies;
	private LevelView levelView;

	/**
	 * Creates an instance of LevelParent
	 * @param backgroundImageName: name of background image for level
	 * @param screenHeight: height of the screen
	 * @param screenWidth: width of the screen
	 * @param playerInitialHealth: the user's initial number of lives
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new ImageView(new Image(backgroundImageName));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Initializes the level's friendly units
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if game is over, and if so, ends game
	 */
	protected abstract void checkIfGameOver();
	
	/**
	 * Spawns enemies for the level
	 */
	protected abstract void spawnEnemyUnits();
	
	/**
	 * Instantiates the level's view
	 * @return the level's view
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the level's scene
	 * @return: the level's scene
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}
	
	/**
	 * Starts the level
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}
	
	/**
	 * Switches to the next level of Sky Battle
	 * @param levelName: name of the next level
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}
	
	/**
	 * Updates the level
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}
	
	/**
	 * Initializes the level's Timeline
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the level's background
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}
	
	/**
	 * Fires a user projectile
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates projectiles for enemy units
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Displays enemy-fired projectile
	 * @param projectile: projectile to be displayed
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates each actor in the level
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the level
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the screen and from the list of actors containing them
	 * @param actors: list of all actors at the beginning of a keyframe
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between friendly units and enemy units
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}
	

	/**
	 * Handles collisions between user projectiles and enemy units
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}
	
	/**
	 * Handles collisions between enemy projectiles and friendly units
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}
	
	/**
	 * Handles collisions between two lists of actors
	 * @param actors1: first list of actors
	 * @param actors2: second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * For each enemy unit that passes the user, deals damage to user and removes the enemy 
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}
	
	/**
	 * Updates the level's view
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}
	
	/**
	 * Updates the user's kill count
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}
	
	/**
	 * 
	 * @param enemy: enemy that is being checked to see whether it has penetrated the user's defenses
	 * @return: true if enemy has penetrated user's defenses; false otherwise
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}
	
	/**
	 * Stops the timeline and displays image indicating user has won the game
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}
	
	/**
	 * Stops the timeline and displays image indicating user has lost the game
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}
	
	/**
	 * @return the user's plane
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * @return Group containing all Nodes displayed in level
	 */
	protected Group getRoot() {
		return root;
	}
	
	/**
	 * @return the current number of enemies on the screen
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}
	
	/**
	 * Adds an enemy unit to the level
	 * @param enemy: enemy unit to be added to level
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}
	
	/**
	 * @return: the maximum y position for an enemy unit
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}
	
	/**
	 * @return: the width of the screen
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}
	
	/**
	 * @return true if the user has been destroyed; false otherwise
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
	
	/**
	 * Sets the value of currentNumberOfEnemies to the current size of the list
	 * of enemy units
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
