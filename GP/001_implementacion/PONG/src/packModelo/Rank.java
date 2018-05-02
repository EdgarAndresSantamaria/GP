package packModelo;

public class Rank {
	
	private int puntosJug1,puntosJug2;
	private String jugador1,jugador2;
	
	public Rank(String nombre1, String nombre2) {
		//inicializar nuevo Rank
		jugador1=nombre1;
		jugador2=nombre2;
		puntosJug1=0;
		puntosJug2=0;
	}

}
