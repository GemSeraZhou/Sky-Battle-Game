package actors;

import javafx.scene.image.*;

/**
 * Actor for Sky Battle Game with the ability to change positions
 * 
 * @author Stephen
 *
 */

public abstract class ActiveActor extends ImageView {
	
	private static final String IMAGE_LOCATION = "/images/";
	
	/**
	 * Creates an instance of an ActiveActor
	 * @param imageName: the name of the image representing the ActiveActor
	 * @param imageHeight: the height of the ActiveActor's image
	 * @param initialXPos: the initial x coordinate of the ActiveActor
	 * @param initialYPos: the initial y coordinate of the ActiveActor
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}
	
	/**
	 * Updates the position of the ActiveActor
	 */
	public abstract void updatePosition();
	
	/**
	 * Moves the ActiveActor horizontally by the amount specified by the parameter
	 * 
	 * @param horizontalMove: distance to move ActiveActor horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}
	
	/**
	 * Moves the ActiveActor vertically by the amount specified by the parameter
	 * 
	 * @param horizontalMove: distance to move ActiveActor vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
