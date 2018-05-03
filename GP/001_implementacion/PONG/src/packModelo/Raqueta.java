package packModelo;

import java.awt.Rectangle;
import java.util.Collection;

public class Raqueta extends Elemento{
	private Jugador jugador;//jugador asociado a la raqueta
	private Collection<DyRaqueta> lPotenciadores;//lista potenciadores del jugador
	private int dy; //variable velocidad desplazamiento vertical, defecto : (2 pixel / update)
	
	//constantes
	private final int defaultDy=2;
	
	public Raqueta(String pNombre, Boolean pCampo) {
		super("raqueta");
		if(pNombre.contains("IA")) {
			jugador=new IA(pNombre);
		}else {
			jugador=new Jugador(pNombre);
		}
	}

	public String getNombre() {
		return jugador.getNombre();
	}

	public int golpeaRaqueta(Rectangle bola) {
		if(golpea(bola)) {
			return dy;
		}else {
			return 0;
		}
	}

	public void emular() {
		if(Pong.getPong().dentroCampo(getShape())){
			incrementarY(dy);
		}else{
			invertirY(dy);
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
		dy=2;//resetear velocidad
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