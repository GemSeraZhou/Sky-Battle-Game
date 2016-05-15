package actors;

/**
 * 
 * Projectile for enemies in Level One of Sky Battle
 * 
 * @author Stephen
 *
 */
public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	
	/**
	 * Creates an instance of EnemyProjectile
	 * @param initialXPos: the initial X position of the projectile
	 * @param initialYPos: the initial Y position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
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
