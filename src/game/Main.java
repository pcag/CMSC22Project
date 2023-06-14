/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Mini-Project: Shooting Game
 * 
 * (c) Institute of Computer Science, CAS, UPLB
 * 
 * @author Paulene Joy M. Cagna-an
 * @date 2023-05-18 17:20
 *
 *************************************************************************************************************************/

package game;
	

import javafx.application.Application;
import javafx.stage.Stage;
import main.Menu;

public class Main extends Application {
	
	public void init() throws Exception{
		Thread.sleep(5000);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		Menu mainMenu = new Menu();
		mainMenu.loadTo(stage);
		stage.show();
	}
	
}
