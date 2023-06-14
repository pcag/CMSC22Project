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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import game.GameStage;


public class Menu extends View{
	private Image img = new Image("/images/Title.jpg");
	
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
		HBox.setMargin(content, null);
		HBox.setHgrow(content, Priority.ALWAYS);
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


		Button newGameBtn = new Button("NEW GAME");
		newGameBtn.setPrefHeight(50);
		newGameBtn.setPrefWidth(130);
		newGameBtn.setTranslateX(-80);
		newGameBtn.setTranslateY(100);
		newGameBtn.setStyle("-fx-background-color: #FFE4C4;" +
				 "-fx-font-size: 15;" +
				 "-fx-border-color: #800000;" +
				 "-fx-border-width: 3px;");
		newGameBtn.setOnMouseClicked(event -> {
			GameStage theGameStage = new GameStage();
			theGameStage.setStage(stage);
		});


		Button instructionsBtn = new Button("INSTRUCTIONS");
		instructionsBtn.setPrefHeight(50);
		instructionsBtn.setPrefWidth(130);
		instructionsBtn.setTranslateX(-30);
		instructionsBtn.setTranslateY(100);
		instructionsBtn.setStyle("-fx-background-color: #FFE4C4;" +
				 "-fx-font-size: 15;" +
				 "-fx-border-color: #800000;" +
				 "-fx-border-width: 3px;");
		instructionsBtn.setOnMouseClicked(event -> {
			Instructions instructionsTab = new Instructions();
			instructionsTab.loadTo(stage);
		});

		Button aboutBtn = new Button("ABOUT");
		aboutBtn.setPrefHeight(50);
		aboutBtn.setPrefWidth(130);
		aboutBtn.setTranslateX(20);
		aboutBtn.setTranslateY(100);
		aboutBtn.setStyle("-fx-background-color: #FFE4C4;" +
				 "-fx-font-size: 15;" +
				 "-fx-border-color: #800000;" +
				 "-fx-border-width: 3px;");
		aboutBtn.setOnMouseClicked(event -> {
			About aboutTab = new About();
			aboutTab.loadTo(stage);
		});

		Button exitBtn = new Button("EXIT");
		exitBtn.setPrefHeight(50);
		exitBtn.setPrefWidth(130);
		exitBtn.setTranslateX(70);
		exitBtn.setTranslateY(100);
		exitBtn.setStyle("-fx-background-color: #FFE4C4;" +
				 "-fx-font-size: 15;" +
				 "-fx-border-color: #800000;" +
				 "-fx-border-width: 3px;");
		exitBtn.setOnMouseClicked(event -> {
			System.exit(0);
		});

		buttonContents.getChildren().addAll(newGameBtn, instructionsBtn, aboutBtn, exitBtn);
		return buttonContents;
	}

}

