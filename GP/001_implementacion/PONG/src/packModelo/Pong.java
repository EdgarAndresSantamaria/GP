package packModelo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import packVista.Renderer;

public class Pong {
	
	private ArrayList<Bola> lBola ; //lista bolas
	private ArrayList<Rank> lPuntuacion ;//ranking del jugador1
	private ArrayList<Potenciador> lPotenciadores;//lista potenciadores en el campo
	private Raqueta jug1;
	private Raqueta jug2;
	private Renderer renderer;
	private static Pong instancia;
	private Rectangle bounds;
	private double probabilidadPotenciador;
	
	private Pong() {}
	
	public static Pong getPong() {
		if(instancia==null) {
			instancia=new Pong();
		}
		return instancia;
	}
	
	public void setOponente(String pJugador2) { // selección de oponente
		jug2=new Raqueta(pJugador2,false);	
		lPuntuacion.add(new Rank(jug1.getNombre(),jug2.getNombre())); //generar nuevo Rank para la partida}
	}
	
	public void setConfig(String pJugador1,Rectangle pBounds ) {//inicialización del juego
		jug1=new Raqueta(pJugador1,true);
		lBola=new ArrayList<>();
		Bola principal=new Bola(false);
		lBola.add(principal);
		lPuntuacion=new ArrayList<>();
		lPuntuacion=GestorBD.getGestorBD().cargar(pJugador1);//cargar puntuación historica
		bounds=pBounds;
		probabilidadPotenciador=0.6;
	}
	
	public json mostrar() {
		//generar un json con los atributos de los elementos
	}
	
	public boolean update() {
		boolean acabado=false;
		//emular el movimiento de las bolas
		for (Bola tmp : lBola ) {
			Potenciador golpeado=tmp.emular();
			if(golpeado!=null) {
				if(golpeado instanceof DyRaqueta) {
					//es potenciador de raqueta
					if(tmp.campoApotenciar()) {
						//campo de jugador (true->izq,false->drch)
						if(jug1.posiblePotenciar(golpeado.getMax())) {//si es posible potenciar campo izquierdo
							jug1.addPotenciador(golpeado);
						}
					}else {
						if(jug2.posiblePotenciar(golpeado.getMax())) {//si es posible potenciar campo derecho
							jug2.addPotenciador(golpeado);
						}
					}
				}else {
					//es potenciador multiplicador
					if(posibleMultiplicar(golpeado.getMax())) {
						lBola.add(new Bola(true));
					}
				}
			}else {	
				Boolean marcado=tmp.marcado();
				if(marcado != null){//comprobar fin de juego
					if(marcado==true) {//marca campo izquierdo
						 lPuntuacion.get(lPuntuacion.size()-1).marcarJug1();//marcar campo izquierdo
					}else {//marca campo derecho
						lPuntuacion.get(lPuntuacion.size()-1).marcarJug2();//marcar campo derecho
					}
					Boolean ganador=finJuego();
					//comprobar fin de juego
					if(ganador!=null) {
						acabado=ganador;
						GestorBD.getGestorBD().guardar(lPuntuacion);//guardar nueva puntuación historica
					}
				}
			}
		}

		//emular el movimiento de la raqueta
		jug1.emular();
		if(jug2.getNombre().contains("IA")) {
			//calcular siguiente movimiento de la IA
			jug2
		}
		jug2.emular();

		//comprobar si desaparecen potenciadores
		for (Potenciador tmp : lPotenciadores ) {
			if(tmp.expirado()){
				lPotenciadores.remove(tmp);
			}
		}
		
		lanzarPotenciador();	
		return acabado;
	}
	
	private boolean finJuego() {
		return lPuntuacion.get(lPuntuacion.size()-1).fin();//marcar campo derecho;
	}

	private void lanzarPotenciador() {
		//lanzar un nuevo potenciador random
		if()
		
	}
	
	public void  moverRaqueta(Boolean pDir,Boolean pCampo) {
		//campo de jugador (true->izq,false->drch)
		if(pCampo==true) {//campo izquierdo
			jug1.moverRaqueta(pDir);
		}else {//campo derecho
			jug2.moverRaqueta(pDir);
		}
	}

	private boolean posibleMultiplicar(int max) {
		return lBola.size()<max;
	}

	public Potenciador golpeaPotenciador(Rectangle bola) {
		Potenciador result=null;
		for(Potenciador tmp : lPotenciadores) {
			if(tmp.golpea(bola)) {
				return result; //rompe la ejecución del for al encontrar un golpeado
			}
		}
		return result;
	}

	public boolean golpeaRaqueta(Rectangle bola) {
		return jug1.golpeaRaqueta(bola) || jug2.golpeaRaqueta(bola) ;
	}

	public boolean dentroCampo(Rectangle shape) {
		return bounds.intersects(shape);
	}

	public Boolean marca(Rectangle bola) {
		Object marcado = null;
		//campo de jugador (true->izq,false->drch,null->no marca)
		if(bounds.getMinX()==bola.getX()) {//marcar campo izquierdo
				marcado=true;
		}else if(bounds.getMaxX()==bola.getX()) {//marcar campo derecho
			marcado=false;
		}
		return (Boolean)marcado;
	}
}