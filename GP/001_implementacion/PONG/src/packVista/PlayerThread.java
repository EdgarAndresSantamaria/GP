package packVista;

import javazoom.jl.player.jlp;

public class PlayerThread extends Thread{
	
	private jlp nReproductor;
	
	public PlayerThread() {
		String[] args=new String[1];
		args[0]="/home/edgar/Música/musicaPong.mp3"; //nota este path como se vuelve generico??
		nReproductor=jlp.createInstance(args);
	}
	
	public void run() {
		while(true) {	
				try {
					nReproductor.play();
					Thread.sleep(30000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		
	}

}
