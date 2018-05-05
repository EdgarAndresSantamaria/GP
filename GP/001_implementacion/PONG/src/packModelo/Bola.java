package packModelo;

public class Bola extends Elemento {
	private boolean independiente;
	private int dx,dy;

	public Bola(boolean pIndependiente) {
		super("bola");
		independiente=pIndependiente;
	}

	/**
	 * metodo encargado de aplicar los cambios pertinentes 
	 * a la posicion (x,y) de la bola en funcion de la direccion
	 * (dx,dy)
	 * @return
	 */
	public Potenciador emular() {
		System.out.println("emulando bola...");
		//gestión de fisicas de la bola
		Potenciador potenciadorGolpeado=Pong.getPong().golpeaPotenciador(getShape());
		Integer dirRaqueta=Pong.getPong().golpeaRaqueta(getShape());
		if(dirRaqueta!= (Integer) null){//tanto raquetas como potenciadores provocan rebote
			System.out.println("rebote con raqueta...");
			dy=dirRaqueta;//seguira la direccion vertical de la raqueta impactada y con su misma velocidad ..... OJO!!!!
			decrementarX(dx);//rebote orizontal
			dx=-dx;//invertir direccion orizontal
			incrementarY(dy);
		}else if(potenciadorGolpeado!=null) {
			System.out.println("rebote con potenciador...");
			decrementarX(dx);//rebote orizontal
			dx=-dx;//invertir dirección orizontal
			incrementarY(dy);
		}else{
			if(Pong.getPong().dentroCampo(getShape())){
				System.out.println("continuar movimiento...");
				incrementarY(dy);
				incrementarX(dx);
			}else {
				//si fuera de campo ivertimos direccion
				System.out.println("rebote fin campo...");
				decrementarY(dy);
				incrementarX(dx);
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

	public int getDy() {
		// TODO Auto-generated method stub
		return dy;
	}

	public void setDy(int pDy) {
		dy=pDy;		
		
	}
	
}