package actors;

import javafx.scene.image.Image;

public abstract class Projectile extends ActiveActorDestructible {

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
