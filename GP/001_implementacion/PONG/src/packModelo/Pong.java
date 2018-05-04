package packModelo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import packVista.Renderer;

public class Pong {
	
	private ArrayList<Bola> lBola ; //lista bolas
	private ArrayList<Rank> lPuntuacion ;//ranking del jugador1
	private ArrayList<Potenciador> lPotenciadores;//lista potenciadores en el campo
	private Raqueta jug1;
	private Raqueta jug2;
	private Renderer renderer;
	private Rectangle bounds;
	
	private Boolean tipoPotenciador;
	
	//constantes
	private final double probabilidadPotenciador=0.6;
	private final int DxBola= -2;//cte velocidad de la bola (es negativa porque inicia hacia jug1)
	private int fronteraSeguraJug1;//area jugador 1
	private int fronteraSeguraJug2;//area jugador 2
	
	//invariables
	private static Pong instancia;
	
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
	
	public void setConfig(String pJugador1, Rectangle pBounds ) {//inicialización del juego
		jug1=new Raqueta(pJugador1,true);
		lBola=new ArrayList<>();
		Bola principal=new Bola(false);
		inicializarBolaPpal(principal);
		inicializarRaquetas();
		lBola.add(principal);
		lPuntuacion=new ArrayList<>();
		lPuntuacion=GestorBD.getGestorBD().cargar(pJugador1);//cargar puntuación historica
		bounds=pBounds;
		tipoPotenciador=true;//tipo Potenciador true -> Multiplicador/false -> DyRaqueta
		
		fronteraSeguraJug1=(int)bounds.getX()+25;
		fronteraSeguraJug2=(int)bounds.getMaxX()-25;
	}
	
	public Boolean existeUsuario(String username, String pwd) {
		return GestorBD.getGestorBD().existeUsuario(username,pwd);
	}
	
	public Boolean registroUsuario(String username, String pwd) {
		return GestorBD.getGestorBD().registroUsuario(username, pwd);
	}
	
	private void inicializarRaquetas() {
		int xMax=(int) bounds.getMaxX();
		double yMax= bounds.getMaxY();
		jug1.setCoord(10,(int) (yMax/2));//campo izquierdo a media altura
		jug2.setCoord(xMax-10,(int) (yMax/2));//campo derecho a media altura
	}
	
	private void inicializarBolaPpal(Bola nuevaBola) {
		double xMax=bounds.getMaxX();
		double yMax=bounds.getMaxY();
		//pelota principal
		int pX=(int)xMax/2;//punto medio
		int pY=(int)yMax/2;//punto medio
		nuevaBola.setCoord(pX, pY);
		nuevaBola.setDx(DxBola);
	}
	
	private void inicializarBolaSecundaria(Bola nuevaBola,Rectangle pShape,int pDx) {
		nuevaBola.setCoord((int)(pShape.getWidth()/2), (int)(pShape.getHeight()/2));//la bola aparece en el centro del potenciador
		nuevaBola.setDx(-pDx);//al contrario que la bola original
	}
	
	private void inicializarPotenciador(Potenciador nuevoPotenciador) {
		int x = new Random().nextInt(fronteraSeguraJug2);
		if(x<fronteraSeguraJug1) {
			inicializarPotenciador( nuevoPotenciador); //no debe aparecer dentro de la frontera segura
		}
		int y=new Random().nextInt((int) bounds.getHeight());
		nuevoPotenciador.setCoord(x, y);
	}

	/*public JSONArray mostrar() {
		 //generar un json con los atributos de los elementos 
	    JSONArray listaObjetos = new JSONArray(); 
	     
	    //Raquetas, bolas, potenciadores (dimension y posicion) 
	    // datos: [{nombre: String, width: int, height: int, x: int, y:int},... ] 
	    JSONObject raquetas = new JSONObject(); 
	    raquetas.put("nombre", xxx); 
	    raquetas.put("width", xxx); 
	    raquetas.put("height", sss); 
	    raquetas.put("x", asklf); 
	    raquetas.put("y", dfff); 
	     
	    listaObjetos.add(raquetas); 
	     
	    raquetas = new JSONObject(); 
	    raquetas.put("nombre", xxx); 
	    raquetas.put("width", xxx); 
	    raquetas.put("height", sss); 
	    raquetas.put("x", asklf); 
	    raquetas.put("y", dfff); 
	     
	    listaObjetos.add(raquetas); 
	     
	    for(Bola unaBola : lBola) { 
	      JSONObject bola = new JSONObject(); 
	      bola.put("nombre", xxx); 
	      bola.put("width", xxx); 
	      bola.put("height", sss); 
	      bola.put("x", asklf); 
	      bola.put("y", dfff); 
	       
	      listaObjetos.add(bola); 
	    } 
	     
	    for(Potenciador unPotenciador : lPotenciadores) { 
	      JSONObject potenciador = new JSONObject(); 
	      potenciador.put("nombre", xxx); 
	      potenciador.put("width", xxx); 
	      potenciador.put("height", sss); 
	      potenciador.put("x", asklf); 
	      potenciador.put("y", dfff); 
	       
	      listaObjetos.add(potenciador); 
	    } 
	     
	    return listaObjetos; 
	     
	}*/
	
