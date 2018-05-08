package packVista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import packModelo.Pong;

public class EventoTeclado1  implements KeyListener{
	
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		System.err.println("key pressed....");
		if (id == KeyEvent.VK_UP)
		{
			System.err.println("up....");
			Pong.getPong().moverRaqueta(true, false);

		}
		else if (id == KeyEvent.VK_DOWN)
		{
			System.err.println("down....");
			Pong.getPong().moverRaqueta(false, false);
		}
	}
	
	public void keyReleased(KeyEvent arg0) {
		int id = arg0.getKeyCode();
		System.err.println("key released....");
		if (id == KeyEvent.VK_UP ||id == KeyEvent.VK_DOWN)
		{
			System.err.println("stop....");
			Pong.getPong().pararRaqueta(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
