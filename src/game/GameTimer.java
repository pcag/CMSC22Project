package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import gameElements.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method. 
 */
 
public class GameTimer extends AnimationTimer{
	
	private GraphicsContext gc;
	private Scene theScene;
	private Student student;
	private GameStage gameStage;
	private ArrayList<Exam> exams;
	private int examNum;
	private ArrayList<Powerups> powerups;
	private int powerupClass;
	private int powerupTime;
	private ArrayList<Teacher> finalBoss;
	private Timer timer;
	private int time;
	private int activePowerUp = 0;
	private Boolean invincible = false;
	private boolean activatePowerTimer = false;
	public static final Image gameTimer = new Image("images/timer.gif");
	public static final Image score = new Image("images/score.gif");
	public static final Image health = new Image("images/health.png");
	
	public static final int MAX_NUM_EXAMS = 7;
	public static final int SPAWN_EXAMS = 3;
	public static final int PANEL_SPACE = 30;
	public static final int STUDENT_INITIAL_X = 150;
	public static final int STUDENT_INITIAL_Y = 250;
	public static final int EXAM_DAMAGE = 20;
	
	
	GameTimer(GraphicsContext gc, Scene theScene, GameStage gameStage){
		this.gc = gc;
		this.theScene = theScene;
		this.gameStage = gameStage;
		
		this.student = new Student("Student",STUDENT_INITIAL_X,STUDENT_INITIAL_Y);
		//instantiate the ArrayList of Fish
		this.exams = new ArrayList<Exam>();
		//instantiate the ArrayList of Powerups
		this.powerups = new ArrayList<Powerups>();
		//instantiate the ArrayList of final boss
		this.finalBoss = new ArrayList<Teacher>();
		
		//call the spawnExams method
		this.spawnExams();
		//call method to handle mouse click event
		this.handleKeyPressEvent();
		//call method for timer
		this.timer1();
		//starts timer
		this.timer.start();
	}

	@Override
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

		this.student.move();
		/* TODO: Call the moveBullets and moveFishes methods. */
		this.moveExams();
		this.movePencils();
		this.moveFinalBoss();
//		this.movePencilsBoss();
		
		this.student.render(this.gc);
		/* TODO: Call the renderFishes and renderBullets methods. */
		this.renderPowerups();
		this.renderExams();
		this.renderPencils();
		this.renderFinalBoss();
//		this.renderPencilsBoss();
		
		this.despawnPowerups();
		
