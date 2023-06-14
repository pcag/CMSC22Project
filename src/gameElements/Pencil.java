package gameElements;

import javafx.scene.image.Image;
import game.GameStage;
import game.Sprite;

public class Pencil extends Sprite {
	public final static Image PENCIL_IMAGE = new Image("images/pencil.png", Pencil.PENCIL_WIDTH, Pencil.PENCIL_WIDTH, false, false);
	private final static int PENCIL_SPEED = 20;
	public final static int PENCIL_DAMAGE = 20;
	public final static int PENCIL_WIDTH = 20;

	public Pencil(int x, int y) {
		super(x, y);
		this.loadImage(Pencil.PENCIL_IMAGE);
	}

	// method that will move/change the x position of the bullet
	public void moveRight(boolean right) {
		/*
		 * TODO: Change the x position of the bullet depending on the bullet speed. If
		 * the x position has reached the right boundary of the screen, set the bullet's
		 * visibility to false.
		 */
		if (right) {
			this.x += PENCIL_SPEED;
			if (this.getX() + PENCIL_WIDTH == GameStage.WINDOW_WIDTH) {
				this.setVisible(false);
			}
		} else {
			this.x -= PENCIL_SPEED;
			if (this.getX() + PENCIL_WIDTH == 0) {
				this.setVisible(false);
			}
		}
	}
}