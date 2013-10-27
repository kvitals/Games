package RaceGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {

	Timer mainTimer = new Timer(20, this);

	Image img = new ImageIcon("res/bg_road.png").getImage();

	Player p = new Player();

	Thread enemiesFactory = new Thread(this);
	Thread audioThread = new Thread(new AudioThread());

	List<Enemy> enemies = new ArrayList<Enemy>();

	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);

	}

	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}

	}

	@Override
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null); // risuem dorogu
		g.drawImage(img, p.layer2, 0, null);
		g.drawImage(p.img, p.x, p.y, null); // risuem avto Playera

		double v = (200 / Player.MAX_V) * p.v; // sozdaem Speedometr
		g.setColor(Color.WHITE);
		Font font = new Font("Arial Bold", Font.ITALIC, 22);
		g.setFont(font);

		g.drawString("Speed = " + v + " km/h", 100, 655);

		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (e.x >= 2400 || e.x <= -2400) { // udalenie ob'ektov iz spiska
				i.remove();
			} else {
				g.drawImage(e.img, e.x, e.y, null);

			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) { // metod Timer mainTimer-a
		p.move(); // zapusk dvizheniya dorogi

		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy k = i.next();
			k.move();
		}
		repaint(); // perezapusk metoda "paint"
		// System.out.println(enemies.size()); //smotrim za kolichestvom enemies
		// v spiske
		testCollisionWithEnemy();
		testWin();

	}

	private void testWin() {
		if (p.s > 50000) {
			JOptionPane.showMessageDialog(null, "<< Вы выиграли!!!>>");
			System.exit(0);
		}

	}

	private void testCollisionWithEnemy() { // fiksiruem stolknovenie
											// pryamougolnikov
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (p.getRect().intersects(e.getRect())) {
				JOptionPane.showMessageDialog(null,
						"<< Произошло столкновение. Авто в ремонте.>>");
				System.exit(0);
			}

		}

	}

	@Override
	public void run() {
		while (true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy(1200, rand.nextInt(510),
						rand.nextInt(70), this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
