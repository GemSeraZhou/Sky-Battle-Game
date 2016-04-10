package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class Enemy extends HorizontalActor {
	
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getEnemyPlane());

	public Enemy(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}

}
