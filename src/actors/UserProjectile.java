package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class UserProjectile extends ActiveActorDestructible {

	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getUserFire());
	
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
}
