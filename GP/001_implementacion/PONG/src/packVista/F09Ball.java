package packVista;

import java.awt.Color;
import java.awt.Graphics;

public class F09Ball 
{
	
	private int x,y,ancho=25,alto=25;

	
	public F09Ball(F07Pong pPong)
	{
		this.x = pPong.getAnchura()/2 - this.ancho/2;
		this.y = pPong.getAltura()/2 - this.alto/2;
	}

	public void update(F08Raqueta raqueta1, F08Raqueta raqueta2)
	{
		
	}
	
	

	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(x, y, ancho, alto);
		
	}
}
