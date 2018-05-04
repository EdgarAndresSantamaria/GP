package packModelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import packExcepciones.ExcepcionDatoActualizado;
import packExcepciones.ExcepcionDatoNoActualizado;
import packGestores.GestorBD;

public class GestorBD {
	private static GestorBD miGestorBD;
	private Connection myConn;
	private Statement myStmt;


	/**
	 * constructora
	 */
	private GestorBD() {

	}

	/**
	 * metodo para recuperar la instancia unica del GestorBD
	 * @return
	 */
	public static GestorBD getGestorBD() {
		if (miGestorBD == null) {
			miGestorBD = new GestorBD();
		}
		return miGestorBD;
	}

	/**
	 * metodo para abrir una nueva conexion hacia la BD
	 * @param serverAddress
	 * @param port
	 * @param user
	 * @param password
	 */
	public void conectar() throws SQLException {

		// 1. Get a connection to database
		myConn = DriverManager.getConnection("jdbc:mysql://galan.ehu.eus:3306/Xpdejaime001_pong", "Xpdejaime001", "AIC7nDBJ");

		System.out.println("Database connection successful!\n");

		// 2. Create a statement
		myStmt = myConn.createStatement();
	}

	/**
	 * metodo para cerrar la actual conexion hacia la BD
	 */
	public void cerrarConexion()  throws SQLException  {	
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	/**
	 * metodo update que ejecuta sentencias SQL del tipo update,insert,delete
	 * @param SentenciaSQL
	 */
	private void exeSQLUpdate(String SentenciaSQL)  throws SQLException  {
		myStmt.executeUpdate(SentenciaSQL);

	}

	/**
	 * metodo update que ejecuta sentencias SQL del tipo Select
	 * resultado devuelto en formato ResultSet SQL
	 * @param SentenciaSQL
	 * @return
	 * @throws SQLException
	 */
	private ResultSet exeSQLSelect(String SentenciaSQL) throws SQLException {
		ResultSet myRs = null;
		// 3. Execute SQL query
		myRs = myStmt.executeQuery(SentenciaSQL);
		return myRs;
	}

	public ArrayList<Rank> cargar(String pJugador1) {
		//cargar el ranking del jugador especificado
		return null;
	}

	public void guardar(ArrayList<Rank> ranking) {
		//guardar el ranking del jugador especificado
	}

	public Boolean existeUsuario(String nombre, String pwd) {
		Boolean encontrado = false;
		try {
			// Se conecta
			conectar();

			// Se pide
			ResultSet myRs = exeSQLSelect("SELECT * FROM users WHERE username='" + nombre + "' AND password='"+ pwd + "'");

			// Se procesa el resultado
			encontrado = myRs != null && myRs.next();

			// Se cierra
			cerrarConexion();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encontrado;

	}
}
