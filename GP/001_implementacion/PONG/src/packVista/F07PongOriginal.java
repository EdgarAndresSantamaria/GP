package packVista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Canvas;

public class F07PongOriginal implements ActionListener
{

	private static F07PongOriginal pong;

	private int ancho = 700, alto = 700;

	private Renderer renderer;

	private F08Raqueta player1;

	private F08Raqueta player2;

	private JFrame jframe;
	
	private F09Ball ball;

	public F07PongOriginal()
	{
//		Timer timer = new Timer(20, this);
	
		jframe = new JFrame("PONG TAEP");
		jframe.setResizable(false);

		renderer = new Renderer();

		jframe.setSize(ancho+15, alto);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().add(renderer);
		
		start();

//		timer.start();
				
	}

	public void start()
	{
		player1 = new F08Raqueta(this, 1);
		player2 = new F08Raqueta(this, 2);
		ball = new F09Ball(this);
	}

	public void update()
	{
	}

	public void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, ancho, alto);
		
		g.setColor(Color.GREEN);
		g.setStroke(new BasicStroke(5));
		g.drawLine(ancho/2, 0, ancho/2, alto);
		
		player1.render(g);
		player2.render(g);
		ball.render(g);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		update();
		renderer.repaint();
	}

	public int getAltura() 
	{
		return alto;
	}

	public int getAnchura() 
	{
		return ancho;
	}

	public static F07PongOriginal getPong() 
	{
		if(pong == null)
		{
			pong = new F07PongOriginal();
		}
		return pong;
	}
	
}
