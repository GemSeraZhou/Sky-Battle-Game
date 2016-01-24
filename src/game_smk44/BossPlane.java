package game_smk44;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.ImageView;

public class BossPlane extends FighterPlane {
	

	private static final int PLANE_DIMENSIONS = 300;
	private static final int INITIAL_X_POS = Main.getScreenWidth() - PLANE_DIMENSIONS;
	private static final int MOVE_FREQ_IN_PATTERN = 5;
	private ArrayList<Integer> movePattern;
	private boolean isShielded;
	private int curMove;
	private int consecutiveMoves;
	private int currentFramesWithShield;
	
	public BossPlane() {
		initializeImage();
		initPattern();
		this.setLayoutX(INITIAL_X_POS);
		isShielded = false;
		consecutiveMoves = 0;
		speed = 10;
		curMove = 0;
		currentFramesWithShield = 0;
	}
	
	@Override
	protected void initializeImage() {
		imageView = new ImageView();
		imageView.setImage(ImageSetUp.getImageList().get(ImageSetUp.getBossPlane()));
		imageView.setFitHeight(PLANE_DIMENSIONS);
		imageView.setFitWidth(PLANE_DIMENSIONS);
		getChildren().add(imageView);
	}
	
	private void initPattern() {
		movePattern = new ArrayList<Integer>();
		for (int i = 0; i < MOVE_FREQ_IN_PATTERN; i++) {
			movePattern.add(speed);
			movePattern.add(-speed);
			movePattern.add(0);
		}
		Collections.shuffle(movePattern);
	}

	public void shufflePattern() {
		Collections.shuffle(movePattern);
	}
	
	public ArrayList<Integer> getMovePattern() {
		return movePattern;
	}

	public boolean isShielded() {
		return isShielded;
	}

	public void setShielded(boolean isShielded) {
		this.isShielded = isShielded;
	}

	public int getCurMove() {
		return curMove;
	}

	public void setCurMove(int curMove) {
		this.curMove = curMove;
	}
	
	public int getConsecMoves() {
		return consecutiveMoves;
	}
	
	public void setConsecMoves(int num) {
		consecutiveMoves = num;
	}
	
	public int getCurrentFramesWithShield() {
		return currentFramesWithShield;
	}
	
	public void setCurrentFramesWithShield(int num) {
		currentFramesWithShield = num;
	}
		
}

