package packModelo;

public class Bola extends Elemento {
	private boolean independiente;
	private int dx,dy;

	public Bola(boolean pIndependiente) {
		super("bola");
		independiente=pIndependiente;
	}

	public Potenciador emular() {
		//gestión de fisicas de la bola
		Potenciador potenciadorGolpeado=Pong.getPong().golpeaPotenciador(getShape());
		int dirRaqueta=Pong.getPong().golpeaRaqueta(getShape());
		if(dirRaqueta!=0){//tanto raquetas como potenciadores provocan rebote
			dy=dirRaqueta;//seguira la direccion vertical de la raqueta impactada y con su misma velocidad ..... OJO!!!!
			invertirX(dx);//rebote orizontal
		}else if(potenciadorGolpeado!=null) {
			invertirX(dx);//rebote orizontal
		}else{
			if(Pong.getPong().dentroCampo(getShape())){
				incrementarY(dy);
			}else {
				//si fuera de campo ivertimos direccion
				invertirY(dy);
			}
		}
		return potenciadorGolpeado;
	}
	
	public boolean campoApotenciar() {
		//campo de jugador (true->izq,false->drch)
		return !(dx<0);//si es menor que 0 se dirige a la izquierda -> proviene de drcha
	}

	public Boolean marcado() {
		//preguntamos a ver si la dimensión de la bola se encuetra en la meta
		return Pong.getPong().marca(getShape());	
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int pDx) {
		dx=pDx;		
	}
	
}