package actors;

import javafx.scene.image.Image;
import view.ImageSetUp;

/**
 * User-Controlled fighter plane for Sky Battle
 * 
 * @author Stephen
 *
 */
public class UserPlane extends FighterPlane {

	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getUserPlane());
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplier;
	private int numberOfKills;
	
	/**
	 * Creates an instance of UserPlane
	 * @param initialHealth: initial health of the user
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}
	
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}
	
	/**
	 * 
	 * @return true if UserPlane is in motion; false otherwise
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}
	
	/**
	 * Moves the UserPlane upward when position is updated
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}
	
	/**
	 * Moves the UserPlane downward when position is updated
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}
	
	/**
	 * Stops the UserPlane's motion when position is updated
	 */
	public void stop() {
		velocityMultiplier = 0;
	}
	
	/**
	 * 
	 * @return the user's number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}
	
	/**
	 * Increments the UserPlane's kill count
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}
