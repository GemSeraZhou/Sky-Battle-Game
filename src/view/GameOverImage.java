package view;

import controller.ImageSetUp;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final int LOSS_SCREEN_X_POS = -160;
	private static final int LOSS_SCREEN_Y_POS = -375;
	
	public GameOverImage() {
		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(LOSS_SCREEN_X_POS);
		setLayoutY(LOSS_SCREEN_Y_POS);
	}

}
