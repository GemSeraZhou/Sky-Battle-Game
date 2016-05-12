package view;

import controller.ImageSetUp;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	
	public GameOverImage(double xPosition, double yPosition) {
		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}

}
