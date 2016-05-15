package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Images indicating user has won the game
 * 
 * @author Stephen
 *
 */
public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	
	/**
	 * Creates an instance of WinImage
	 * @param xPosition: x position of image
	 * @param yPosition: y position of image
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(IMAGE_NAME));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}
	
	/**
	 * Displays the image
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

}
