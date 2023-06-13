package game;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SplashScreen extends Preloader{
	Stage stage;
	
	private Scene splashScreen() {
		BorderPane b = new BorderPane();
		b.setBackground(Background.EMPTY);
		b.setCenter(new ImageView(new Image("/images/splash.gif",800,500,true,false)));
		Scene s = new Scene (b,800,500);
		s.setFill(Color.WHITE);
		return s;
	}

	public void start(Stage stage) throws Exception{
		this.stage = stage;
		stage.setScene(splashScreen());
		stage.show();
	}
	
	public void handleStateChangeNotification (StateChangeNotification evt) {
		if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
			stage.hide();
		}
	}
}
