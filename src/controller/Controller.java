package controller;

import javafx.scene.Group;
import levels.Level;
import levels.LevelTwo;

public class Controller {
	
	private Group root;
	
	// Screen ID's
	private static final int LOAD_SCREEN = 0;
	private static final int LEVEL_ONE = 1;
	private static final int LEVEL_TWO = 2;
	
	private LoadScreen myLoadScreen;
	private Level myGame;
	private Level myGameLevelTwo;
	
	public Controller(Group root) {
		this.root = root;
	}
	
	public void launchGame() {
		switchScreens(LEVEL_ONE);
	}
	
	public void switchScreens(int newScreenID) {
		if (newScreenID == LOAD_SCREEN) {
			myLoadScreen = new LoadScreen();
			root.getChildren().add(myLoadScreen);
			myLoadScreen.start();
		}
		if (newScreenID == LEVEL_ONE) {
			root.getChildren().remove(myLoadScreen);
			myLoadScreen = null;
			myGame = new Level();
			root.getChildren().add(myGame);
			myGame.start();
		}
		if (newScreenID == LEVEL_TWO) {
			root.getChildren().remove(myGame);
			myGame = null;
			myGameLevelTwo = new LevelTwo();
			root.getChildren().add(myGameLevelTwo);
			myGameLevelTwo.start();			
		}
		
	}

	public int getLoadScreenCode() {
		return LOAD_SCREEN;
	}
	
	public int getLevelOneCode() {
		return LEVEL_ONE;
	}
	
	public int getLevelTwoCode() {
		return LEVEL_TWO;
	}

}
