package gameMenu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import game.*;

public class MainMenu {

	private Scene scene;
	private Stage stage;
	private ImageView imgView; //background
	private ImageView Title;
	private VBox vertBox;

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;

	//audio
	public final Media mp3MusicFile1 = new Media(getClass().getResource("/music/bg.mp3").toExternalForm());
	public final MediaPlayer musicplayer1 = new MediaPlayer(mp3MusicFile1);

	// the menuScreen serves as our "menu"
	public MainMenu() {
		StackPane root = new StackPane();
		this.scene = new Scene(root, MainMenu.WINDOW_WIDTH, MainMenu.WINDOW_HEIGHT);
		this.imgView = this.createView();


		this.vertBox = this.createVBox();

		//audio
		this.musicplayer1.setAutoPlay(true);
		this.musicplayer1.setVolume(0.3);

//		//loops the music all throughout the game
		musicplayer1.setOnEndOfMedia(new Runnable() {
			public void run() {
				musicplayer1.seek(Duration.ZERO);
			}
		});

		root.getChildren().addAll(this.imgView, this.vertBox);
	}

	private ImageView createView() {
		Image bg1 = new Image("/images/Title.jpg", 800, 500, true, true);
		ImageView view = new ImageView();
		view.setImage(bg1);
		return view;
	}

	//for title
//	private ImageView titleView() {
//		Image title = new Image("/images/Title.jpg", 400, 250, true, true);
//
//		ImageView viewTitle = new ImageView();
//		viewTitle.setImage(title);
//		return viewTitle;
//	}




    private VBox createVBox() {
    	VBox vbox = new VBox();
//    	this.Title= this.titleView(); //title screen


        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(8));
        vbox.setSpacing(8);


        Button b1 = new Button("New Game");
        Button b2 = new Button("Instructions");
        Button b3 = new Button("About");

        b1.setStyle("-fx-font: 16 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");
        b2.setStyle("-fx-font: 16 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");
        b3.setStyle("-fx-font: 16 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");

       vbox.getChildren().add(Title);

        vbox.getChildren().add(b1);
        vbox.getChildren().add(b2);
        vbox.getChildren().add(b3);

        this.setMouseHandler1(b1);			// Instead of placing here the entire target.setOnMethodName(new EventHandler<EventType>(){},
        this.setMouseHandler2(b2);
        this.setMouseHandler3(b3);			// we placed it in a method so we can reuse it

        return vbox;
    }

    public void setMouseHandler1(Button b1) { // for starting the new game
    	b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//audio
//           	musicplayer1.stop();
            	GameStage theGameStage = new GameStage();
        		theGameStage.setStage(stage);
                //System.out.println(b1.getText());
            }
        });
    }

    public void setMouseHandler2(Button b2) {//for instructions
    	b2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//            	musicplayer1.stop();
            	Instructions instructions = new Instructions();
            	instructions.setStage(stage);
                System.out.println(b2.getText());
            }
        });
    }

    public void setMouseHandler3(Button b3) {// for about developer
    	b3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
//            	musicplayer1.stop();
            	About about = new About();
            	about.setStage(stage);
                System.out.println(b3.getText());
            }
        });
    }

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		stage.getIcons().add(new Image("resources/icon.png"));
		this.stage.setTitle("Pacman Shooting Game");
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	//getters
	Scene getScene(){ //returning this scene from the SplashScreen
		return this.scene;
	}

}
