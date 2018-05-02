package packModelo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import packVista.Renderer;

public class Pong {
	
	private Collection<Bola> lBola ; //lista bolas
	private Collection<Rank> lPuntuacion ;//ranking del jugador1
	private Collection<Potenciador> lPotenciadores;//lista potenciadores en el campo
	private Raqueta jug1;
	private Raqueta jug2;
	private Renderer renderer;
	private static Pong instancia;
	private Rectangle bounds;
	
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
		lPuntuacion=GestorBD.getGestorBD().cargar(pJugador1);//cargar puntuación historica
		bounds=pBounds;
	}
	
	public void jugar() {
		update();
	}
	
	
	private void update() {
		//emular el movimiento de las bolas
		for (Bola tmp : lBola ) {
			Potenciador golpeado=tmp.emular();
			if(golpeado!=null) {
				if(golpeado instanceof DyRaqueta) {
					//es potenciador de raqueta
					if(tmp.campoApotenciar()) {
						//campo de jugador (true->izq,false->drch)
						if(//posible potenciar)
						jug1.addPotenciador(golpeado);
					}else {
						if(//posible potenciar)
						jug2.addPotenciador(golpeado);
					}
				}else {
					//es potenciador multiplicador
					if(//posible multiplicar)
					lBola.add(new Bola(true));
				}
			}else {
				//comprobar si ha marcado
				
				if(marcado)//comprobar fin dejugo
			}
		}
		
		//emular el movimiento de la raqueta
		jug1.emular();
		jug2.emular();
		
		//comprobar si desaparecen potenciadores
		for (Potenciador tmp : lPotenciadores ) {
			if(tmp.expirado()) {
				lPotenciadores.remove(tmp);
			}
		}
		// lanzar nuevos potenciadores
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
}