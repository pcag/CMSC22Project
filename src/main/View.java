package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class View {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 500;
	//public static final Image BG_IMAGE = new Image("images/menu_bg.jpg");
	public static final Color BG_COLOR = Color.MAGENTA;
	public static final Color GEN_COLOR = Color.BLACK;

	protected Stage stage;
	protected Scene scene;

	abstract protected Parent createRoot();

	public void loadTo(Stage stage) {
		Parent root = createRoot();
		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		this.stage = stage;

	}

	protected Text createText(String text, int size) {
		Text txt = new Text(text);
		txt.setFont(Font.font("Sans Serif", FontWeight.BOLD, size));
		txt.setStroke(Color.BLACK);
		txt.setStrokeWidth(1);
		txt.setFill(Color.CYAN);
		return txt;
	}


	protected Button createButton(String name) {
		Button btn = new Button(name);
		btn.setMinSize(220, 50);
		btn.setBorder(new Border(new BorderStroke(GEN_COLOR, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		btn.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		btn.setPadding(new Insets(8, 16, 8, 16));
		btn.setFont(Font.font("Sans Serif"));
		btn.setAlignment(Pos.CENTER);

		//animation
		DropShadow drop = new DropShadow(50, Color.CYAN);
		drop.setInput(new Glow());

//		btn.setOnMousePressed(event -> btn.setEffect(drop));
//		btn.setOnMouseReleased(event -> btn.setEffect(null));

		return btn;
	}
}