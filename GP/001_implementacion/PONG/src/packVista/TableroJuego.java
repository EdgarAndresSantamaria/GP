package packVista;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import packModelo.Pong;

public class TableroJuego extends Canvas {
	
	public TableroJuego(Dimension dimension) {
		setVisible(true);
		setSize(dimension);
		
	}
	
	public void loopJuego() {
		System.out.println("antes del if...");
		
		Boolean ganado=false;
		int quienGano=-1;
		System.out.println("antes del while...");
		
		while(!ganado) {
			synchronized (this){
				try {
					createBufferStrategy(2);
					BufferStrategy bs=getBufferStrategy();
					wait(1);
					//preparar siguiente frame
					quienGano=Pong.getPong().jugar();
					if(quienGano!=-1){
						ganado=true;
					}
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
				System.out.println("vuelta");
			}
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
				//System.out.println("		 raqueta....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("raquetaDrcha")) {
				//System.out.println("		 raqueta....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			
			}else if(sig.get("nombre").equals("bola")) {
				//System.out.println("		 bola....");
				g2d.setPaint(Color.WHITE);
			    g2d.setStroke(new BasicStroke( 10.0f ) );            
				g2d.fill( new Rectangle2D.Double((int)sig.get("x"),(int)sig.get("y"),(int)sig.get("width"),(int)sig.get("height")));
			}else if(sig.get("nombre").equals("potenciador")) {
				//System.out.println("		 potenciador....");
				g2d.setPaint(Color.WHITE);
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
			
			}
		}
	}
}
