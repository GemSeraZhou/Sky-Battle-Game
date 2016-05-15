package levels;

import actors.Boss;
import view.LevelView;
import view.LevelViewLevelTwo;

/**
 * Level Two of Sky Battle 
 * 
 * @author Stephen
 *
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	
	/**
	 * Creates an instance of Sky Battle's second level
	 * @param screenHeight: height of the screen
	 * @param screenWidth: width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
