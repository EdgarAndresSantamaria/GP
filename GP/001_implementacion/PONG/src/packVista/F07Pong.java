package packVista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONException;

import packModelo.Pong;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyListener;

public class F07Pong extends JFrame {

	private TableroJuego juego;
	private Dimension screenSize;
	private JLabel label;

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
		juego.setBackground(Color.BLACK);
		label=new JLabel();
		add(juego);
		juego.loopJuego();

		if(!Pong.getPong().esInvitadoJugador1()) {
			JSONArray ranking = Pong.getPong().getRanking();
			if(ranking!=null) {
				try {
					new F06Ranking(ranking,true);
					dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

}
