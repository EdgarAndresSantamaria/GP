package packVista;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import packModelo.Pong;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.print.PrinterJob;
import java.util.Iterator;

public class F07Pong extends JFrame {

	private TableroJuego juego;
	private String tipoJugador;
	private Dimension screenSize;

	/**
	 * Create the frame.
	 */
	public F07Pong(String tipoJugador) {
		super("Pong TM");
		this.tipoJugador = tipoJugador; //Jugador o IA
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		setSize(screenSize.getSize());
		juego=new TableroJuego(getSize());
		add(juego);;
		inicializarListeners();	
		juego.loopJuego();
	}
	
	private void inicializarListeners() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int id = arg0.getKeyCode();
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
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				int id = arg0.getKeyCode();
				System.err.println("key released....");
				if (id == KeyEvent.VK_W ||id == KeyEvent.VK_S)
				{
					System.err.println("stop....");
					Pong.getPong().pararRaqueta(true);
				}
			}
		});
		
		if(tipoJugador.contains("Jugador")) {
			addKeyListener(new KeyAdapter() {
				@Override
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
				

				@Override
				public void keyReleased(KeyEvent arg0) {
					int id = arg0.getKeyCode();
					System.err.println("key released....");
					if (id == KeyEvent.VK_UP ||id == KeyEvent.VK_DOWN)
					{
						System.err.println("stop....");
						Pong.getPong().pararRaqueta(false);
					}
				}
			});
		}
	}

}
