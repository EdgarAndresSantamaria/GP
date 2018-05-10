package packVista;

import javax.swing.JFrame;


import org.json.JSONArray;
import org.json.JSONException;

import packModelo.Pong;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class F07Pong extends JFrame {

	private TableroJuego juego;
	private Dimension screenSize;
	

	/**
	 * Create the frame.
	 */
	public F07Pong(String pTipoJugador) {
		super("Pong TM");
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		setSize(screenSize.getSize());
		juego=new TableroJuego(getSize(),pTipoJugador);
		juego.setBackground(Color.BLACK);
		add(juego);
		loopJuego();

		if(!Pong.getPong().esInvitadoJugador1()) {
			JSONArray ranking = Pong.getPong().getRanking();
			if(ranking!=null) {
				try {
					new F06Ranking(ranking,true);
					dispose();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	public void loopJuego() {
		Boolean ganado=false;
		int quienGano=-1;
		while(!ganado) {
			try {
					Thread.sleep(1000/30);//30 fps
					//preparar siguiente frame
					quienGano=Pong.getPong().jugar();
					if(quienGano!=-1){
						ganado=true;
					}
					juego.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("vuelta");
			}

	}
}
