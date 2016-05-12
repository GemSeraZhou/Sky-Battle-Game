package controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private static Controller myController;

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ImageSetUp.initialize();
		Group root = new Group();
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		stage.setScene(scene);
		myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch();
	}
}