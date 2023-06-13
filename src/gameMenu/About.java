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

public class About{

	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;
	private Canvas canvas2;
	private GraphicsContext gc2;
	private Stage stage;

	public static final Image img= new Image("/resources/pages/about.png");
	public static final Image img1= new Image("/resources/pages/aboutPage.png");
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;


	About(){				//to create AboutDeveloper scene
		this.pane = new StackPane();
		this.scene = new Scene(pane, About.WINDOW_WIDTH, About.WINDOW_HEIGHT,Color.BLACK);
		this.canvas = new Canvas(About.WINDOW_WIDTH, About.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.canvas2 = new Canvas(About.WINDOW_WIDTH, About.WINDOW_HEIGHT);
		this.gc2 = canvas.getGraphicsContext2D();
		this.setProperties(); //setting of the two GraphicContext


	}

	public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("About the Developers");
        this.stage.setScene(this.scene);
        this.stage.show();
    }

	private void setProperties(){
		//heading
		this.gc.drawImage(img, 0, 10, About.WINDOW_WIDTH,50); // header
		this.gc.setFill(Color.SNOW);

		//main content
		this.gc2.setFill(Color.BLACK);
		this.gc2.fillRect(0,70,About.WINDOW_WIDTH,About.WINDOW_HEIGHT);
		this.gc2.drawImage(img1, 0, 70, About.WINDOW_WIDTH,450);

		//buttons
		Button b1 = new Button ("Back");
		this.setMouseHandler(b1);
		b1.setTranslateX(10);
		b1.setTranslateY(160);
		b1.setStyle("-fx-font: 14 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");

		Button exitbtn = new Button("Exit");
		exitbtn.setTranslateX(10);
		exitbtn.setTranslateY(200);
		this.addEventHandler(exitbtn);
		exitbtn.setStyle("-fx-font: 14 Helvetica;"
        		+ " -fx-background-color: null;"
        		+ " -fx-text-fill: #FFFFFF;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");


		pane.getChildren().addAll(this.canvas, this.canvas2);	//add the two canvas
		pane.getChildren().add(b1);								//back button
		pane.getChildren().add(exitbtn);						//exit button
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
				System.exit(0); //when clicked, the system will exit // when exit button is clicked, the system will exit
			}
		});

	}

	//getters
	Scene getScene(){ //returning this scene from the SplashScreen
		return this.scene;
	}


}