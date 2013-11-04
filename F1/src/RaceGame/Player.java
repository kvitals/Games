package RaceGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	public static final int MAX_V = 100; // max speed limit
	public static final int MAX_TOP = 60; // max verhniaya poziciya avto
	public static final int MAX_BOTTOM = 510; // max nizhniaya poziciya avto

	Image img_c = new ImageIcon(getClass().getClassLoader().getResource("res/player.png")).getImage();
	Image img_l = new ImageIcon(getClass().getClassLoader().getResource("res/player_left.png")).getImage();
	Image img_r = new ImageIcon(getClass().getClassLoader().getResource("res/player_right.png")).getImage();

	Image img = img_c;

	public Rectangle getRect() {
		return new Rectangle(x, y, 323, 90);
	}

	int v = 0; // start speed
	int dv = 0; // uskorenie
	int s = 0; // Distants

	int x = 30; // nachal'naya poziciya Playera
	int y = 100;
	int dy = 0;

	int layer1 = 0; // sloi dorogi
	int layer2 = 1200;

	public void move() {
		s += v;
		v += dv;
		if (v >= MAX_V) // ogranichenie skorosti
			v = MAX_V;
		if (v <= 0)
			v = 0;

		y -= dy;
		if (y <= MAX_TOP) // smeshenie vverh/vniz
			y = MAX_TOP;
		if (y >= MAX_BOTTOM)
			y = MAX_BOTTOM;

		if (layer2 - v <= 0) { // zaciklili dorogu
			layer1 = 0;
			layer2 = 1200;
		} else {
			layer1 -= v;
			layer2 -= v;
		}
	}

	public void keyPressed(KeyEvent e) { // deystviya pri naxhatii klavishy
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) { // stepen' uscoreniya
			dv = 1;
		}
		if (key == KeyEvent.VK_LEFT) { // stepen' tormozheniya
			dv = -1;
		}
		if (key == KeyEvent.VK_UP) { // stepen' povorotov
			dy = 8;
			img = img_l;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = -8;
			img = img_r;
		}
	}

	public void keyReleased(KeyEvent e) { // deystviya pri otpuskanii klavishy
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			dv = 0;
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
			img = img_c;
		}
	}
}
