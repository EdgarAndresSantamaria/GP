package packVista;

import javax.swing.JFrame;


import org.json.JSONArray;
import org.json.JSONException;

import javazoom.jl.player.jlp;
import packModelo.Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class F07Pong extends JFrame  implements KeyListener , Runnable{

	private PlayerThread nReproductor;
	private TableroJuego juego;
	private Dimension screenSize;
	
	public static void main(String [] args) {
		Rectangle campo=new Rectangle(600,600);
		Pong.getPong().setConfig("yo", campo);
		Pong.getPong().setOponente("IA dificil");
		F07Pong miclase = new F07Pong(campo);
		Thread elHilo = new Thread(miclase);
		elHilo.start();
	}
	/**
	 * Create the frame.
	 * @param campo 
	 */
	public F07Pong(Rectangle campo) {
		super("Pong TM");
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		//setSize(screenSize.getSize());
		setSize(campo.width,campo.height);
		juego=new TableroJuego(getSize());
		juego.setBackground(Color.BLACK);
		add(juego);
		addKeyListener(this);
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
		else if (id == KeyEvent.VK_UP)
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
		if (id == KeyEvent.VK_W || id == KeyEvent.VK_S)
		{	
			Pong.getPong().pararRaqueta(true);	
		}
		else if (id == KeyEvent.VK_UP || id == KeyEvent.VK_DOWN)
		{
			if(!Pong.getPong().tipoJugador2().contains("IA")) {
				Pong.getPong().pararRaqueta(false);	
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void run() {
		nReproductor=new PlayerThread();
		nReproductor.start();
		loopJuego();
	}	
	
	
}
