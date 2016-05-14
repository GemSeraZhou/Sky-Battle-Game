package levels;

import actors.Boss;
import javafx.scene.image.Image;
import view.ImageSetUp;
import view.LevelView;
import view.LevelViewLevelTwo;

/**
 * Level Two of Sky Battle 
 * 
 * @author Stephen
 *
 */
public class LevelTwo extends LevelParent {

	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final Image BACKGROUND_IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getBackground1());
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	
	/**
	 * Creates an instance of Sky Battle's second level
	 * @param screenHeight: height of the screen
	 * @param screenWidth: width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
