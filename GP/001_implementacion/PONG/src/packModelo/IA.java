package packModelo;

public class IA extends Jugador {
	Estrategia estrategia;

	IA(String pNombre) {
		super(pNombre);
		estrategia=new Estrategia(pNombre.split(" ")[1]); // IA facil || IA dificil
		
	}
	
	public void siguienteMov() {
		estrategia.siguienteMov();
	}

}
