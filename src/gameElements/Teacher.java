package gameElements;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import game.GameStage;
import game.GameTimer;

public class Teacher extends Exam{

	Random rd = new Random();
	private int teacherHealth;
	private boolean moveUp;
	private ArrayList<Pencil> pencils;

	public static final int TEACHER_HEALTH = 3000;
	public static final int TEACHER_DAMAGE = 50;
	public final static int TEACHER_SIZE = 75;
	public final static int PENCIL_MAX = 4;
	public final static Image TEACHER_IMAGE = new Image("images/teacher.png",TEACHER_SIZE,TEACHER_SIZE,false,false);

	public Teacher(int x, int y) {
		super(x, y);
		this.loadImage(TEACHER_IMAGE);
		this.teacherHealth = TEACHER_HEALTH;
		this.pencils = new ArrayList<Pencil>();
		this.moveUp = rd.nextBoolean();
	}

	//method so that Teacher will move diagonally
	public void moveUp(){
		if(!this.moveUp && this.getY() + TEACHER_SIZE < GameStage.WINDOW_HEIGHT)
			this.y += this.getSpeed();
		else if(this.getY() + TEACHER_SIZE >= GameStage.WINDOW_HEIGHT){
			this.moveUp = true;
			this.y -= this.getSpeed();
		}
		else if (this.moveUp && this.getY() > GameTimer.PANEL_SPACE)
			this.y -= this.getSpeed();
		else if (this.getY() <= GameTimer.PANEL_SPACE){
			this.moveUp = false;
			this.y += this.getSpeed();
		}
	}

	public void shoot(){
		if(this.pencils.size() < PENCIL_MAX){
			int x = (int) (this.x + this.width+20);
			int y = (int) (this.y + this.height/2);
			Pencil p = new Pencil(x,y);
			this.pencils.add(p);
		}
    }

	//getters
	public int getTeacherHealth(){
		return this.teacherHealth;
	}

	public ArrayList<Pencil> getBullets(){
		return this.pencils;
	}

	//setters
	public void setTeacherHealth(int health){
		this.teacherHealth = health;
	}

}
