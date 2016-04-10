package game_smk44;

import javafx.scene.image.Image;

public class EnemyFire extends HorizontalActor {
	
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getEnemyFire());
	
	public EnemyFire(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos, HORIZONTAL_VELOCITY);
	}


}
