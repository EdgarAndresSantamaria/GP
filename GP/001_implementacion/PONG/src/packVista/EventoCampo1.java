package packVista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import packModelo.Pong;

public class EventoCampo1 implements KeyListener{

	@Override	
	public void keyPressed(KeyEvent e){
		int id=e.getKeyCode();
		
		if (id == KeyEvent.VK_W)
		{
			Pong.getPong().moverRaqueta(false,true);	
		}
		else if (id == KeyEvent.VK_S)
		{
			Pong.getPong().moverRaqueta(true,true);	
		}		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		int id=e.getKeyCode();
		if (id == KeyEvent.VK_W || id == KeyEvent.VK_S)
		{	
			Pong.getPong().pararRaqueta(true);	
		}	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
