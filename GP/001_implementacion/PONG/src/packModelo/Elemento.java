package packModelo;

import java.awt.Rectangle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Elemento {
	
	private Rectangle dimension;
	private int x,y;
	
	public Elemento(String pTipo) {
		dimension = new Rectangle();
		if(pTipo.equals("bola")) {//medidas de la bola
			dimension.setSize(5, 5);

		}else if (pTipo.equals("potenciador")) {// medidas del potenciador
			dimension.setSize(5, 10);
							
		}else {//medidas de la raqueta
			dimension.setSize(5, 20);		
		}
	}
	
	public void setCoord(int pX, int pY) {
		dimension.x=pX;
		dimension.y=pY;
	}
	
	public void invertirX(int pDx) {
		dimension.x+=pDx;
		dimension.x=-dimension.x;
	}
	
	public void invertirY(int pDy) {
		dimension.y+=pDy;
		dimension.y=-dimension.y;
	}
	
	public void incrementarX(int pDx) {
		dimension.x+=pDx;
	}
	
	public void incrementarY(int pDy) {
		dimension.y+=pDy;
	}
	
	public boolean golpea(Rectangle bola) {
		return dimension.intersects(bola);
	}
	
	public Rectangle getShape() {
		return dimension;
	}

	public JSONObject getDimensionesYPos() {
		 JSONObject elemento = new JSONObject(); 
		 elemento.put("width", dimension.getWidth()); 
		 elemento.put("height", dimension.getHeight()); 
		 elemento.put("x", x); 
		 elemento.put("y", y); 
		 return elemento;
	}
}
