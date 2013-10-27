package RaceGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame f = new JFrame("Java RaceGame");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1100, 700);
		f.add(new Road());
		f.setVisible(true);

	}

}
