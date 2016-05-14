package view;

import controller.ImageSetUp;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {
	
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	
	public WinImage(double xPosition, double yPosition) {
		this.setImage(ImageSetUp.getImageList().get(ImageSetUp.getYouWin()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}
	
	public void showWinImage() {
		this.setVisible(true);
	}

}
