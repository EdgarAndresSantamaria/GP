package packModelo;

public class DyRaqueta extends Potenciador {
	
	private int buff;

	public DyRaqueta(boolean comportamiento) {
		super(3);//inicializamos el numero maximo de DyRaqueta ,default= 2(defaultDy)^3(maxDyRaqueta)=8
		initBehaviour(comportamiento);
	}
	
	private void initBehaviour(Boolean comportamiento) {
		if(comportamiento==(Boolean)null) {
			buff=2;//raqueta mejorada (vas mas rapido!!!!!)
		}else {
			buff=-2;//raqueta aturdida (vas al contrario y mas rapido!!!!!)
		}	
	}

	public int actuar() {
		return buff;
	}

}
