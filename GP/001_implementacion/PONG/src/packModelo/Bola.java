package packModelo;

public class Bola extends Elemento {
	private boolean independiente;
	private int dx,dy;

	public Bola(boolean pIndependiente) {
		super("bola");
		independiente=pIndependiente;
		if(pIndependiente){
			setCoord();//generamos pelota Random tanto posición cómo movimiento
		}else {
			setCoord();//generamos pelota en el centro y empieza hacia la izquierda
		}
	}

	public Potenciador emular() {
		//gestión de fisicas de la bola
		Potenciador golpeado=Pong.getPong().golpeaPotenciador(getShape());
		if(Pong.getPong().golpeaRaqueta(getShape()) || golpeado!=null) {
			//tanto raquetas como potenciadores provocan rebote
			invertirX(dx);
		}else{
			if(Pong.getPong().dentroCampo(getShape())){
				incrementarY(dy);
			}else {
				//si fuera de campo ivertimos direccion
				invertirY(dy);
			}
		}
		return golpeado;
	}
	
	public boolean campoApotenciar() {
		//campo de jugador (true->izq,false->drch)
		return !(dx<0);
	}

	public Boolean marcado() {
		return Pong.getPong().marca(getShape());
	}

}
