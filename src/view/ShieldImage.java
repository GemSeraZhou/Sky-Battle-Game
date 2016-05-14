package view;

import javafx.scene.image.ImageView;


/**
 * Creates an image of a shield that can be displayed or hidden
 * 
 * @author Stephen
 *
 */
public class ShieldImage extends ImageView {
	
	private static final int SHIELD_SIZE = 200;
	
	/**
	 * Creates an instance of a ShieldImage at a given x and y coordinate
	 * 
	 * @param xPosition: the initial x position of the image
	 * @param yPosition: the initial y position of the image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(ImageSetUp.getImageList().get(ImageSetUp.getShield()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}
	
	/**
	 * Displays image of a shield
	 */
	public void showShield() {
		this.setVisible(true);
	}
	
	/**
	 * Hides currently displayed image of a shield
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}
