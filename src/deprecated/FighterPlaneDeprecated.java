package deprecated;
//package actors;
//
//import controller.ImageSetUp;
//import controller.Main;
//import javafx.scene.Parent;
//import javafx.scene.image.ImageView;
//
//public class FighterPlaneDeprecated extends Parent {
//	
//	protected ImageView imageView;	
//	protected int steps;
//	protected int speed;
//	private static final int PLANE_X_POS = 5;
//	private static final int PLANE_Y_POS = Main.getScreenHeight()/2 - 60;
//	private static final int PLANE_HEIGHT = 150;
//	private static final int PLANE_WIDTH = 200;
//	private static final int BULLET_X_POS = 110;
//	private static final int BULLET_Y_POS_ADJUST = 20;
//	
//
//
//	public FighterPlaneDeprecated() {
//		initializeImage();
//		this.setLayoutX(PLANE_X_POS);
//		this.setLayoutY(PLANE_Y_POS);	
//		steps = 0;
//		speed = 8;
//	}
//	
//	protected void initializeImage() {
//		imageView = new ImageView();
//		imageView.setImage(ImageSetUp.getImageList().get(ImageSetUp.getUserPlane()));
//		imageView.setFitHeight(PLANE_HEIGHT);
//		imageView.setFitWidth(PLANE_WIDTH);
//		getChildren().add(imageView);
//	}
//	
//	public Projectile fire(double yCoor) {
//		Projectile p = new Projectile();
//		p.setTranslateX(BULLET_X_POS);
//		p.setTranslateY(yCoor + BULLET_Y_POS_ADJUST);
//		return p;
//	}
//	
//	public int getSteps() {
//		return steps;
//	}
//	
//	public void setSteps(int numSteps) {
//		steps = numSteps;
//	}
//	
//	public int getSpeed() {
//		return speed;
//	}
//	
//}
