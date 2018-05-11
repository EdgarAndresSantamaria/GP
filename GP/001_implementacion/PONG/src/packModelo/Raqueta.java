package packModelo;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Raqueta extends Elemento{
	private Jugador jugador;//jugador asociado a la raqueta
	private ArrayList<DyRaqueta> lPotenciadores;//lista potenciadores del jugador
	private int dy; //variable velocidad desplazamiento vertical, defecto : (2 pixel / update)
	
	//constantes
	private final int defaultDy=4;
	
	/**
	 * constructora de raqueta, genera una nueva raqueta parada con
	 * el nombre y campo especificados
	 * @param pNombre
	 * @param pCampo
	 */
	public Raqueta(String pNombre, Boolean pCampo) {
		super("raqueta");
		lPotenciadores = new ArrayList<DyRaqueta>();
		if(pNombre.contains("IA")) { //IA facil o IA dificil
			jugador=new IA(pNombre);
			moverRaqueta(true);//iniciar en movimiento hacia arriba
		}else {
			jugador=new Jugador(pNombre);
			pararRaqueta();//iniciar parada
		}
		
		
	}

	public String getNombre() {
		return jugador.getNombre();
	}

	/**
	 * devuelve la velocidad de la raqueta en caso de ser golpeada y null en caso de no ser golpeada 
	 * @param bola
	 * @return
	 */
	public Boolean golpeaRaqueta(Rectangle bola) {
		return golpea(bola);
			
	}

	/**
	 * metodo encargado de aplicar los cambios en la posicion sde la raqueta (y)
	 * segun la direccion(dy)
	 */
	public void emular() {
		if(Pong.getPong().dentroCampo(getShape(),0,dy)){
			System.out.println("incrementar dy raqueta");
			incrementarY(dy);
		}else{
			System.out.println("invertir dy raqueta");
			dy=-dy;
			incrementarY(dy);	
		}
	}

	public void addPotenciador(DyRaqueta golpeado) {
		lPotenciadores.add(golpeado);	
	}

	public boolean posiblePotenciar(int max) {
		return lPotenciadores.size()<max;
	}

	public void moverRaqueta(Boolean pDir) {
		
		//direccion( true-> hacia arriba , false -> hacia abajo )
		if(pDir) {
			dy=defaultDy;
		}else {
			dy=-defaultDy;
		}
	}
	
	public void pararRaqueta() {
		dy=0;	
	}

	public void activarPotenciadores() {
		for(DyRaqueta tmp : lPotenciadores) {
			dy*=tmp.actuar();
		}
	}

	public void siguienteMov() {
		IA es = (IA) jugador;
		es.siguienteMov();		
		
	}

	public void desactivarPotenciadores() {
		if(jugador.getNombre().contains("IA facil")){//si IA facil
			if(dy>0) {
				moverRaqueta(true);//resetear dy positivo
			}else {
				moverRaqueta(false);//resetear dy negativo
			}
		}else {
			pararRaqueta();
		}
		for(DyRaqueta tmp : lPotenciadores) {
			if(tmp.expirado()) {//eliminar los potenciadores expirados 
				lPotenciadores.remove(tmp);
			}
		}
		
	}
	
	public int getDy() {
		return dy;
	}

}