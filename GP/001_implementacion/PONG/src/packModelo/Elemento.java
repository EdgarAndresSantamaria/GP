package packModelo;

import java.awt.Rectangle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Elemento {
	
	private Rectangle dimension;
	
	public Elemento(String pTipo) {
		dimension = new Rectangle();
		if(pTipo.equals("bola")) {//medidas de la bola
			dimension.setSize(10, 10);

		}else if (pTipo.equals("potenciador")) {// medidas del potenciador
			dimension.setSize(10, 20);
							
		}else {//medidas de la raqueta
			dimension.setSize(10, 40);		
		}
	}
	
	public void setCoord(int pX, int pY) {
		dimension.x=pX;
		dimension.y=pY;
	}
	
	public void decrementarX(int pDx) {
		System.out.println(dimension.x);
		System.out.println("decrementada x......");
		dimension.x-=pDx;
		System.out.println(dimension.x);
	}
	
	public void decrementarY(int pDy) {
		System.out.println(dimension.y);
		System.out.println("decrementada y......");
		dimension.y-=pDy;
		System.out.println(dimension.y);
		
	}
	
	public void incrementarX(int pDx) {
		System.out.println(dimension.x);
		System.out.println("incrementada x......");
		dimension.x+=pDx;
		System.out.println(dimension.x);
	}
	
	public void incrementarY(int pDy) {
		System.out.println(dimension.y);
		System.out.println("incrementada y......");
		dimension.y+=pDy;
		System.out.println(dimension.y);
	}
	
	public boolean golpea(Rectangle bola) {
		return dimension.intersects(bola);
	}
	
	public Rectangle getShape() {
		return dimension;
	}

	public JSONObject getDimensionesYPos() {
		 JSONObject elemento = new JSONObject(); 
		 elemento.put("width", (int)dimension.getWidth()); 
		 elemento.put("height",(int) dimension.getHeight()); 
		 elemento.put("x", (int)dimension.getX()); 
		 elemento.put("y",(int) dimension.getY()); 
		 return elemento;
	}
}
