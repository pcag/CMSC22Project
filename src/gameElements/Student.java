package gameElements;

import java.util.ArrayList;
import java.util.Random;

import game.GameStage;
import game.GameTimer;
import game.Sprite;
import javafx.scene.image.Image;

public class Student extends Sprite {
	private String name;
	private int strength;
	private int strengthMax;
	private boolean alive;

	private ArrayList<Pencil> pencils;
	public final static Image STUDENT_IMAGE = new Image("images/Student.gif", Student.STUDENT_WIDTH,
			Student.STUDENT_WIDTH, false, false);
	public final static int STUDENT_SIZE = 50;
	private final static int STUDENT_WIDTH = 50;
	public final static int PENCIL_MAX = 3;
	public final static int PENCIL_MAX_PU = 5;

	public Student(String name, int x, int y) {
		super(x, y);
		this.name = name;
		Random r = new Random();
		this.strength = r.nextInt(151) + 100;
		this.strengthMax = this.strength;
		this.alive = true;
		this.pencils = new ArrayList<Pencil>();
		this.loadImage(Student.STUDENT_IMAGE);
	}

	public boolean isAlive() {
		if (this.alive)
			return true;
		return false;
	}

	public String getName() {
		return this.name;
	}

	public void die() {
		this.alive = false;
	}

	// method that will get the pencils 'shot' by the student
	public ArrayList<Pencil> getPencils() {
		return this.pencils;
	}

	// method called if spacebar is pressed
	public void shoot(boolean pU) {
		// compute for the x and y initial position of the bullet
		int x = (int) (this.x + this.width + 20);
		int y = (int) (this.y + this.height / 2);
		/* TODO: Instantiate a new bullet and add it to the bullets arraylist of ship */

		Pencil p = new Pencil(x, y);
		this.pencils.add(p);
		p.moveRight(pU);
	}

	// method called if up/down/left/right arrow key is pressed.
	public void move() {
		/*
		 * TODO: Only change the x and y position of the ship if the current x,y
		 * position is within the gamestage width and height so that the ship won't exit
		 * the screen.
		 */
		int tempx = this.x + this.dx;
		int tempy = this.y + this.dy;

		if (tempx < 0 || tempx > (GameStage.WINDOW_WIDTH - STUDENT_SIZE)) {
			return;
		}
		this.x += this.dx;
		if (tempy <= 0 || tempy > (GameStage.WINDOW_HEIGHT - STUDENT_SIZE)) {
			return;
		}
		this.y += this.dy;
	}

	// getters
	public int getStrength() {
		return this.strength;
	}

	public int getStrengthMax() {
		return this.strengthMax;
	}

	// setter
	public void setStrength(int s) {
		this.strength = s;
	}

}
