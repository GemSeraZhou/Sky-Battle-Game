package actors;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ActiveActor extends Parent {
	
	private final ImageView myImageView;
	
	public ActiveActor(Image image, int imageHeight, double initialXPos, double initialYPos) {
		this.myImageView = new ImageView(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.myImageView.setFitHeight(imageHeight);
		this.myImageView.setPreserveRatio(true);
	}
	
	public abstract void updatePosition();
	
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}
	
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
