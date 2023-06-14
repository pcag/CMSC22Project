package main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import game.GameStage;


public class Menu extends View{
	private Image img = new Image("/images/Title.jpg");
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 500;
	
	@Override
	protected Parent createRoot() {
		StackPane root = new StackPane();
		root.setPrefSize(800, 500);
		root.setBackground(new Background(new BackgroundImage(img, null, null, null, null)));
		root.setAlignment(Pos.CENTER);

//		ImageView imgView = new ImageView(new Image("images/menu_bg.jpg", 1800, 1800, false, false));
//		imgView.setFitWidth(WINDOW_HEIGHT);
//		imgView.setFitHeight(WINDOW_HEIGHT);
//		imgView.setPreserveRatio(true);

		HBox content = createContent();

//		root.getChildren().addAll(imgView, content);
		root.getChildren().addAll(content);

		return root;
	}

	private HBox createContent() {
		HBox content = new HBox();
		content.setAlignment(Pos.BASELINE_LEFT);
		content.setSpacing(100);


		HBox buttons = createButtonContents();
		content.getChildren().addAll(buttons);
		content.setAlignment(Pos.CENTER);
		return content;
	}

	private HBox createButtonContents() {
		HBox buttonContents = new HBox();
		buttonContents.setAlignment(Pos.CENTER);
		buttonContents.setSpacing(8);


		Button newGameBtn = createButton("NEW GAME");
		newGameBtn.setPrefHeight(20);
		newGameBtn.setPrefWidth(50);
		newGameBtn.setTranslateX(50);
		newGameBtn.setTranslateY(100);
		newGameBtn.setOnMouseClicked(event -> {
			GameStage theGameStage = new GameStage();
			theGameStage.setStage(stage);
		});


		Button instructionsBtn = createButton("INSTRUCTIONS");
		instructionsBtn.setPrefHeight(10);
		instructionsBtn.setPrefWidth(20);
		instructionsBtn.setTranslateX(150);
		instructionsBtn.setTranslateY(100);
		instructionsBtn.setOnMouseClicked(event -> {
			Instructions instructionsTab = new Instructions();
			instructionsTab.loadTo(stage);
		});

		Button aboutBtn = createButton("ABOUT");
		aboutBtn.setPrefHeight(20);
		aboutBtn.setPrefWidth(50);
		aboutBtn.setTranslateX(250);
		aboutBtn.setTranslateY(100);
		aboutBtn.setOnMouseClicked(event -> {
			About aboutTab = new About();
			aboutTab.loadTo(stage);
		});

		Button exitBtn = createButton("EXIT");
		exitBtn.setPrefHeight(20);
		exitBtn.setPrefWidth(50);
		exitBtn.setTranslateX(350);
		exitBtn.setTranslateY(100);
		exitBtn.setOnMouseClicked(event -> {
			System.exit(0);
		});

		buttonContents.getChildren().addAll(newGameBtn, instructionsBtn, aboutBtn, exitBtn);
		return buttonContents;
	}

}

