package game_smk44;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class LoadScreen extends Parent {
	
	ImageView background;

	public LoadScreen() {
		background = new ImageView();
		background.setImage(ImageSetUp.getImageList().get(ImageSetUp.getLoadScreenBackground()));
		background.setFitHeight(Main.getScreenHeight());
		background.setFitWidth(Main.getScreenWidth());
	    background.setFocusTraversable(true);
		background.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Main.getMyController().launchGame();
            }
        });
	    background.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        public void handle(KeyEvent e) {
	        	Main.getMyController().launchGame();
	        }
	    });

	    Group group = new Group();
	    group.getChildren().add(background);
	    getChildren().add(group);
	}
    
	public void start() {
		background.requestFocus();
	}

}
