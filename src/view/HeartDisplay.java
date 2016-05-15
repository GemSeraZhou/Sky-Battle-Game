package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays hearts representing the user's number of lives remaining
 * 
 * @author Stephen
 *
 */
public class HeartDisplay {
	
	private static final String HEART_IMAGE_NAME = "/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;
	
	/**
	 * Creates an instance of HeartDisplay
	 * 
	 * @param xPosition: x position of the display
	 * @param yPosition: y position of the display
	 * @param heartsToDisplay: initial number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) { 
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}
	
	/**
	 * Initializes the container holding the hearts
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}
	
	/**
	 * Creates hearts to be displayed and adds them to the container
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(HEART_IMAGE_NAME));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}
	
	/**
	 * Removes a heart from the heart display
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	
	/**
	 * @return the container holding all the hearts
	 */
	public HBox getContainer() {
		return container;
	}

}
