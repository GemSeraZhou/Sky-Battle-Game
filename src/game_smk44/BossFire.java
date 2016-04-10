package game_smk44;

import javafx.scene.image.Image;

public class BossFire extends HorizontalActor {
	
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getFireball());
	
	public BossFire(int initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, HORIZONTAL_VELOCITY);
	}

	
	

}
