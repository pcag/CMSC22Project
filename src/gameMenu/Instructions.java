package gameMenu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Instructions {
	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;
	private Canvas canvas2;
	private GraphicsContext gc2;
	private Stage stage;
	private Image img= new Image("/resources/pages/Instructions.png");
	private Image img1= new Image("/resources/pages/instructionsPage.png");


	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;


	Instructions(){				//creates scene for instruction
		this.pane = new StackPane();
		this.scene = new Scene(pane, Instructions.WINDOW_WIDTH, Instructions.WINDOW_HEIGHT,Color.BLACK);
		this.canvas = new Canvas(Instructions.WINDOW_WIDTH, Instructions.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.canvas2 = new Canvas(Instructions.WINDOW_WIDTH, Instructions.WINDOW_HEIGHT);
		this.gc2 = canvas.getGraphicsContext2D();
		this.setProperties();


	}

	public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Instructions");
        this.stage.setScene(this.scene);
        this.stage.show();
    }

	private void setProperties(){
		//header
		this.gc.drawImage(img, 0, 10, Instructions.WINDOW_WIDTH,50); // header
		this.gc.setFill(Color.SNOW);										//set color to snow

		//main content
		this.gc2.setFill(Color.BLACK);										//set color to steelblue
		this.gc2.fillRect(0,70,Instructions.WINDOW_WIDTH,Instructions.WINDOW_HEIGHT);
		this.gc2.drawImage(img1, 0, 70, Instructions.WINDOW_WIDTH,450);


		//buttons
		Button b1 = new Button ("Back");
		this.setMouseHandler(b1);
		b1.setTranslateX(-40);
		b1.setTranslateY(200);
		b1.setStyle("-fx-font: 14 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");

		Button exitbtn = new Button("Exit"); //exit button
		exitbtn.setTranslateX(40);
		exitbtn.setTranslateY(200);
		this.addEventHandler(exitbtn);
		exitbtn.setStyle("-fx-font: 14 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");
		//pane.getChildren().add(instructions);
		pane.getChildren().addAll(this.canvas, this.canvas2);
		pane.getChildren().add(b1);
		pane.getChildren().add(exitbtn);
		}




	private void setMouseHandler(Button b1) {
		b1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				MainMenu menu = new MainMenu();
				menu.setStage(stage); //when clicked, it will go back to the splash screen
			}
		});

	}

	private void addEventHandler(Button btn) {
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				System.exit(0); //when clicked, the system will exit
			}
		});

	}

	//getters
	Scene getScene(){ //returning this scene from SplashScreen
		return this.scene;
	}

}