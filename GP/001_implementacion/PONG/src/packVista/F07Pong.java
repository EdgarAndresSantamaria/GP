package packVista;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
<<<<<<< Updated upstream
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.print.PrinterJob;
import java.util.Iterator;
import javax.swing.JButton;
import java.awt.Canvas;
=======
>>>>>>> Stashed changes

public class F07Pong extends JFrame {

	private TableroJuego juego;
	private Dimension screenSize;
	private Canvas canvas;

	/**
	 * Create the frame.
	 */
	public F07Pong(String tipoJugador) {
		super("Pong TM");
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		setSize(screenSize.getSize());
		getContentPane().setLayout(null);
		
		canvas = new Canvas();
		canvas.setBounds(0, 64, 100, 100);
		getContentPane().add(canvas);
		
		juego=new TableroJuego(getSize());
<<<<<<< Updated upstream
		juego.setBackground(Color.BLACK);
		juego.setBounds(0, 0, 1360, 739);
		getContentPane().add(juego);
		inicializarListeners();	
		juego.loopJuego();
	}
	
	private void inicializarListeners() {
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int id = arg0.getKeyCode();
				System.err.println("key pressed....");
				if (id == KeyEvent.VK_W)
				{
					System.err.println("up....");
					canvas.setLocation(canvas.getX(), canvas.getY()-1);
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
		
=======
		add(juego);
		juego.addKeyListener(new EventoTeclado());//eventos de jugador 1
>>>>>>> Stashed changes
		if(tipoJugador.contains("Jugador")) {
			juego.addKeyListener(new EventoTeclado1());//eventos de jugador 2
		}
		juego.loopJuego();
	}
}
