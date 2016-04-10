package game_smk44;

import javafx.scene.image.Image;

public class UserProjectile extends HorizontalActor {

	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getUserFire());
	
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}
	
}
