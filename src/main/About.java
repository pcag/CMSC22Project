package main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;



public class About extends View{
	@Override
	protected Parent createRoot(){
		VBox root = new VBox();
//		root.setBackground(new Background(new BackgroundImage("/images/About.jpg", null, null, null, null)));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(16);

		Text title = createText("About", 48);

		Text dev = createText("Game Developer", 28);

		String info = "Clark Vince Diala\n" + " BS Tulog\n" +
		"University of the Philippines - Los Banos";

		Text myInfo =  createText(info, 24);
		myInfo.setTextAlignment(TextAlignment.CENTER);


		Button backBtn =  createButton("BACK");

		backBtn.setOnMouseClicked(event -> {
			Menu mainMenu = new Menu();
			mainMenu.loadTo(stage);
		});

		root.getChildren().addAll(title, dev, myInfo, backBtn);

		return root;
	}


}
