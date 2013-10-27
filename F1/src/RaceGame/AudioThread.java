package RaceGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {

	@Override
	public void run() {
		try {
			Player p = new Player(new FileInputStream("res/song.mp3"));
			p.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}

	}

}
