package gameElements;

import java.util.Random;

import javafx.scene.image.Image;
import game.Sprite;

public class Powerups extends Sprite {

	Random rd = new Random();
	private int powerupClass;
	private int spawnTime;

	public final static int PU_SIZE = 30;
	public final static Image HINT = new Image("/images/hint.png", PU_SIZE, PU_SIZE, false, false);
	public final static Image INCENTIVE = new Image("images/incentive.png", PU_SIZE, PU_SIZE, false, false);
	public final static Image RECITE = new Image("images/recite.png", PU_SIZE, PU_SIZE, false, false);

	public Powerups(int x, int y, int time) {
		super(x, y);
		this.spawnTime = time;
		this.powerupClass = rd.nextInt(3) + 1;
		switch (this.powerupClass) {
		case 1:
			this.loadImage(HINT);
			break;
		case 2:
			this.loadImage(INCENTIVE);
			break;
		case 3:
			this.loadImage(RECITE);
			break;
		}
	}

	// getters
	public int getPowerupClass() {
		return this.powerupClass;
	}

	public int getSpawnTime() {
		return this.spawnTime;
	}

}
