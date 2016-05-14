package levels;
//package levels;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//import actors.BossPlane;
//import actors.BossProjectile;
//import actors.Projectile;
//import controller.ImageSetUp;
//import controller.Main;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.image.ImageView;
//import javafx.util.Duration;
//
//public class LevelTwo extends Level {
//	
//	private static final int MAX_FRAMES_WITH_SHIELD = 750;
//	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
//	private static final int BOSS_UPPER_BOUND = -100;
//	private static final int BOSS_LOWER_BOUND = Main.getScreenHeight() - 225;
//	private static final int BOSS_FIRE_RATE = 4;
//	private static final int BOSS_FIRE_PROB = 100;
//	private static final int BOSS_SHIELD_PROB = 500;
//	private static final int WIN_SCREEN_X_POS = 355;
//	private static final int WIN_SCREEN_Y_POS = 175;
//	private static final int WIN_SCREEN_HEIGHT = 500;
//	private static final int WIN_SCREEN_WIDTH = 600;
//	private static final int SHIELD_X_POS = Main.getScreenWidth() - 150;
//	private static final int SHIELD_Y_POS = Main.getScreenHeight() - 200;
//	private static final int SHIELD_DIMENSIONS = 200;
//	
//	private int bossHealth;
//	private BossPlane boss;
//	private ImageView winScreen; 
//	private ImageView shieldImage;
//
//	@Override
//	protected void initTimeline() {
//		timeline = new Timeline();
//		timeline.setCycleCount(Timeline.INDEFINITE);
//      	
//		KeyFrame gameLoop = new KeyFrame(Duration.millis(Level.getMillisecondDelay()), new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//            	moveUser();  
//            	moveEnemies();
//            	moveBullets();
//            	generateEnemyFire();
//            	manageBossShield();
//            	checkBulletCollisions();
//            	checkIfGameOver();
//            }
//        });
//		timeline.getKeyFrames().add(gameLoop);
//	}
//	
//	@Override
//	protected void initScreenElements() {
//		createUser();
//		initBoss();
//		initWinScreen();
//		initShield();
//		lives = 5;
//		deaths = 0;
//		r = new Random();
//		hearts = new ArrayList<ImageView>();
//		bullets = new ArrayList<Projectile>();
//	}
//	
//	private void initBoss() {
//		boss = new BossPlane();
//		bossHealth = 100;
//		group.getChildren().add(boss);
//	}
//	
//	private void initWinScreen() {
//		winScreen = new ImageView();
//		winScreen.setImage(ImageSetUp.getImageList().get(ImageSetUp.getYouWin()));
//		winScreen.setVisible(false);
//		winScreen.setFitHeight(WIN_SCREEN_HEIGHT);
//		winScreen.setFitWidth(WIN_SCREEN_WIDTH);
//		winScreen.setLayoutX(WIN_SCREEN_X_POS);
//		winScreen.setLayoutY(WIN_SCREEN_Y_POS);
//		group.getChildren().add(winScreen);
//	}
//	
//	private void initShield() {
//		shieldImage = new ImageView();
//		shieldImage.setImage(ImageSetUp.getImageList().get(ImageSetUp.getShield()));
//		shieldImage.setVisible(false);
//		shieldImage.setFitHeight(SHIELD_DIMENSIONS);
//		shieldImage.setFitWidth(SHIELD_DIMENSIONS);
//		shieldImage.setLayoutX(SHIELD_X_POS);
//		shieldImage.setLayoutY(SHIELD_Y_POS);
//		group.getChildren().add(shieldImage);
//		
//	}
//	
//	@Override
//	protected void setBackGroundImage() {
//		background.setImage(ImageSetUp.getImageList().get(ImageSetUp.getBackground2()));
//	}
//	
//	@Override
//	protected void moveEnemies() {
//		double initialTranslateY = boss.getTranslateY();
//		boss.setTranslateY(initialTranslateY + getNextMove());
//		double curPos = boss.getLayoutY() + boss.getTranslateY();
//		if (curPos < BOSS_UPPER_BOUND) {
//			boss.setTranslateY(initialTranslateY);
//		}
//		if (curPos > BOSS_LOWER_BOUND) {
//			boss.setTranslateY(initialTranslateY);
//		}
//	}
//	
//	/** determines boss's next move;
//	 *  to improve smoothness of movements, boss makes same move for 10 consecutive frames
//	 *  if move is last move in list of possible moves, shuffle list and reset index of current move to 0
//	 * 
//	 * @return boss's next move in the Y direction
//	 */
//	private int getNextMove() {
//		int nextMove = boss.getMovePattern().get(boss.getCurMove());
//		if (boss.getConsecMoves() < MAX_FRAMES_WITH_SAME_MOVE) {
//			boss.setConsecMoves(boss.getConsecMoves()+1);
//			return nextMove;
//		}
//		if (boss.getCurMove() == boss.getMovePattern().size() - 1) {
//			boss.shufflePattern();
//			boss.setCurMove(0);			
//		} else {
//			boss.setCurMove(boss.getCurMove() + 1);
//		}
//		boss.setConsecMoves(0);
//		return nextMove;
//	}
//	
//    @Override
//    protected void checkIfGameOver() {
//		if (lives < 1) {
//			timeline.stop();
//			lossScreen.setVisible(true);
//		}
//		if (bossHealth <= 0) {
//			timeline.stop();
//			winScreen.setVisible(true);
//			boss.setVisible(false);
//		}
//    }
//    
//    @Override
//    protected void generateEnemyFire() {
//    	int randomNum = r.nextInt(BOSS_FIRE_PROB);
//    	if (randomNum <  BOSS_FIRE_RATE) generateBossFire();
//    }
//    
//    private void generateBossFire() {
//    	double curPos = boss.getLayoutY() + boss.getTranslateY();
//    	BossProjectile bp = new BossProjectile(curPos);
//    	bullets.add(bp);
//    	group.getChildren().add(bp);
//    }
//        
//    @Override
//    protected void checkBulletCollisions() {
//		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
//		for (Projectile bullet : bullets) {
//			if (bullet.getClass() == BossProjectile.class) {
//				handleEnemyBulletCollision(toRemove, bullet);
//			}
//			else {
//				handleUserBulletCollision(toRemove, bullet);
//			}
//		}
//		bullets.removeAll(toRemove);
//		group.getChildren().removeAll(toRemove);
//    }
//    
//    @Override
//    protected void handleUserBulletCollision(ArrayList<Projectile> toRemove, Projectile bullet) {
//		if (bullet.getBoundsInParent().intersects(boss.getBoundsInParent())) {
//			if (!boss.isShielded()) bossHealth--;
//			toRemove.add(bullet);
//		}
//    }
//    
//    @Override
//    protected void handleEnemyBulletCollision(ArrayList<Projectile> toRemove, Projectile bullet) {
//    	if (bullet.getBoundsInParent().intersects(user.getBoundsInParent())) {
//			toRemove.add(bullet);
//			removeHeart();
//		}
//    }
//    
//    @Override
//    protected void moveBullets() {
//		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
//		for (Projectile bullet : bullets) {
//			bullet.setTranslateX(bullet.getTranslateX() + bullet.getVelocity());
//			if (bullet.getTranslateX() > Main.getScreenWidth()) toRemove.add(bullet);
//		}
//		bullets.removeAll(toRemove);
//		group.getChildren().removeAll(toRemove);
//    }
//        
//    private void manageBossShield() {
//    	if (boss.isShielded()) {
//    		int curFrames = boss.getCurrentFramesWithShield();
//    		if (curFrames <= MAX_FRAMES_WITH_SHIELD) {
//    			boss.setCurrentFramesWithShield(curFrames + 1);
//    		} else {
//    			boss.setCurrentFramesWithShield(0);
//    			shieldImage.setVisible(false);
//    			boss.setShielded(false);
//    		}
//    	}
//    	else {
//        	int randomNum = r.nextInt(BOSS_SHIELD_PROB);
//        	if (randomNum == 1) { 
//        		boss.setShielded(true);
//        		boss.setCurrentFramesWithShield(1);
//        		shieldImage.setVisible(true);
//        	}
//    	}   	
//    }
//
//}
