package packMain;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import packVista.F00Inicio;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		F00Inicio inicio = new F00Inicio();
		//F04MenuJuego m = new F04MenuJuego(false);
	}

}