	public Boolean jugar() {
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
							jug1.addPotenciador((DyRaqueta) golpeado);//añadir potenciador a raqueta izquierda
						}
					}else {
						if(jug2.posiblePotenciar(golpeado.getMax())) {//si es posible potenciar campo derecho
							jug2.addPotenciador((DyRaqueta) golpeado);//añadir potenciador a raqueta derecha
						}
					}
				}else {
					//es potenciador multiplicador
					if(lBola.size()<golpeado.getMax()) {
						Bola multiplicada=new Bola(true);
						inicializarBolaSecundaria(multiplicada,golpeado.getShape(),tmp.getDx());//lanzar la bola 
						lBola.add(multiplicada);
					}
				}
			}else{	
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
		//expansión de los buff
		jug1.activarPotenciadores();
		jug2.activarPotenciadores();
		//emular el movimiento de la raqueta
		jug1.emular();
		if(jug2.getNombre().contains("IA")) {
			//calcular siguiente movimiento de la IA
			jug2.siguienteMov();
		}
		jug2.emular();
		//contracción y update de los buff
		jug1.desactivarPotenciadores();
		jug2.desactivarPotenciadores();

		//comprobar si desaparecen potenciadores
		for (Potenciador tmp : lPotenciadores ) {
			if(tmp.expirado()){
				lPotenciadores.remove(tmp);
			}
		}
		//generar Potenciador .... o no
		lanzarPotenciador();	
		return acabado;//retornará acabado cuando haya finalizado la simulación
	}
	
	private boolean finJuego() {
		return lPuntuacion.get(lPuntuacion.size()-1).fin();//marcar campo derecho;
	}

	private void lanzarPotenciador() {
		//lanzar un nuevo potenciador random ,tipos: true -> Multiplicador/ false -> DyRaqueta (-) / null -> DyRaqueta (+)
		boolean seLanza=probabilidadPotenciador*new Random().nextInt(100)>50;//modelando el azar para la aparición de potnciadores
		if(seLanza){
			Potenciador lanzado;
			if(tipoPotenciador==(Boolean) null){//lanzar potenciador tipo null
				lanzado=new DyRaqueta(true);
				tipoPotenciador=true;//cambiar de tipo
			}else if(tipoPotenciador){//cambiar de tipo a true
				lanzado=new Multiplicador();
				tipoPotenciador=!tipoPotenciador;//cambiar de tipo a false
			}else {//lanzar tipo false
				lanzado=new DyRaqueta(false);
				tipoPotenciador=(Boolean) null;//cambiar de tipo a null
			}
			lPotenciadores.add(lanzado);//lanzar al campo el potenciador
			inicializarPotenciador(lanzado);		}
	}

	public void  moverRaqueta(Boolean pDir,Boolean pCampo) {//distincion de campos para el caso player vs player
		//campo de jugador (true->izq,false->drch)
		if(pCampo==true) {//campo izquierdo
			jug1.moverRaqueta(pDir);
		}else {//campo derecho
			jug2.moverRaqueta(pDir);
		}
	}

	public Potenciador golpeaPotenciador(Rectangle bola) {
		Potenciador result=null;
		for(Potenciador tmp : lPotenciadores) {
			if(tmp.golpea(bola)) {
				lPotenciadores.remove(tmp);//eliminar de la lista el potenciador golpeado
				return result; //rompe la ejecución del for al encontrar un golpeado
			}
		}
		return result;
	}

	public int golpeaRaqueta(Rectangle bola) {
		return jug1.golpeaRaqueta(bola) + jug2.golpeaRaqueta(bola); // devolvera la velocidad y la direccion con que se impacto ... o no
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

	
	public void seguirBola() {//pero cual?? ... ahi reside su inteligencia y su perdición...., la más cercana
		int xBolaCercana=0;//inicializamos en la otra punta del campo
		Boolean dir;
		for(Bola tmp:lBola) {
			if(xBolaCercana<(int)tmp.getShape().getX() && tmp.getDx()>0) {//aun mas cercana y en nuestra dirección y no interceptamos....
				xBolaCercana=(int)tmp.getShape().getX();
				if((int)jug2.getShape().getY()>= (int)tmp.getShape().getY()) {//subir raqueta
					jug2.moverRaqueta(true);
				}else {//bajar raqueta
					jug2.moverRaqueta(false);
				}
			}
		}
		
	}

	public void deLadoAlado() {
		int ry=(int)jug2.getShape().getY();//0 de la raqueta
		int rmaxY= (int)jug2.getShape().getMaxY();//height de la raqueta
		int dy =jug2.getDy();
		if(dy>0 &&  (int) bounds.getHeight()>rmaxY) {//hacia abajo si solo si va hacia abajo y queda campo
			jug2.moverRaqueta(false);
		}else {//hacia arriba cuando se invierta su direccion por fin de campo y solo suba....ciclo
			jug2.moverRaqueta(true);
		}
		
	}
}