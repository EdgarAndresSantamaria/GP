package packModelo;

public class Rank {
	
	private int puntosJug1,puntosJug2;
	private String jugador1,jugador2;
	private int maxPuntos;
	
	public Rank(String nombre1, String nombre2) {
		//inicializar nuevo Rank
		jugador1=nombre1;
		jugador2=nombre2;
		puntosJug1=0;
		puntosJug2=0;
	}

	public void marcarJug2() {
		puntosJug1++;
		
	}
	
	public void marcarJug1() {
		puntosJug2++;
		
	}

	public boolean fin() {
		Object fin=null;
		if(puntosJug1==maxPuntos) {
			fin=true;
		}else if(puntosJug2==maxPuntos){
			fin=false;
		}
		return (Boolean) fin;
	}

}
