package game_smk44;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;


public class Game extends Parent {
	
	private static final int MILLISECOND_DELAY = 17;
	private static final int KILLS_FOR_NEXT_LEVEL = 100;
	private static final int NUM_ENEMIES = 3;
	private static final int MAX_DEATHS = 5;
	private static final int MIN_LIVES = 1;
	private static final int HEART_WIDTH = 50;
	private static final int HEART_HEIGHT = 50;
	private static final int HEART_DISPLAY_X_POS = Main.getScreenWidth() - 265;
	private static final int HEART_DISPLAY_Y_POS = 5;
	private static final int ENEMY_X_POS_INITIAL = Main.getScreenWidth() - 200;
	private static final int LOSS_SCREEN_X_POS = -160;
	private static final int LOSS_SCREEN_Y_POS = -375;
	private static final int USER_UPPER_BOUND = -40;
	private static final int USER_LOWER_BOUND = Main.getScreenHeight()- 150;
	private static final int ENEMY_SPAWN_RANGE = 500;
	private static final int ENEMY_FIRE_PROB = 250;
	private static final int LEVEL_TWO_CODE = Main.getMyController().getLevelTwoCode();
	
	protected Group group;
	protected ImageView background;
	protected ImageView lossScreen;
	protected Timeline timeline;
	protected Random r;
	
	protected FighterPlane user;
	protected ArrayList<EnemyPlane> enemies;
	protected ArrayList<Projectile> bullets; 
	protected ArrayList<EnemyProjectile> enemyBullets; 
	protected ArrayList<ImageView> hearts;
	protected int kills;
	protected int lives;
	protected int deaths;
	
	
	public Game() {
		group = new Group();
		getChildren().add(group);
		initBackground();
		initLossImage();
		initScreenElements();
		initHearts();
		initTimeline();
	}
	
	public void start() {
		int backgroundIdxInGroup = 0;
		group.getChildren().get(backgroundIdxInGroup).requestFocus();
		timeline.play();
	}
	
