package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class UserPlane extends FighterPlane {

	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 600.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getUserPlane());
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final int INITIAL_HEALTH = 5;
	private int velocityMultiplier;
	private int numberOfKills;
	
	
	public UserPlane() {
		super(IMAGE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, INITIAL_HEALTH);
		velocityMultiplier = 0;
	}
	
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}
	
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}
	
	public void moveUp() {
		velocityMultiplier = -1;
	}
	
	public void moveDown() {
		velocityMultiplier = 1;
	}
	
	public void stop() {
		velocityMultiplier = 0;
	}
	
	public int getNumberOfKills() {
		return numberOfKills;
	}
	
	public void incrementKillCount() {
		numberOfKills++;
	}

}