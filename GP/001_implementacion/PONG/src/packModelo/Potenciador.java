package packModelo;

public class Potenciador extends Elemento {
	
	private int tiempo;
	private int maxNum;

	public Potenciador(int pMax) {
		super("potenciador");
		maxNum=pMax;
		tiempo=60*10;//60 segundos 
	}

	public boolean expirado() {
		boolean expirado=tiempo==0;
		if(!expirado) {
			tiempo--;
		}
		return expirado;
	}

	public int getMax() {
		return maxNum;
	}

}
