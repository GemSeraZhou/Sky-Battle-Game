package game_smk44;

import javafx.scene.image.ImageView;

public class BossProjectile extends Projectile  {
	
	private static final int BULLET_HEIGHT = 75;
	private static final int BULLET_WIDTH = 100;
	private static final int INITIAL_X_POS = Main.getScreenWidth() - 350;
	private static final int Y_POS_ADJUSTMENT = 100;
	
	
	public BossProjectile(double yPos) {
		velocity = -15;
		this.setLayoutX(INITIAL_X_POS);
		this.setLayoutY(yPos + Y_POS_ADJUSTMENT);
	}
	
	@Override
	protected void setUpImage() {
		imageView.setFitHeight(BULLET_HEIGHT);
		imageView.setFitWidth(BULLET_WIDTH);
		imageView.setImage(ImageSetUp.getImageList().get(ImageSetUp.getFireball()));
	}

}
