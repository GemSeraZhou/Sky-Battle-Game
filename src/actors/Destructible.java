package actors;

/**
 * Interface for all actors in Sky Battle that can take damage and be destroyed
 * 
 * @author Stephen
 *
 */
public interface Destructible {

	/**
	 * Handles damage dealt to Destructible
	 */
	void takeDamage();
	
	/**
	 * Destroys the Destructible
	 */
	void destroy();
	
}
