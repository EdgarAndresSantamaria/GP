package packModelo;

import java.util.Collection;

public class GestorBD {
	private static GestorBD instancia;
	
	private GestorBD() {}

	public static GestorBD getGestorBD() {
		if(instancia==null) {
			instancia=new GestorBD();
		}
		return instancia;
	}

	public Collection<Rank> cargar(String pJugador) {
		//cargar todos los Ranks de jugador
		return null;
	}

}
