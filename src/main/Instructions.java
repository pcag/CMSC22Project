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

		Button backBtn =  createButton("BACK");
		backBtn.setTranslateX(40);
		backBtn.setTranslateY(100);
		backBtn.setStyle("-fx-font: 12 Times New Roman;"
        		+ " -fx-background-color: #FFE4C4;"
        		+ " -fx-text-fill: #000000;"
        		+ "-fx-border-color:#FFFAFA;"
        		+ " -fx-border-width: 2px;");

		backBtn.setOnMouseClicked(event -> {
			Menu mainMenu = new Menu();
			mainMenu.loadTo(stage);
		});

		root.getChildren().addAll(backBtn);

		return root;
	}




}

