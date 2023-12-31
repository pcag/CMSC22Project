package game;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {
    public static final int WINDOW_HEIGHT = 500;
    public static final int WINDOW_WIDTH = 800;
    private Scene scene;
    private Stage stage;
    private Group root;
    private Canvas canvas;
    private GraphicsContext gc;
    private GameTimer gametimer;

    // the class constructor
    public GameStage() {
        this.root = new Group();
        this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, Color.CADETBLUE);
        this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        // instantiate an animation timer
        this.gametimer = new GameTimer(this.gc, this.scene, this);
    }

    // method to add the stage elements
    public void setStage(Stage stage) {
        this.stage = stage;

        // Set stage elements here
        this.root.getChildren().add(canvas);

        this.stage.setTitle("Mini Ship Shooting Game");
        this.stage.setScene(this.scene);
        
        

        // invoke the start method of the animation timer
        this.gametimer.start();

        this.stage.show();
    }

    // method to call gameOver screen
    public void gameOver(int num, GameTimer gameTimer) {
        // musicplayer.stop();
        PauseTransition transition = new PauseTransition(Duration.seconds(0.3));
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent arg0) {
                Result gameover = new Result(num, gameTimer);
                stage.setScene(gameover.getScene());
            }
        });
    }
}
