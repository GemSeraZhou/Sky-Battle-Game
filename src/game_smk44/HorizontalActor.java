package game_smk44;

import javafx.scene.image.Image;

public abstract class HorizontalActor extends Actor {

	private int horizontalVelocity;
	
	public HorizontalActor(Image image, int imageHeight, double initialXPos, double initialYPos, int horizontalVelocity) {
		super(image, imageHeight, initialXPos, initialYPos);
		this.horizontalVelocity = horizontalVelocity;
	}

	protected void move() {
		moveHorizontally(horizontalVelocity);
	}
	
}
