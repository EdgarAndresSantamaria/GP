package packModelo;

public class Estrategia {
	private Boolean dificultad;

	public Estrategia(String pDificultad) {
		if(pDificultad.equals("facil")){
			dificultad=false;//facil
		}else {
			dificultad=true; //dificil
		}			
	}

	public void siguienteMov() {//AI scripted
		if(dificultad) {
			Pong.getPong().seguirBola();
		}else {
			Pong.getPong().deLadoAlado();
		}
	}
}
