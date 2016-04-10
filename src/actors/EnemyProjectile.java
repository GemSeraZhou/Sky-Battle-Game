package actors;

import controller.ImageSetUp;
import javafx.scene.image.ImageView;

public class EnemyProjectile extends Projectile {
	
	private static final int BULLET_HEIGHT = 50;
	private static final int BULLET_WIDTH = 75;
	private static final int xPosAdjust = 1000;
	private static final int yPosAdjust = 35;
	
	public EnemyProjectile(EnemyPlane enemy) {
		this.setLayoutY(enemy.getLayoutY() + yPosAdjust);
		this.setTranslateX(enemy.getTranslateX() + xPosAdjust);
		velocity = -10;
		initImage();
	}
	
	@Override
	protected void initImage() {
		imageView = new ImageView();
		imageView.setImage(ImageSetUp.getImageList().get(ImageSetUp.getEnemyFire()));
		imageView.setFitHeight(BULLET_HEIGHT);
		imageView.setFitWidth(BULLET_WIDTH);
		getChildren().add(imageView);
	}

}
