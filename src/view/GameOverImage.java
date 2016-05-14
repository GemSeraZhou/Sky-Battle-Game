package view;

import javafx.scene.image.ImageView;

/**
 * Image indicating game is over
 * 
 * @author Stephen
 *
 */
public class GameOverImage extends ImageView {
	
	/**
	 * Creates an instance of GameOverImage
	 * @param xPosition: x position of the image
	 * @param yPosition: y position of the image
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}
