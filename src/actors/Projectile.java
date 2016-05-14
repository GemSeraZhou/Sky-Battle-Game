package actors;

import javafx.scene.image.Image;

/**
 * Abstract class for all projectile in Sky Battle  
 * 
 * @author Stephen
 *
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Creates an instance of Projectile
	 * @param image: image for the projectile
	 * @param imageHeight: height of the projectile's image
	 * @param initialXPos: initial x position of the projectile
	 * @param initialYPos: initial y position of the projectile
	 */
	public Projectile(Image image, int imageHeight, double initialXPos, double initialYPos) {
		super(image, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}
