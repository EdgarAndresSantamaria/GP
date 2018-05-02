package packModelo;

public class Potenciador extends Elemento {
	
	private int tiempo;

	public Potenciador(String pTipo) {
		super(pTipo);
		tiempo=60*10;//60 segundos 
	}

	public boolean expirado() {
		boolean expirado=tiempo==0;
		if(!expirado) {
			tiempo--;
		}
		return expirado;
	}

}
