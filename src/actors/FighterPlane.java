package actors;

import javafx.scene.image.Image;

public abstract class FighterPlane extends ActiveActorDestructible {

	private double health;
	
	public FighterPlane(Image image, int imageHeight, double initialXPos, double initialYPos, double health) {
		super(image, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	public abstract ActiveActorDestructible fireProjectile();
	
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}
	
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}
	
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}
	
	private boolean healthAtZero() {
		return health == 0;
	}
		
}
