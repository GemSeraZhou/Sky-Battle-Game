package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class BossFire extends Projectile {
	
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getFireball());
	
	public BossFire(double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
