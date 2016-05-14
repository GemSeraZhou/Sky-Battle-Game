package actors;

import javafx.scene.image.Image;

/**
 * Abstract class for all fighter planes in Sky Battle
 * 
 * @author Stephen
 *
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;
	
	/**
	 * Creates an instance of a FighterPlane
	 * @param image: image representing the plane
	 * @param imageHeight: height of the plane's image
	 * @param initialXPos: initial x position of the plane
	 * @param initialYPos: initial y position of the plane
	 * @param health: initial health of the plane
	 */
	public FighterPlane(Image image, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(image, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile 
	 * @return a Projectile
	 */
	public abstract ActiveActorDestructible fireProjectile();
	
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}
	
	/**
	 * 
	 * @param xPositionOffset: offset for projectile's x position
	 * @return: initial x position of the projectile fired by fighter plane
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * 
	 * @param yPositionOffset: offset for projectile's y position
	 * @return: initial y position of the projectile fired by fighter plane
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}
	
	/**
	 * 
	 * @return true if the fighter plane's health is at zero; false otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}
	
	/**
	 * 
	 * @return the health of the fighter plane
	 */
	public int getHealth() {
		return health;
	}
		
}