		this.collisionExams();
		this.collisionPowerups();
		this.collisionPencils();
		this.collisionFinalBoss();
		this.displayStat();
	}
	
	//method that will render/draw the exams to the canvas
	private void renderExams() {
		for (Exam e : this.exams){
			e.render(this.gc);
		}
	}
	
	//method that will render/draw the pencils to the canvas
	private void renderPencils() {
		/* TODO: Loop through the bullets arraylist of student and render each bullet to the canvas. */
		for(Pencil p: this.student.getPencils()) {
			p.render(this.gc);
		}
	}
	
	//method that will render/draw the powerups to the canvas
	private void renderPowerups() {
		for (Powerups p: this.powerups) {
			p.render(this.gc);
		}
	}

	//method that will render/draw the final boss to the canvas
	private void renderFinalBoss() {
		for(Teacher fb:this.finalBoss) {
			fb.render(this.gc);
		}
	}

	//method that will call back/despawn the powerups
	private void despawnPowerups() {
		for(int i=0; i < this.powerups.size(); i++) {
			Powerups p = powerups.get(i);
			
			if(!p.visible) {
				powerups.remove(i);
			}
			if((this.time - p.getSpawnTime()) >= 5) {
				powerups.remove(i);
			}
		}
	}

	//method that will call/spawn the powerups
	private void spawnPowerups() {
		Random r = new Random();
		if(this.time % 10 == 0) {
			int x = r.nextInt((GameStage.WINDOW_WIDTH/2) - Powerups.PU_SIZE);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT - (Powerups.PU_SIZE + PANEL_SPACE) + PANEL_SPACE);
			Powerups p = new Powerups(x,y,this.time);
			this.powerups.add(p);
		}
	}
	
	//method that will call/spawn the exams
	private void spawnExams() {
		Random r = new Random();
		if(this.time < 5) {
			for(int i=0; i < GameTimer.MAX_NUM_EXAMS; i++) {
				int x = r.nextInt(GameStage.WINDOW_WIDTH - Exam.EXAM_SIZE);
				int y = r.nextInt(GameStage.WINDOW_HEIGHT - (PANEL_SPACE + Exam.EXAM_SIZE) + PANEL_SPACE);
				Exam e = new Exam(x,y);
				this.exams.add(e);
			}
		}
		else if(this.time % 5 == 0) {
			for(int i=0; i < GameTimer.SPAWN_EXAMS; i++) {
				int x = r.nextInt(GameStage.WINDOW_WIDTH - Exam.EXAM_SIZE);
				int y = r.nextInt(GameStage.WINDOW_HEIGHT - (PANEL_SPACE + Exam.EXAM_SIZE) + PANEL_SPACE);
				Exam e = new Exam(x,y);
				this.exams.add(e);
			}
		}
	}

	//method that will call/spawn the boss
	private void spawnFinalBoss() {
		Random r = new Random();
		if(this.time == 30) {
			int x = r.nextInt((GameStage.WINDOW_WIDTH/2) - Teacher.TEACHER_SIZE);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT - (Teacher.TEACHER_SIZE + PANEL_SPACE) + PANEL_SPACE);
			Teacher fb = new Teacher(x,y);
			this.finalBoss.add(fb);
		}
	}
	
	//method that will move the exams
	private void moveExams(){
		//Loop through the exams arraylist
		for(int i = 0; i < this.exams.size(); i++){
			Exam e = this.exams.get(i);
			/* TODO:  *If a fish is alive, move the fish. Else, remove the fish from the fishes arraylist. */
			if(e.isAlive())
				e.move();
			else
				this.exams.remove(i);
		}
	}
	
	//method that will move the pencils
	private void movePencils() {
		ArrayList<Pencil> pList = this.student.getPencils();
		
		for(int i=0; i < pList.size();i++) {
			Pencil p = pList.get(i);
			if(p.visible)
				p.moveRight(true);
			else
				pList.remove(i);
		}
	}
	
	//method that will move the boss(Teaacher)
	private void moveFinalBoss() {
		for(int i=0; i<this.finalBoss.size(); i++) {
			Teacher fb = this.finalBoss.get(i);
			if(fb.isAlive()) {
				fb.move();
				fb.moveUp(); 		
			}
			else {
				this.finalBoss.remove(i);
			}
		}
	}
	
	//method that will move the ship depending on the key pressed
	private void moveStudent(KeyCode ke) {		
		if(ke==KeyCode.UP) this.student.setDY(-3);   
		
		if(ke==KeyCode.LEFT) this.student.setDX(-3);
		
		if(ke==KeyCode.DOWN) this.student.setDY(3);
		
		if(ke==KeyCode.RIGHT) this.student.setDX(3);
		
		if(this.powerupClass == 3 && this.time <= (this.powerupTime + 5)){
			if(ke==KeyCode.SPACE) this.student.shoot(true);	
		}
		else 
			if(ke==KeyCode.SPACE) this.student.shoot(false);
		
		//System.out.println(ke+" key pressed.");
   	}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopStudent(KeyCode ke){
		this.student.setDX(0);
		this.student.setDY(0);
	}
	
	//method that will call for collision of elements with pencil
	private void collisionPencils() {
		if(this.finalBoss.size() == 1) {
			for(Pencil p: this.finalBoss.get(0).getBullets()) {
				if(p.collidesWith(student)) {
					student.setStrength(student.getStrength() - Pencil.PENCIL_DAMAGE);
					System.out.println("Health: " + this.student.getStrength());
					p.setVisible(false);
				}
				if (p.x == GameStage.WINDOW_WIDTH || p.x == 0) {
					p.setVisible(false);
				}
			}
		}
	}
	
	//method that will call for collision of exams to student
	private void collisionExams() {
		for(Exam e:this.exams) {
			for(Pencil p:this.student.getPencils()) {
				if(e.collidesWith(p)) {
					this.examNum++;
					e.setAlive(false);
					p.setVisible(false);
				}

				for(Teacher fb:this.finalBoss) {
					if(fb.collidesWith(e)) {
						fb.setTeacherHealth(fb.getTeacherHealth() - this.student.getStrength());
						e.setVisible(false);
						if(fb.getTeacherHealth() <= 0) {
							fb.setAlive(false);
							this.timer.stop();
							stop();
							gameStage.gameOver(1,this);
						}
					}
				}
			}
	
			if(e.collidesWith(this.student) && !this.invincible) {
				if(this.powerupClass == 2 & (this.time <= this.powerupTime + 3)){
					this.examNum++;
					e.setAlive(false);
					continue;
				}
				this.student.setStrength(this.student.getStrength() - Exam.EXAM_DAMAGE);
				System.out.println("Health: " + this.student.getStrength());
				this.examNum++;
				e.setAlive(false);
				if(this.student.getStrength() <= 0) {
					this.timer.stop();
					stop();
					gameStage.gameOver(0, this);
				}
			}
		}
	}
	
	//method that will call for collision of powerups to student
	private void collisionPowerups() {
		for(Powerups p:this.powerups) {
			if(p.collidesWith(this.student)) {
				p.setVisible(false);
				this.powerupClass = p.getPowerupClass();
				if(this.powerupClass == 1) {
					this.student.setStrength(this.student.getStrength() + 50);
					System.out.println("Health: " + this.student.getStrength());
				}
				else if(this.powerupClass == 2) {
					System.out.println("INVISIBILITY ACTIVATED FOR 3 SECONDS");
					this.invincible = true;
					this.activePowerUp = 2;
					this.activatePowerTimer  = true;
				}
				else {
					System.out.println("ATTACK SPEED INCREASED FOR 5 SECONDS");
				}
			}
		}
	}
	
	private void removePowerUpEffect() {
		this.invincible = false;
		this.activePowerUp = 0;
	}
	
	//method that will call for collision with final boss
	private void collisionFinalBoss() {
		for(Teacher fb:this.finalBoss) {
			if(fb.collidesWith(this.student)) {
				if(this.powerupClass == 2 & (this.time <= (this.powerupTime + 3))) {
					continue;
				}
				this.student.setStrength(this.student.getStrength() - Teacher.TEACHER_DAMAGE);
				System.out.println("Health: " + this.student.getStrength());
				if(this.student.getStrength() <= 0) {
					this.timer.stop();
					stop();
					gameStage.gameOver(0, this);
				}
			}
		}
	}
	
	private void displayStat(){
		Font theFont = Font.font("Helvetica",FontWeight.EXTRA_BOLD,15);//set font type, style and size
		this.gc.setFont(theFont);
		this.gc.setFill(Color.SNOW);
//		this.gc.drawImage(score, 20, 10, 20, 20);
//		this.gc.fillText("SCORE: " + this.student, GameStage.WINDOW_WIDTH*0.05, GameStage.WINDOW_HEIGHT*0.05);
		this.gc.drawImage(health, 200, 10, 20, 20);
		this.gc.fillText("STRENGTH: " + student.getStrength(), GameStage.WINDOW_WIDTH*0.28, GameStage.WINDOW_HEIGHT*0.05);
		this.gc.drawImage(gameTimer, 370, 10, 20, 20);
		this.gc.fillText("TIME: " + this.time, GameStage.WINDOW_WIDTH*0.5, GameStage.WINDOW_HEIGHT*0.05);
		if(this.finalBoss.size() == 1){
			this.gc.fillText("BOSS STRENGTH: " + this.finalBoss.get(0).getTeacherHealth(), GameStage.WINDOW_WIDTH*0.6, GameStage.WINDOW_HEIGHT*0.05);
		}
	}
	
	//winning condition
	private void winCondition() {
		if(this.time == 60) {
			stop();
			this.timer.stop();
			gameStage.gameOver(1, this);
		}
	}
	
	//method that will display status on the screen
//	private void displayStats() {
//	
//	}
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveStudent(code);
			}
		});
		
		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopStudent(code);
		            }
		        });
    }
	
	//method that will create a timer
	private void timer1() {
		this.timer = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				time++;
				System.out.println(time);
				
				if (activatePowerTimer) {
					powerupTime = time;
					activatePowerTimer = false;
				}
				
				if (activePowerUp != 0) {
					switch (activePowerUp) {
					case 2:
						if (time - powerupTime >= 3) {
							removePowerUpEffect();
							System.out.println("REMOVED INVINCIBILITY");
						}
					}
				}
				
				if(time % 5 == 0) {
					spawnExams();
				}
				
				spawnPowerups();
				spawnFinalBoss();
				winCondition();
				if(finalBoss.size() == 1)
					finalBoss.get(0).shoot();
			}
		});
	}
	
	//getter
	public int getScore() {
		return this.examNum;
	}
}