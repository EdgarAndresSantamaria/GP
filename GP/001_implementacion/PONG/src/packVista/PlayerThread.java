package packVista;

import javazoom.jl.player.jlp;

public class PlayerThread extends Thread{
	
	private jlp nReproductor;
	private static PlayerThread instancia;
	private Boolean conMusica;
	
	private PlayerThread() {
		String[] args=new String[1];
		args[0]="packMusica/musicaPong.mp3"; //nota este path como se vuelve generico??
		nReproductor=jlp.createInstance(args);
		conMusica=true;
	}
	
	public static PlayerThread getMusica() {
		if(instancia==null) {
			instancia=new PlayerThread();
		}
		return instancia;
	}
	
	public void pararMusica() {
		conMusica=false;
	}
	
	public void run() {
		while(conMusica) {	
				try {
					nReproductor.play();
					Thread.sleep(30000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		
	}

}
