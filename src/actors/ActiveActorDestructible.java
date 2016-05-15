package actors;

/**
 * Actor for Sky Battle Game with ability to update its position, take damage, and be destroyed
 * 
 * @author Stephen
 *
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	
	/**
	 * Creates an instance of ActiveActorDestructible
	 * @param image: the name of the image representing the ActiveActorDestructible
	 * @param imageHeight: the height of the ActiveActorDestructible's image
	 * @param initialXPos: the initial x coordinate of the ActiveActorDestructible
	 * @param initialYPos: the initial y coordinate of the ActiveActorDestructible
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	@Override
	public abstract void updatePosition();
	
	/**
	 * Updates the ActiveActorDestructible
	 */
	public abstract void updateActor();
	
	/**
	 * Handles damage to the ActiveActorDestructible
	 */
	@Override
	public abstract void takeDamage();
	
	/**
	 * Destroys the ActiveActorDestructible
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}
	
	/**
	 * Sets the ActiveActorDestructible's isDestroyed property to given parameter
	 * @param isDestroyed: true if actor is to be destroyed; false otherwise
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	/**
	 * 
	 * @return true if actor is destroyed; false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
