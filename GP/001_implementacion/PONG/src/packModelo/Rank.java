package packModelo;

public class Rank {
	
	private int puntosJug1,puntosJug2;
	private String jugador1,jugador2;
	
	//constante
	private final int maxPuntos=22;//partida a 22 puntos
	
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
	
	/**
	* metodo que devuelve valor en caso de acabar la partida (true -> gana jugador 1
	* false -> gana jugador 2) y null si todavia no ha acabado
	* @return
	*/
	public boolean fin() {
		Object fin=null;
		if(puntosJug1==maxPuntos) {
			fin=true;//gana jugador 1
		}else if(puntosJug2==maxPuntos){
			fin=false;//gana jugador 2
		}
		System.err.println("finalizado..."+fin);
		System.err.println("puntos jug 1..."+puntosJug1);
		System.err.println("puntos jug 2..."+puntosJug2);
		System.err.println("max puntos..."+maxPuntos);
		return (Boolean) fin;
	}

	/**
	 * metodo que devuelve los puntos del jugador 1
	 * @return
	 */
	public int puntos1() {
		// TODO Auto-generated method stub
		return puntosJug1;
	}
	
	/**
	 * metodo que devuelve los puntos del jugador 2
	 * @return
	 */
	public int puntos2() {
		// TODO Auto-generated method stub
		return puntosJug2;
	}

}
