package packVista;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;

public class F07Pong extends JFrame {

	private TableroJuego juego;
	private Dimension screenSize;

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
		juego=new TableroJuego(getSize());
		juego.setBackground(Color.DARK_GRAY);
		getContentPane().add(juego);
		juego.addKeyListener(new EventoTeclado());//eventos de jugador 1
		if(tipoJugador.contains("Jugador")) {
			juego.addKeyListener(new EventoTeclado1());//eventos de jugador 2
		}
		juego.loopJuego();
	}

}
