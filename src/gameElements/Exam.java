package gameElements;
import java.util.Random;
import javafx.scene.image.Image;
import game.GameStage;
import game.Sprite;

public class Exam extends Sprite {
	Random rd = new Random();
	public static final int MAX_EXAM_SPEED = 5;
	public static final int EXAM_DAMAGE = 30;
	public final static Image EXAM_IMAGE = new Image("images/exam.png",Exam.EXAM_WIDTH,Exam.EXAM_WIDTH,false,false);
	public final static int EXAM_WIDTH=50;
	public final static int EXAM_SIZE = 50;
	private boolean alive;
	//attribute that will determine if a fish will initially move to the right
	private boolean moveRight;
	private int speed;

	
	public Exam(int x, int y){
		super(x,y);
		this.alive = true;
		this.loadImage(Exam.EXAM_IMAGE);
		/* TODO: Randomize speed of fish and moveRight's initial value. */
		this.speed = rd.nextInt(4)+1;
		this.moveRight = rd.nextBoolean();
	}
	
	//method that changes the x position of the fish
	public void move(){
		/*
		 * TODO: 				If moveRight is true and if the fish hasn't reached the right boundary yet,
		 *    						move the fish to the right by changing the x position of the fish depending on its speed
		 *    					else if it has reached the boundary, change the moveRight value / move to the left 
		 * 					 Else, if moveRight is false and if the fish hasn't reached the left boundary yet,
		 * 	 						move the fish to the left by changing the x position of the fish depending on its speed.
		 * 						else if it has reached the boundary, change the moveRight value / move to the right
		 */
		if (this.moveRight && this.getX() + EXAM_SIZE < GameStage.WINDOW_WIDTH)
			this.x += this.speed;
		else if (this.getX() + EXAM_SIZE >= GameStage.WINDOW_WIDTH) {
			this.moveRight = false;
			this.x -= this.speed;
		}
		else if (!this.moveRight && this.getX() > 0)
			this.x += this.speed;
		else if (this.getX() <= 0) {
			this.moveRight = true;
			this.x += this.speed;
		}
	}
	
	//getters
	public boolean isAlive() {
		return this.alive;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	//setter
	public void setAlive(boolean t) {
		this.alive = t;
	}
	
	
}
