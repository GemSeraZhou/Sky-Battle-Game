package actors;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Actor extends Parent {
	
	private final ImageView myImageView;
	
	public Actor(Image image, int imageHeight, double initialXPos, double initialYPos) {
		this.myImageView = new ImageView(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.myImageView.setFitHeight(imageHeight);
		this.myImageView.setPreserveRatio(true);
	}
	
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}
	
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
