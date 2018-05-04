package packVista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;

import packModelo.Pong;

import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class F07Pong extends JFrame {

	private JPanel contentPane;
	private Canvas raquetaizqud;
	private Canvas raquetaderech;
	private String tipoJugador;

	/**
	 * Create the frame.
	 */
	public F07Pong(String tipoJugador) {
		this.tipoJugador = tipoJugador; //Jugador o IA
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 473);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		raquetaizqud = new Canvas();
		
		raquetaizqud.setBackground(Color.WHITE);
		raquetaizqud.setBounds(0, 78, 26, 110);
		contentPane.add(raquetaizqud);

		raquetaderech = new Canvas();
		
		raquetaderech.setBackground(Color.WHITE);
		raquetaderech.setBounds(690, 78, 26, 110);
		contentPane.add(raquetaderech);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GREEN);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(348, 0, 200, 444);
		contentPane.add(separator);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.scrollbar);
		canvas.setBounds(89, 106, 22, 22);
		contentPane.add(canvas);
		
		inicializarListeners();
		
		loopJuego();
	}
	
	private void inicializarListeners() {
		raquetaizqud.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int id = arg0.getKeyCode();

				if (id == KeyEvent.VK_W)
				{
					Pong.getPong().moverRaqueta(true, true);
				}
				else if (id == KeyEvent.VK_S)
				{
					Pong.getPong().moverRaqueta(false, true);
				}
				
			}
		});
		
		if(tipoJugador.contains("Jugador")) {
			raquetaderech.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					int id = e.getKeyCode();
					
					if (id == KeyEvent.VK_UP)
					{
						Pong.getPong().moverRaqueta(true, false);
						///jakñlsdfjlkasdflkasdkñfajdlfjalksdjfk

					}
					else if (id == KeyEvent.VK_DOWN)
					{
						Pong.getPong().moverRaqueta(false, false);
						//asdklfjñasdlkjflakdsjfadslkajsldkfjlsd
					}
				}
			});
		}
	}

	private void loopJuego() {
		//metodo mostrar elementos, obtener coordenadas de estos, y pintarlos en pantalla
		//metodo play que "juega"
		boolean terminado = false;
		while(!terminado) {
			terminado = Pong.getPong().jugar();
			JSONArray coordenadasElementos = Pong.getPong().mostrar();
			System.out.println(coordenadasElementos);
			//un metood para pintar todos los elementos desde lo anterior
		}
		
	}

}
