package packVista;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.jlp;
import packModelo.Pong;

public class PlayerThread extends Thread{
	
	private jlp nReproductor;
	
	public PlayerThread() {
		String[] args=new String[1];
		args[0]="/home/edgar/Música/musicaPong.mp3"; //nota este path como se vuelve generico??
		nReproductor=jlp.createInstance(args);
	}
	
	public void run() {
		Boolean fin=false;
		while(!fin) {
			try {
				fin=Pong.getPong().playMusic();
				fin=true;
			} catch (Exception e) {	
				try {
					nReproductor.play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
