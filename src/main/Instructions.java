package main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;


public class Instructions extends View {
	private Image bg = new Image("/images/Instructions.jpg");
	
	@Override
	protected Parent createRoot() {
		VBox root = new VBox();
		root.setBackground(new Background(new BackgroundImage(bg, null, null, null, null)));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(16);

		Button backBtn = new Button ("Back");
		backBtn.setPrefHeight(40);
		backBtn.setPrefWidth(100);
		backBtn.setStyle("-fx-background-color: #FFE4C4;" +
						 "-fx-border-color: #000000;" +
						 "-fx-border-width: 2px;");
		backBtn.setTranslateY(208);
//		b1.setTranslateX(-40);
//		b1.setTranslateY(200);
//		b1.setStyle("-fx-font: 14 Helvetica;"
//        		+ " -fx-background-color: null;"
//        		+ " -fx-text-fill: #FFFFFF;"
//        		+ "-fx-border-color:#FFFAFA;"
//        		+ " -fx-border-width: 2px;");
//		
		backBtn.setOnMouseClicked(event -> {
			Menu mainMenu = new Menu();
			mainMenu.loadTo(stage);
		});

		root.getChildren().addAll(backBtn);

		return root;
	}




}

