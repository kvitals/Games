package RaceGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	public Enemy() {
	}

	int x;
	int y;
	int v;
	Road road;
	Image img = new ImageIcon(getClass().getClassLoader().getResource("res/enemy.png")).getImage();

	public Rectangle getRect() {
		return new Rectangle(x, y, 330, 90);
	}

	public Enemy(int x, int y, int v, Road road) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.road = road;
	}

	public void move() {
		x = x - road.p.v + v;
	}

}
