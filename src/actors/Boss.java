package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class Boss extends Actor {

	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getBossPlane());
	
	public Boss() {
		super(IMAGE,IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION);
	}
	
	public void move() {

	}
	
	

}
