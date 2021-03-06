package packVista;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import packModelo.Pong;

public class TableroJuego extends Canvas   {	
	
	
	private String tipoJugador;
	
	public TableroJuego(Dimension dimension) {
		setVisible(true);
		setSize(dimension);	
		tipoJugador=Pong.getPong().tipoJugador2();
	}
	

	public void repaint() {
			try {
					createBufferStrategy(2);
					BufferStrategy bs=getBufferStrategy();
					do {
						do {
							Graphics g= bs.getDrawGraphics();
							dibujar(g);
							g.dispose();
						}while(bs.contentsRestored());
						bs.show();
					}while(bs.contentsLost());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}


	public void dibujar( Graphics g) {
		//System.out.println("painting component....");
		Graphics2D g2d = ( Graphics2D ) g;
		JSONArray pLmostrar=Pong.getPong().mostrar();
		//System.out.println("JSON: ...."+pLmostrar);
		Iterator<?> tmp=pLmostrar.iterator();
		while(tmp.hasNext()) {
			JSONObject sig=(JSONObject) tmp.next();
			if(sig.get("nombre").equals("raquetaIzqda")){
				//System.out.println("		 raquetaizq....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("raquetaDrcha")) {
				//System.out.println("		 raquetadrch....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("bola")) {
				//System.out.println("		 bola....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			}else if(sig.get("nombre").equals("multiplicador")) {
				//System.out.println("		 potenciador....");
				g2d.setPaint(Color.BLUE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("dyRaqueta")) {
				//System.out.println("		 potenciador....");
				g2d.setPaint(Color.RED);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("areaIzq")) {
				//System.out.println("		 area....");
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),0,10,getSize().height));
			
			}else if(sig.get("nombre").equals("areaDrch")) {
				//System.out.println("		 area....");
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),0,10,getSize().height));
			
			}else if(sig.get("nombre").equals("puntosJug1")) {
				//System.out.println("		 puntos jugador1....");
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
			    g2d.drawString(Integer.toString((int)sig.get("puntos")), (getSize().width/2)-30, getSize().height/4);
			
			}else if(sig.get("nombre").equals("puntosJug2")) {
				//System.out.println("		 puntos jugador 2....");
				g2d.setPaint(Color.GREEN);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.drawString( Integer.toString((int)sig.get("puntos")), (getSize().width/2)+30, getSize().height/4);
			}
		}
		g2d.setBackground(Color.BLACK);
	}
}
