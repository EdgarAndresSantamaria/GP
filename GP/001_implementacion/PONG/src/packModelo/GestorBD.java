package packModelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class GestorBD {
	private static GestorBD miGestorBD;


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


	public void guardar(Rank rank) {
		//guardar el ranking
		try {

			// Se pide
			Document doc = Jsoup.connect("https://galan.ehu.es/pdejaime001/WEB/pong/Ranking.php").data("jugador1",rank.getJugador1()).data("jugador2",rank.getJugador2()).data("puntuacion1",String.valueOf(rank.puntos1())).data("puntuacion2",String.valueOf(rank.puntos2())).post();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public JSONArray getRanking() {
		try {

			// Se pide
			Document doc = Jsoup.connect("https://galan.ehu.es/pdejaime001/WEB/pong/Ranking.php").get();
			
			// Se procesa el resultado
			if(doc != null) {
				String respuesta = doc.text();
				if(respuesta.equals("false")) return null;
				else {
					//json
					JSONArray ranking = new JSONArray(respuesta);
					return ranking;
				}
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	

	public Boolean existeUsuario(String nombre, String pwd) {
		try {

			// Se pide
			Document doc = Jsoup.connect("https://galan.ehu.es/pdejaime001/WEB/pong/LogearUsuario.php").data("username",nombre).data("password",pwd).maxBodySize(0).post();
			
			// Se procesa el resultado
			if(doc != null) {
				String respuesta = doc.text();
				if(respuesta.equals("true")) return true;
				else return false;
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean registroUsuario(String nombreUsuario, String pwd, String email) {
		try {
			if(existeUsuario(nombreUsuario, pwd)) {
				return false;
			}
			// Se pide
			Document doc = Jsoup.connect("https://galan.ehu.es/pdejaime001/WEB/pong/RegistrarUsuario.php").data("username",nombreUsuario).data("email",email).data("password",pwd).maxBodySize(0).post();
			
			// Se procesa el resultado
			if(doc != null) {
				String respuesta = doc.text();
				if(respuesta.equals("true")) return true;
				else return false;
			}

			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
	

}
