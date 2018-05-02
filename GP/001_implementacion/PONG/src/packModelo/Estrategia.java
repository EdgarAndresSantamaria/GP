package packModelo;

public class Estrategia {
	private int dificultad;

	public Estrategia(String pDificultad) {
		if(pDificultad.equals("facil")){
			dificultad=1;//facil
		}else {
			dificultad=2; //dificil
		}			
	}

	
}
