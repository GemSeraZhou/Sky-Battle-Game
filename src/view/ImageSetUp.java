package view;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 * Sets up all the images for the game (All images taken from Google Images
 * 
 * @author Stephen
 *
 */
public class ImageSetUp {
	
	private static ObservableList<Image> imageList = javafx.collections.FXCollections.<Image>observableArrayList();

	public static final String IMAGE_LOCATION = "/images/";
	
	private static final int LOAD_SCREEN_BACKGROUND = 0;
	private static final int BACKGROUND_1 = 1;
	private static final int USER_PLANE = 2;
	private static final int ENEMY_PLANE = 3;
	private static final int USER_FIRE = 4;
	private static final int ENEMY_FIRE = 5;
	private static final int HEART = 6;
	private static final int GAME_OVER = 7;
	private static final int BACKGROUND_2 = 8;
	private static final int BOSS_PLANE = 9;
	private static final int FIREBALL = 10;
	private static final int YOU_WIN = 11;
	private static final int SHIELD = 12;

	private static final String[] FILE_NAMES = new String[] {
			"loadscreenbackground.jpg",
			"background1.jpg",
			"userplane.png",
			"enemyplane.png",
			"userfire.png",
			"enemyFire.png",
			"heart.png",
			"gameover.png",
			"background2.jpg",
			"bossplane.png",
			"fireball.png",
			"youwin.png",
			"shield.png"
	};	


	/**
	 * Initializes the list of images
	 */
	public static void initialize() {
		for (String imageName : FILE_NAMES) {
			Image image = new Image(ImageSetUp.class.getResourceAsStream(IMAGE_LOCATION + imageName));
			if (image.isError()) {
				System.out.println("Error Loading " + imageName);
			} else {
				imageList.add(image);
			}
		}
	}

	/**
	 * @return the full list of images
	 */
	public static ObservableList<Image> getImageList() {
		return imageList;
	}
	
	/**
	 * @return index of image of background1 in list of images
	 */
	public static int getBackground1() {
		return BACKGROUND_1;
	}
	
	/**
	 * @return index of image of user plane in list of images
	 */
	public static int getUserPlane() {
		return USER_PLANE;
	}
	
	/**
	 * @return index of image of enemy plane in list of images
	 */
	public static int getEnemyPlane() {
		return ENEMY_PLANE;
	}
	
	/**
	 * @return index of image of user projectile in list of images
	 */
	public static int getUserFire() {
		return USER_FIRE;
	}
	
	/**
	 * @return index of image of enemy projectile in list of images
	 */
	public static int getEnemyFire() {
		return ENEMY_FIRE;
	}
	
	/**
	 * @return index of image of heart in list of images
	 */
	public static int getHeart() {
		return HEART;
	}
	
	/**
	 * @return index of image of game over image in list of images
	 */
	public static int getGameOver() {
		return GAME_OVER;
	}
	
	/**
	 * @return index of image of background2 in list of images
	 */
	public static int getBackground2() {
		return BACKGROUND_2;
	}
	
	/**
	 * @return index of image of the boss in list of images
	 */
	public static int getBossPlane() {
		return BOSS_PLANE;
	}
	
	/**
	 * @return index of image of a fireball projectile in list of images
	 */
	public static int getFireball() {
		return FIREBALL;
	}
	
	/**
	 * @return index of image of win image in list of images
	 */
	public static int getYouWin() {
		return YOU_WIN;
	}
	
	/**
	 * @return index of image of shield in list of images
	 */
	public static int getShield() {
		return SHIELD;
	}

	/**
	 * @return the String array of image file names
	 */
	public static String[] getFileNames() {
		return FILE_NAMES;
	}
}
