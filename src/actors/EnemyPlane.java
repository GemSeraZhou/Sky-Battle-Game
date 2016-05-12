package actors;

import controller.ImageSetUp;
import javafx.scene.image.Image;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final Image IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getEnemyPlane());
	private static final double PROJECTILE_X_POSITION_OFFSET = 300.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 0.0;
	private static final double INITIAL_HEALTH = 1.0;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return new EnemyFire(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET),
				getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

}
