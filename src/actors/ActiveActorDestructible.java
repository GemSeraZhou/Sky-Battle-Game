package actors;

import javafx.scene.image.Image;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	
	public ActiveActorDestructible(Image image, int imageHeight, double initialXPos, double initialYPos) {
		super(image, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	@Override
	public abstract void updatePosition();
	
	public abstract void updateActor();
	
	@Override
	public abstract void takeDamage();
	
	@Override
	public void destroy() {
		setDestroyed(true);
	}
	
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
