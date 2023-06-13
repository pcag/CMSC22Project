package main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import game.GameStage;


public class Menu extends View{

	@Override
	protected Parent createRoot() {
		StackPane root = new StackPane();
		root.setPrefSize(800, 800);
		//root.setBackground(new Background(new BackgroundImage(BG_IMAGE, null, null, null, null)));
		root.setAlignment(Pos.CENTER);

//		ImageView imgView = new ImageView(new Image("images/menu_bg.jpg", 1800, 1800, false, false));
//		imgView.setFitWidth(WINDOW_HEIGHT);
//		imgView.setFitHeight(WINDOW_HEIGHT);
//		imgView.setPreserveRatio(true);

		VBox content = createContent();

//		root.getChildren().addAll(imgView, content);
		root.getChildren().addAll(content);

		return root;
	}

	private VBox createContent() {
		VBox content = new VBox();
		content.setAlignment(Pos.BASELINE_LEFT);
		content.setSpacing(100);

		StackPane s = new StackPane();
		Text title = createText("IDOL MO SI VINS", 70);
		Rectangle bg = new Rectangle(800,70);
		bg.setOpacity(0.6);
		bg.setFill(Color.BLACK);
		bg.setEffect(new GaussianBlur(1.5));
		s.setAlignment(Pos.CENTER);
		s.getChildren().addAll(bg, title);


		VBox buttons = createButtonContents();
		content.getChildren().addAll(s, buttons);
		content.setAlignment(Pos.CENTER);
		return content;
	}

	private VBox createButtonContents() {
		VBox buttonContents = new VBox();
		buttonContents.setAlignment(Pos.CENTER);
		buttonContents.setSpacing(8);


		Button newGameBtn = createButton("NEW GAME");
		newGameBtn.setOnMouseClicked(event -> {
			GameStage theGameStage = new GameStage();
			theGameStage.setStage(stage);
		});


		Button instructionsBtn = createButton("INSTRUCTIONS");
		instructionsBtn.setOnMouseClicked(event -> {
			Instructions instructionsTab = new Instructions();
			instructionsTab.loadTo(stage);
		});

		Button aboutBtn = createButton("ABOUT");
		aboutBtn.setOnMouseClicked(event -> {
			About aboutTab = new About();
			aboutTab.loadTo(stage);
		});

		Button exitBtn = createButton("EXIT");
		exitBtn.setOnMouseClicked(event -> {
			System.exit(0);
		});

		buttonContents.getChildren().addAll(newGameBtn, instructionsBtn, aboutBtn, exitBtn);
		return buttonContents;
	}

}