	protected void initTimeline() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
      	
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	moveUser();     	
            	moveBullets();
            	moveEnemies(); 
            	generateEnemyFire();
            	handleEnemyPenetration();
            	checkBulletCollisions();
            	checkPlaneCollisions();
            	checkIfGameOver();
            }
        });
		timeline.getKeyFrames().add(gameLoop);
		
	}
	
	protected void initBackground() {
		background = new ImageView();
		setBackGroundImage();
		background.setFocusTraversable(true);
		background.setFitHeight(Main.getScreenHeight());
		background.setFitWidth(Main.getScreenWidth());
	    background.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent e) {
	        	KeyCode kc = e.getCode();
	        	if (kc == KeyCode.UP) user.setSteps(-user.getSpeed());
	        	if (kc == KeyCode.DOWN) user.setSteps(user.getSpeed());
	        	if (kc == KeyCode.SPACE) fireBullet();
	        	// Cheat Codes
	        	if (kc == KeyCode.L) lives = 0; // lose game automatically
	        	if (kc == KeyCode.ESCAPE) Platform.exit(); // Close game window
	        	if (kc == KeyCode.N) goToLevelTwo();
	        }
	    });
	    background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
            	KeyCode kc = e.getCode();
            	if (kc == KeyCode.UP || kc == KeyCode.DOWN) {
            		user.setSteps(0);
            	}
                
            }
        });
	    group.getChildren().add(background);
	}
	
	protected void setBackGroundImage() {
		background.setImage(ImageSetUp.getImageList().get(ImageSetUp.getBackground1()));
	}
	
	protected void initLossImage() {
		lossScreen = new ImageView();
		lossScreen.setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		lossScreen.setLayoutX(LOSS_SCREEN_X_POS);
		lossScreen.setLayoutY(LOSS_SCREEN_Y_POS);
		lossScreen.setVisible(false);
		group.getChildren().add(lossScreen);
	}
	
	protected void initScreenElements() {
		kills = 0;
		lives = 5;
		deaths = 0;
		r = new Random();
		hearts = new ArrayList<ImageView>();
		enemies = new ArrayList<EnemyPlane>();
		bullets = new ArrayList<Projectile>();
		enemyBullets = new ArrayList<EnemyProjectile>();
		createUser();
		createEnemies();	
	}
	
	protected void initHearts() {
		for (int i = 0; i < lives; i++) {
			ImageView heart = new ImageView();
			heart.setImage(ImageSetUp.getImageList().get(ImageSetUp.getHeart()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setFitWidth(HEART_WIDTH);
			heart.setTranslateX(HEART_DISPLAY_X_POS + (i*HEART_WIDTH));
			heart.setTranslateY(HEART_DISPLAY_Y_POS);
			hearts.add(heart);
			group.getChildren().add(heart);
		}
	}
	
	protected void createUser() {
		user = new FighterPlane();
		group.getChildren().add(user);	
	}
	
	protected void fireBullet() {
		double yPos = user.getLayoutY() + user.getTranslateY();
		Projectile p = user.fire(yPos);
		bullets.add(p);
		group.getChildren().add(p);
	}
	
	protected void moveUser() {
		double initialTranslateY = user.getTranslateY();
    	if (user.getSteps() != 0) {
    		user.setTranslateY(initialTranslateY + user.getSteps());
    		double curPos = user.getLayoutY() + user.getTranslateY();
    		if (curPos < USER_UPPER_BOUND) {
    			user.setTranslateY(initialTranslateY);
    		}
    		if (curPos > USER_LOWER_BOUND) {
    			user.setTranslateY(initialTranslateY);
    		}
    	}
	}
	
	protected void moveBullets() {
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		for (Projectile bullet : bullets) {
			bullet.setTranslateX(bullet.getTranslateX() + bullet.getVelocity());
			if (bullet.getTranslateX() > Main.getScreenWidth()) toRemove.add(bullet);
		}
		bullets.removeAll(toRemove);
		group.getChildren().removeAll(toRemove);
		moveEnemyBullets();
	}
	
	protected void moveEnemyBullets() {
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		for (Projectile enemyFire : enemyBullets) {
			enemyFire.setTranslateX(enemyFire.getTranslateX() + enemyFire.getVelocity());
			if (enemyFire.getTranslateX() > Main.getScreenWidth()) toRemove.add(enemyFire);
		}
		enemyBullets.removeAll(toRemove);
		group.getChildren().removeAll(toRemove);
	}
	
	protected void checkBulletCollisions() {
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		for (Projectile bullet : bullets) {
			for (EnemyPlane enemy : enemies) {
				if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
					kills++;
					toRemove.add(bullet);
					respawnEnemy(enemy);
				}
			}
		}
		bullets.removeAll(toRemove);
		group.getChildren().removeAll(toRemove);
		checkEnemyBulletCollisions();
	}
	
	protected void checkEnemyBulletCollisions() {
		ArrayList<EnemyProjectile> toRemoveEnemy = new ArrayList<EnemyProjectile>();
		for (EnemyProjectile bullet : enemyBullets) {
			if (bullet.getBoundsInParent().intersects(user.getBoundsInParent())) {
				toRemoveEnemy.add(bullet);
				removeHeart();
			}
		}
		enemyBullets.removeAll(toRemoveEnemy);
		group.getChildren().removeAll(toRemoveEnemy);
	}
	
	protected void createEnemies() {
		for (int i = 0; i < NUM_ENEMIES; i++) {
			EnemyPlane enemy = new EnemyPlane();
			setEnemyPos(enemy, true);
			enemies.add(enemy);
			group.getChildren().add(enemy);
		}	
	}
	
	private void setEnemyPos(EnemyPlane enemy, boolean newPlane) {
		int startY = r.nextInt(ENEMY_SPAWN_RANGE); // randomly determine Y coordinate of enemy
		enemy.setLayoutY(startY);
		if (newPlane) {
			enemy.setLayoutX(ENEMY_X_POS_INITIAL);
		} else {
			enemy.setTranslateX(0);
		}

	}
	
	protected void moveEnemies() {
		for (EnemyPlane enemy : enemies) {
			enemy.setTranslateX(enemy.getTranslateX() + enemy.getSteps());
		}
	}
	
	protected void handleEnemyPenetration() {
		for (EnemyPlane enemy : enemies) {
			if (Math.abs(enemy.getTranslateX()) > Main.getScreenWidth()) {
				respawnEnemy(enemy);
				removeHeart();
			}
		}
	}
	
	private void respawnEnemy(EnemyPlane enemy) {
			setEnemyPos(enemy, false);
	}
	
	protected void generateEnemyFire() {
		for (EnemyPlane enemy : enemies) {
			// 1 IN 250 CHANCE ENEMY FIRES
			if (r.nextInt(ENEMY_FIRE_PROB) == 1 ) {
				EnemyProjectile ep = new EnemyProjectile(enemy);		
				enemyBullets.add(ep);
				group.getChildren().add(ep);
			} 
		}
	}
	
	protected void checkPlaneCollisions() {
		for (EnemyPlane enemy : enemies) {
			if (user.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
				removeHeart();
				respawnEnemy(enemy);
			}
		}
	}
	
	protected void removeHeart() {
		lives--;
		if (deaths < MAX_DEATHS) {
			ImageView toRemove = hearts.get(deaths++);
			toRemove.setVisible(false);
		}
	}
	
	protected void checkIfGameOver() {
		if (lives < MIN_LIVES) {
			timeline.stop();
			lossScreen.setVisible(true);
			user.setVisible(false);
		}
		if (kills > KILLS_FOR_NEXT_LEVEL) {
			goToLevelTwo();
		}
	}
	
	protected void goToLevelTwo() {
		timeline.stop();
		Main.getMyController().switchScreens(LEVEL_TWO_CODE);
	}
	
	public static int getMillisecondDelay() {
		return MILLISECOND_DELAY;
	}
	
}
