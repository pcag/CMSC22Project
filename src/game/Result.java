package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Result {

	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;
	private GameTimer gameTimer;
	private int gameOverNum;

	public final  Media mp3MusicFile = new Media(getClass().getResource("/resources/music/win.mp3").toExternalForm());
	public final MediaPlayer musicplayer = new MediaPlayer(mp3MusicFile);
	public final  Media mp3MusicFile1 = new Media(getClass().getResource("/resources/music/lose.mp3").toExternalForm());
	public final MediaPlayer musicplayer1 = new MediaPlayer(mp3MusicFile1);
	public static final Image img1= new Image("/resources/pages/losePage.png");
	public static final Image img2= new Image("/resources/pages/winPage.png");

	Result(int gameOverNum, GameTimer gameTimer){
		this.pane = new StackPane();
		this.scene = new Scene(pane, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, Color.BLACK);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gameOverNum = gameOverNum;
		this.gameTimer = gameTimer;
		this.setProperties();
	}

	private void setProperties(){
		Font theFont = Font.font("Helvetica",FontWeight.EXTRA_BOLD,30);//set font type, style and size
		this.gc.setFont(theFont);
		this.gc.setFill(Color.SNOW);

		if (this.gameOverNum == 0){
			//for audio
			this.musicplayer1.setAutoPlay(true);
			this.musicplayer1.setVolume(0.9);
			musicplayer1.setOnEndOfMedia(new Runnable() {
			    public void run() {
			    musicplayer1.seek(Duration.ZERO);
			   }
			     });

			// display
			this.gc.drawImage(img1, 0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT); // losePage
			this.gc.fillText("Score: " + gameTimer.getScore(), GameStage.WINDOW_WIDTH*.01,GameStage.WINDOW_HEIGHT*0.05);
		}
		else if (this.gameOverNum == 1){
			this.musicplayer.setAutoPlay(true); //for audio
			this.musicplayer.setVolume(0.9);

			//loops the music all throughout the game
			    musicplayer.setOnEndOfMedia(new Runnable() {
			    public void run() {
			    musicplayer.seek(Duration.ZERO);
			   }
			     });
			this.gc.drawImage(img2, 0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT); // winPage
			this.gc.fillText("Score: " + gameTimer.getScore(), GameStage.WINDOW_WIDTH*.01,GameStage.WINDOW_HEIGHT*0.05);
		}

		Button exitbtn = new Button("Exit Game");
		exitbtn.setTranslateX(10);
		exitbtn.setTranslateY(200);
		this.addEventHandler(exitbtn);
		exitbtn.setStyle("-fx-font: 14 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");


		pane.getChildren().add(this.canvas);
		pane.getChildren().add(exitbtn);
	}


	private void addEventHandler(Button btn) {
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});

	}

	//getters
	Scene getScene(){
		return this.scene;
	}
}
