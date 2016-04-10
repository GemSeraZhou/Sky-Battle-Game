package actors;

import controller.ImageSetUp;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class Projectile extends Parent {
	
	private static final int BULLET_HEIGHT = 125;
	private static final int BULLET_WIDTH = 250;
	
	protected ImageView imageView;
	protected double velocity;
	
	
	public Projectile() {
		velocity = 15;
		initImage();
	}
	
	protected void initImage() {
		imageView = new ImageView();
		setUpImage();
		getChildren().add(imageView);
	}
	
	protected void setUpImage() {
		imageView.setFitHeight(BULLET_HEIGHT);
		imageView.setFitWidth(BULLET_WIDTH);
		imageView.setImage(ImageSetUp.getImageList().get(ImageSetUp.getUserFire()));
	}
	
	public double getVelocity() {
		return velocity;
	}
	
}
