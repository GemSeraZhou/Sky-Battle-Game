package controller;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

// ALL IMAGES TAKEN FROM GOOGLE IMAGES

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

	public static ObservableList<Image> getImageList() {
		return imageList;
	}
	
	public static int getLoadScreenBackground() {
		return LOAD_SCREEN_BACKGROUND;
	}
	
	public static int getBackground1() {
		return BACKGROUND_1;
	}

	public static int getUserPlane() {
		return USER_PLANE;
	}
	
	public static int getEnemyPlane() {
		return ENEMY_PLANE;
	}
	
	public static int getUserFire() {
		return USER_FIRE;
	}
	
	public static int getEnemyFire() {
		return ENEMY_FIRE;
	}
	
	public static int getHeart() {
		return HEART;
	}
	
	public static int getGameOver() {
		return GAME_OVER;
	}
	
	public static int getBackground2() {
		return BACKGROUND_2;
	}
	
	public static int getBossPlane() {
		return BOSS_PLANE;
	}
	
	public static int getFireball() {
		return FIREBALL;
	}
	
	public static int getYouWin() {
		return YOU_WIN;
	}
	
	public static int getShield() {
		return SHIELD;
	}

	public static String[] getFileNames() {
		return FILE_NAMES;
	}
}
