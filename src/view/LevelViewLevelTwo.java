package view;

import javafx.scene.Group;

/**
 * 
 * View for Level Two of Sky Battle
 * 
 * @author Stephen
 *
 */
public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	
	/**
	 * Creates an instance of LevelViewLevelTwo
	 * @param root: Group holding all nodes displayed in level
	 * @param heartsToDisplay: the number of hearts to show in the HeartDisplay
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}
	
	/**
	 * Adds images to root
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	/**
	 * Displays the ShieldImage
	 */
	public void showShield() {
		shieldImage.showShield();
	}
	
	/**
	 * Hides the ShieldImage
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

}
