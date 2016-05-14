package view;

import javafx.scene.Group;

/**
 * View for a Level in Sky Battle
 * 
 * @author Stephen
 *
 */
public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	
	/**
	 * Creates an instance of LevelView
	 * @param root: group that all nodes to be displayed in game must be added to
	 * @param heartsToDisplay: the number of hearts to display at the beginning of the game
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
	}
	
	/**
	 * Displays the hearts representing the user's number of lives remaining
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}
	
	/**
	 * Shows the image indicating the user has completed the game
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	
	/**
	 * Shows the image indicating the user has been defeated and the game is over
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}
	
	/**
	 * Removes hearts from the heart display
	 * @param heartsRemaining: the number of hearts that should be displayed
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
