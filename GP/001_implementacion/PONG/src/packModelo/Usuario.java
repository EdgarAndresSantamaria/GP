package packModelo;

public class Usuario extends Jugador {
	
	private String pwd;

	public Usuario(String pNombre) {
		super(pNombre);
	}
	
	public void setPwd(String password) {
		pwd=password;
	}
	
	public Boolean loginValido() {
		return GestorBD.getGestorBD().loginValido(getNombre(),pwd);
	}
	
}
