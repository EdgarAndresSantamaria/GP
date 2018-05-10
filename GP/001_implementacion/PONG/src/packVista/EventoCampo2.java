package packVista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import packModelo.Pong;

public class EventoCampo2 implements KeyListener{

	@Override	
	public void keyPressed(KeyEvent e){
		int id=e.getKeyCode();
		
		if (id == KeyEvent.VK_UP)
		{
			if(!Pong.getPong().tipoJugador2().contains("IA")) {
				Pong.getPong().moverRaqueta(false,false);	
			}

		}
		else if (id == KeyEvent.VK_DOWN)
		{
			if(!Pong.getPong().tipoJugador2().contains("IA")) {
				Pong.getPong().moverRaqueta(true,false);	
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int id=e.getKeyCode();
	
		if (id == KeyEvent.VK_UP || id == KeyEvent.VK_DOWN)
		{
			if(!Pong.getPong().tipoJugador2().contains("IA")) {
				Pong.getPong().pararRaqueta(false);	
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
