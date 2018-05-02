package packVista;

import java.awt.Color;
import java.awt.Graphics;

public class F08Raqueta
{

	private int numRaqueta;

	private int x, y, ancho = 50, alto = 250;

	private int score;

	public F08Raqueta(F07Pong pong, int pNumRaqueta)
	{
		this.numRaqueta = pNumRaqueta;

		if (pNumRaqueta == 1)
		{
			this.x = 0;
		}

		if (pNumRaqueta == 2)
		{
			this.x = pong.getAnchura() - ancho;
		}

		this.y = pong.getAltura() / 2 - this.alto / 2;
	}

	public void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(getX(), y, ancho, alto);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
