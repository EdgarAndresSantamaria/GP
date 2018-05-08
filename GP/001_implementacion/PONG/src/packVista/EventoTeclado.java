package packVista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import packModelo.Pong;

public class EventoTeclado implements KeyListener{
		
	public void keyPressed(KeyEvent e){
		int id=e.getKeyCode();
		System.err.println("key pressed....");
		if (id == KeyEvent.VK_W)
		{
			System.err.println("up....");
			Pong.getPong().moverRaqueta(true, true);
		}
		else if (id == KeyEvent.VK_S)
		{
			System.err.println("down....");
			Pong.getPong().moverRaqueta(false, true);
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int id=e.getKeyCode();
		System.err.println("key released....");
		if (id == KeyEvent.VK_W ||id == KeyEvent.VK_S)
		{
			System.err.println("stop....");
			Pong.getPong().pararRaqueta(true);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}