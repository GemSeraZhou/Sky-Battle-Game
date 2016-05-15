package actors;

/**
 * Projectile for user-controlled player in Sky Battle
 * 
 * @author Stephen
 *
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	
	/**
	 * Creates an instance of UserProjectile
	 * @param initialXPos: initial x position of projectile
	 * @param initialYPos: initial y position of projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
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
