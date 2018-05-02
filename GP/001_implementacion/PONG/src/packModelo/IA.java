package packModelo;

public class IA extends Jugador {
	Estrategia estrategia;

	public IA(String pNombre) {
		super(pNombre);
		estrategia=new Estrategia(pNombre.split("")[1]); // IA facil || IA dificil
		
	}

}
