package actors;

import javafx.scene.image.Image;
import view.ImageSetUp;

/**
 * 
 * Projectile for enemies in Level One of Sky Battle
 * 
 * @author Stephen
 *
 */
public class EnemyProjectile extends Projectile {
	
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getEnemyFire());
	
	/**
	 * Creates an instance of EnemyProjectile
	 * @param initialXPos: the initial X position of the projectile
	 * @param initialYPos: the initial Y position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos);
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
