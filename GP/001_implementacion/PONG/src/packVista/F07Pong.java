package packVista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import packModelo.Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class F07Pong extends JFrame {

	private JPanel contentPane;
	private Canvas raquetaizqud;
	private Canvas raquetaderech;
	
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
		// Codigo para centrar el frame
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		setSize(screenSize.getSize());
		
		/**
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		 **/
		/**
		//crear campo
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		raquetaizqud = new Canvas();
		raquetaderech = new Canvas();	
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.scrollbar);
		contentPane.add(canvas);	
		**/
		inicializarListeners();	
		loopJuego();
	}
	
	private void inicializarListeners() {
		addKeyListener(new KeyAdapter() {
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
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					int id = e.getKeyCode();
					
					if (id == KeyEvent.VK_UP)
					{
						Pong.getPong().moverRaqueta(true, false);

					}
					else if (id == KeyEvent.VK_DOWN)
					{
						Pong.getPong().moverRaqueta(false, false);
					}
				}
			});
		}
	}

	private void loopJuego() {
		while(true) {
			try {
				Thread.sleep(8000);
				if(Pong.getPong().jugar()){//si terminado terminar ejecucion
					// fin
				}	
				repaint();
				System.out.println("vuelta");
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	public void update(Graphics g) {
		System.out.println("hola");
		paintComponents(g);
	}
	
	public void paintComponents( Graphics g) {
		super.paintComponents( g );
		System.out.println("painting....");
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.setBackground(Color.BLACK);
		JSONArray pLmostrar=Pong.getPong().mostrar();
		Iterator tmp=pLmostrar.iterator();
		while(tmp.hasNext()) {
			JSONObject sig=(JSONObject) tmp.next();
			if(sig.get("nombre").equals("raquetaIzqda")){
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("raquetaDrcha")) {
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("bola")) {
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("potenciador")) {
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("areaIzq")) {
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),0,10,screenSize.height));
			
			}else if(sig.get("nombre").equals("areaDrch")) {
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.draw( new Rectangle2D.Double((int)sig.get("x"),0,10,screenSize.height));
			
			}
			
			
		}
	}
}
