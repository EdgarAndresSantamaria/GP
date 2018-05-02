package packModelo;

import java.awt.Rectangle;

public class Raqueta extends Elemento{
	
	private Jugador jugador;//jugador asociado a la raqueta
	private boolean campo ;//campo de jugador (true->izq,false->drch)
	
	private int dy; //variable a updatear
	

	public Raqueta(String pNombre, Boolean pCampo) {
		super("raqueta");
		if(pNombre.contains("IA")) {
			jugador=new IA(pNombre);
			campo=pCampo;
			setCoord();//generamos raqueta parte drcha  a media altura
		}else {
			jugador=new Usuario(pNombre);
			campo=pCampo;
			if(campo) {
				setCoord();//generamos raqueta parte izquierda  a media altura
			}else {
				setCoord();//generamos raqueta parte drcha  a media altura
			}
			
		}
		// TODO Auto-generated constructor stub
	}


	public String getNombre() {
		return jugador.getNombre();
	}


	public boolean golpeaRaqueta(Rectangle bola) {
		return golpea(bola);
	}


	public void emular() {
		if(Pong.getPong().dentroCampo(getShape())){
			incrementarY(dy);
		}else {
			invertirY(dy);
		}
	}


	public void addPotenciador(Potenciador golpeado) {
		// TODO Auto-generated method stub
		
	}

}
