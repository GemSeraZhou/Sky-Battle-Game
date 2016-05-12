package levels;

import actors.ActiveActorDestructible;
import actors.EnemyPlane;
import actors.UserPlane;
import controller.ImageSetUp;
import javafx.scene.image.Image;

public class LevelOne extends LevelParent {
	
	private static final String NEXT_LEVEL = "levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 100;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final Image BACKGROUND_IMAGE = ImageSetUp.getImageList().get(ImageSetUp.getBackground1());

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void goToNextLevel() {
		setChanged();
		notifyObservers(NEXT_LEVEL);
	}

	@Override
	protected void checkIfGameOver() {
		UserPlane user = (UserPlane) getUser();
		if (user.isDestroyed())
			endGame();
		else if (user.getNumberOfKills() >= KILLS_TO_ADVANCE)
			goToNextLevel();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

}
