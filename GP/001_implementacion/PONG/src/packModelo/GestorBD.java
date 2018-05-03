package packModelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestorBD {
	private static GestorBD miGestorBD;
	private ResultSet resultado;
	private Connection connection;
	private Statement instruccion;
	private String bd = "auditingAB";

	public String getBD(){
		return bd;
	}
	
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
	public void OpenConnection(String serverAddress, String port, String user, String password) throws SQLException {
			connection= DriverManager.getConnection("jdbc:mysql://"+serverAddress+":"+port+"/"+bd, user, password);
			instruccion = connection.createStatement();
	}

	/**
	 * metodo para cerrar la actual conexion hacia la BD
	 */
	public void CloseConnection()  throws SQLException  {	
			connection.close();
	}

	/**
	 * metodo update que ejecuta sentencias SQL del tipo update,insert,delete
	 * @param SentenciaSQL
	 */
	private void Update(String SentenciaSQL)  throws SQLException  {
			instruccion.executeUpdate(SentenciaSQL);
	}

	/**
	 * metodo update que ejecuta sentencias SQL del tipo Select
	 * resultado devuelto en formato ResultSet SQL
	 * @param SentenciaSQL
	 * @return
	 * @throws SQLException
	 */
	private ResultSet Select(String SentenciaSQL) throws SQLException {
		resultado = instruccion.executeQuery(SentenciaSQL);
		return resultado;
	}
	
	/**
	 * to do los métodos que carguen y guarden la clasificación del juego
	 * @param pJugador1
	 * @return
	 */

	public ArrayList<Rank> cargar(String pJugador1) {
		//cargar el ranking del jugador especificado
		return null;
	}
	
	public void guardar(ArrayList<Rank> ranking) {
		//guardar el ranking del jugador especificado
	}
}
