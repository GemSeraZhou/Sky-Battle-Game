package game_smk44;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle"; 
    private static Controller myController;


    /**
     * Set up
     */
    @Override
    public void start (Stage stage) {
    	ImageSetUp.initialize();
    	Group root = new Group();
    	Scene scene = new Scene(root);
    	stage.setTitle(TITLE);
    	stage.setResizable(false);
    	stage.setHeight(SCREEN_HEIGHT);
    	stage.setWidth(SCREEN_WIDTH);
    	stage.setScene(scene);
    	myController = new Controller(root);
    	myController.switchScreens(myController.getLoadScreenCode());
    	stage.show();
    }

    public static Controller getMyController() {
		return myController;
	}
    
    public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	public static int getScreenHeight() {
		return SCREEN_HEIGHT;
	}


	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch();
    }
}