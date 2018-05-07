package packModelo;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Pong {

	private ArrayList<Bola> lBola ; //lista bolas
	private ArrayList<Rank> lPuntuacion ;//ranking del jugador1
	private ArrayList<Potenciador> lPotenciadores;//lista potenciadores en el campo
	private Raqueta jug1;
	private Raqueta jug2;
	private Rectangle bounds;

	private Boolean tipoPotenciador;

	//constantes
	private final double probabilidadPotenciador=0.1;
	private final int DxBola= -2;//cte velocidad de la bola (es negativa porque inicia hacia jug1)
	private final int DyBola=  0;
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

	/**
	 * inicializar oponente del juego IA o jugador
	 * @param pJugador2
	 */
	public void setOponente(String pJugador2) { // selección de oponente
		//System.out.println("inicializando oponente...");
		//generar campo derecho con el jugador
		jug2=new Raqueta(pJugador2,false);	
		lPuntuacion.add(new Rank(jug1.getNombre(),jug2.getNombre())); //generar nuevo Rank para la partida}
		inicializarRaquetas();//inicializar la posición e ambas raquetas

	}

	/**
	 * Método que inicializa el juego con el jugador ya verificado 
	 * y con las dimensiones de pantalla que tendrá el campo
	 * @param pJugador1
	 * @param pBounds
	 */
	public void setConfig(String pJugador1, Rectangle pBounds ) {//inicialización del juego y el campo
		//System.out.println("inicializando....");
		//inicializar campo
		bounds=pBounds;
		//inicializar listas
		lBola=new ArrayList<>();
		lPuntuacion=new ArrayList<Rank>(); 
		lPotenciadores = new ArrayList<Potenciador>();
		//generar raqueta para jugador1 campo izquierdo
		jug1=new Raqueta(pJugador1,true);
		//generar nueva bola principal e inicializarla
		Bola principal=new Bola(false);
		inicializarBolaPpal(principal);
		lBola.add(principal);
		//inicializar variables para generación de potenciadores
		tipoPotenciador=true;//tipo Potenciador true -> Multiplicador/false -> DyRaqueta
		int distanciaArea=(int)(bounds.getX()+bounds.getMaxX()/4);//25% del campo
		fronteraSeguraJug1=distanciaArea;//25 % campo izquierdo seguro
		fronteraSeguraJug2=(int)(bounds.getMaxX()-distanciaArea); //25 % campo derecho seguro	
		//System.out.println("esquina superior: "+bounds.getX()+" "+ bounds.getY());
		//System.out.println("altura/anchura: "+bounds.getWidth()+" "+ bounds.getHeight());
	}

	public Boolean existeUsuario(String username, String pwd) {
		return GestorBD.getGestorBD().existeUsuario(username,pwd);
	}

	public Boolean registroUsuario(String username, String pwd) {
		return GestorBD.getGestorBD().registroUsuario(username, pwd);
	}

	/**
	 * método que inicializa la posición de la raquetas en el juego
	 */
	private void inicializarRaquetas() {
		//System.out.println("inicializando raquetas...");
		//recoger cotas del campo
		int xMax=(int) bounds.getMaxX();
		double yMax= bounds.getMaxY();
		int distanciaRaqueta=(int)(bounds.getX()+bounds.getMaxX()/8);//12,5 % del campo	
		//inicializar raquetas:
		jug1.setCoord(distanciaRaqueta,(int) (yMax/2));//campo izquierdo a media altura
		jug2.setCoord(xMax-distanciaRaqueta,(int) (yMax/2));//campo derecho a media altura
		//System.out.println("coordenadas jugador 1: "+jug1.getShape().x+" "+jug1.getShape().y);
		//System.out.println("coordenadas jugador 2: "+jug2.getShape().x+" "+jug2.getShape().y);
	}

	/**
	 * inicialización de la bola principal del juego
	 * @param nuevaBola
	 */
	private void inicializarBolaPpal(Bola nuevaBola) {
		System.out.println("inicializando bola principal...");
		//recuperar limites del campo
		double xMax=bounds.getMaxX();
		double yMax=bounds.getMaxY();
		//pelota principal
		int pX=(int)xMax/2;//punto medio
		int pY=(int)yMax/2;//punto medio
		nuevaBola.setCoord(pX, pY);
		nuevaBola.setDx(DxBola);
		nuevaBola.setDy(DyBola);
		//System.out.println("coordenadas de la bola:"+nuevaBola.getShape().x+" "+nuevaBola.getShape().y);
		//System.out.println("direccion de la bola:"+nuevaBola.getDx()+" "+nuevaBola.getDy());
	}

	/**
	 * inicializar bola multiplicada desde el centro del potenciador 
	 * y en dirección contraria a la bola que golpeo el potenciador
	 * @param nuevaBola
	 * @param pShape
	 * @param pDx
	 */
	private void inicializarBolaSecundaria(Bola nuevaBola,Rectangle pShape,int pDx) {
		nuevaBola.setCoord((int)(pShape.getCenterX()), (int)(pShape.getCenterY()));//la bola aparece en el centro del potenciador
		nuevaBola.setDx(-pDx);//al contrario que la bola original
	}

	/**
	 * método que inicializa nuevos potenciadores
	 * @param nuevoPotenciador
	 */
	private void inicializarPotenciador(Potenciador nuevoPotenciador) {
		int x = new Random().nextInt(fronteraSeguraJug2);//generar nuevo numero entre 0 y el area  
		if(x<fronteraSeguraJug1) {
			inicializarPotenciador( nuevoPotenciador); //no debe aparecer dentro de la frontera segura
		}
		//System.out.println("Inicializando potenciador....");
		int y=new Random().nextInt((int) bounds.getHeight());//generar nuevo numero aleatorio dentro de la altura
		nuevoPotenciador.setCoord(x, y);
		//System.out.println("coordenadas potenciador: "+nuevoPotenciador.getShape().x+" "+nuevoPotenciador.getShape().y);
	}
	
	/**
	 * método que devuelve un array de objetos en formato JSON para dibujar el campo de juego
	 * @return
	 */
	public JSONArray mostrar() {
		//generar un json con los atributos de los elementos 
		JSONArray listaObjetos = new JSONArray(); 
		//Raquetas, bolas, potenciadores (dimension y posicion) 
		// datos: [{nombre: String, width: int, height: int, x: int, y:int},... ] 
		JSONObject areaIzq =new JSONObject();
		areaIzq.put("nombre", "areaIzq"); 
		areaIzq.put("x", fronteraSeguraJug1); 
		listaObjetos.add(areaIzq); 
		//devolver el limite del area derecha
		JSONObject areaDrch =new JSONObject();
		areaDrch.put("nombre", "areaDrch"); 
		areaDrch.put("x", fronteraSeguraJug2); 
		listaObjetos.add(areaDrch); 
		//devolver el limite del area izquierda
		JSONObject raquetaIzqda = jug1.getDimensionesYPos();
		raquetaIzqda.put("nombre", "raquetaIzqda"); 
		listaObjetos.add(raquetaIzqda); 
		//devolver la raqueta derecha
		JSONObject raquetaDrcha = jug2.getDimensionesYPos();
		raquetaDrcha.put("nombre", "raquetaDrcha"); 
		listaObjetos.add(raquetaDrcha); 

		for(Bola unaBola : lBola) {
			//devolver dimensiones de cada bola
			JSONObject bola = unaBola.getDimensionesYPos();
			bola.put("nombre", "bola");
			listaObjetos.add(bola); 
		} 

		for(Potenciador unPotenciador : lPotenciadores) { 
			//devolver dimensiones de cada potenciador
			JSONObject potenciador = unPotenciador.getDimensionesYPos();
			if(unPotenciador instanceof Multiplicador) {
				potenciador.put("nombre", "multiplicador");
			}else if(unPotenciador instanceof DyRaqueta) {
				potenciador.put("nombre","dyRaqueta");
			}
			listaObjetos.add(potenciador); 
		} 

		return listaObjetos; 

	} 

	/**
	 * metodo principal del juego,constituye el hilo principal, 
	 * cada vez que se le llama updateara los cambios del juego 
	 * y relizara todas las comprobaciones pertinentes
	 * @return
	 */
	public int jugar() {
		int acabado=-1;
		//emular el movimiento de las bolas
		//System.out.println("comprobando bolas...");
		for (Bola tmp : lBola ) {
			Potenciador golpeado=tmp.emular();
			if(golpeado!=null) {
				//System.out.println("golpea potenciador...");
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
				//System.out.println("comprobar si marca...");
				Boolean marcado=tmp.marcado();
				if(marcado != null){//comprobar fin de juego
					if(marcado==true) {//marca campo izquierdo
						//System.out.println("marca en campo izquierdo...");
						lPuntuacion.get(lPuntuacion.size()-1).marcarJug1();//marcar campo izquierdo
					}else {//marca campo derecho
						//System.out.println("marca en campo derecho...");
						lPuntuacion.get(lPuntuacion.size()-1).marcarJug2();//marcar campo derecho
					}
					Boolean ganador=finJuego();
					//comprobar fin de juego
					if(ganador!=null) {
						//System.out.println("ha ganado ...");
						if(ganador) {
							acabado=1;
						}else {
							acabado=2;
						}
						GestorBD.getGestorBD().guardar(lPuntuacion);//guardar nueva puntuación historica
					}
				}
			}
		}
		//System.out.println("activando potenciadores...");
		//expansión de los buff
		jug1.activarPotenciadores();
		jug2.activarPotenciadores();
		//emular el movimiento de la raqueta
		//System.out.println("emular jugador1...");
		jug1.emular();
		if(jug2.getNombre().contains("IA")) {
			//System.out.println("si es IA calcular sig movimiento...");
			//calcular siguiente movimiento de la IA
			jug2.siguienteMov();
		}
		//System.out.println("emular jugador2...");
		jug2.emular();
		//System.out.println("desactivando potenciadores...");
		//contracción y update de los buff
		jug1.desactivarPotenciadores();
		jug2.desactivarPotenciadores();
		//System.out.println("comprobar potenciadores expirados en el campo...");
		//comprobar si desaparecen potenciadores
		for (Potenciador tmp : lPotenciadores ) {
			if(tmp.expirado()){
				//System.out.println("borrando potenciador...");
				lPotenciadores.remove(tmp);
			}
		}
		//generar Potenciador .... o no
		//System.out.println("lanzar nuevo potenciador...");
		lanzarPotenciador();
		return acabado;//retornará acabado cuando haya finalizado la simulación
	}

	/**
	 * metodo que comprueba si se ha terminado la partida actual
	 * @return
	 */
	private boolean finJuego() {
		return lPuntuacion.get(lPuntuacion.size()-1).fin();
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

	/**
	 * método que mueve la raqueta (pCampo) en direccion (pDir)
	 * @param pDir
	 * @param pCampo
	 */
	public void  moverRaqueta(Boolean pDir,Boolean pCampo) {
		//System.out.println("		entra jugador: (true=1,false=2)  :"+pCampo);
		//System.out.println("		direccion: (true=arriba,false=abajo)  :"+pDir);
		//distincion de campos para el caso player vs player
		//campo de jugador (true->izq,false->drch)
		if(pCampo==true) {//campo izquierdo
			jug1.moverRaqueta(pDir);
		}else {//campo derecho
			jug2.moverRaqueta(pDir);
		}
	}
	
	/**
	 * método que mueve la raqueta (pCampo) en direccion (pDir)
	 * @param pDir
	 * @param pCampo
	 */
	public void  pararRaqueta(Boolean pCampo) {
		//System.out.println("		entra jugador: (true=1,false=2)  :"+pCampo);
		//distincion de campos para el caso player vs player
		//campo de jugador (true->izq,false->drch)
		if(pCampo==true) {//campo izquierdo
			jug1.pararRaqueta();;
		}else {//campo derecho
			jug2.pararRaqueta();
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

	public Integer golpeaRaqueta(Rectangle bola) {	
		if(jug1.golpeaRaqueta(bola)) {
			return jug1.getDy();
		}else if(jug2.golpeaRaqueta(bola)){
			return jug2.getDy();
		}else {
			return (Integer)null;
		}
	}

	public boolean dentroCampo(Rectangle shape, int dx, int dy) {
		System.err.println("comprobando dentro campo:....");
		System.err.println("		shape     Y:...."+(int)shape.getY());
		System.err.println("		shape     X:...."+(int)shape.getX());
		System.err.println("		shape     maxY:...."+(int)shape.getWidth());
		System.err.println("		shape     maxX:...."+(int)shape.getHeight());
		System.err.println("		shape     DY:...."+dy);
		System.err.println("		shape     DX:...."+dx);
		
		int posicionEsperadaY;
		int posicionEsperadaX;
		int x0=(int)bounds.getX();
		int y0=(int)bounds.getY();
		int xmax=(int)bounds.getWidth();
		int ymax=(int)bounds.getHeight();
		System.err.println("		bounds     Y:...."+(int)bounds.getY());
		System.err.println("		bounds     X:...."+(int)bounds.getX());
		System.err.println("		bounds     maxY:...."+(int)bounds.getWidth());
		System.err.println("		bounds     maxX:...."+(int)bounds.getHeight());
		if(dy>0 ||dy==0) {
			posicionEsperadaY= (int)shape.getY()+dy;
		}else {
			posicionEsperadaY= (int)shape.getY()+dy+(int)shape.getHeight();
		}
		if(dx>0||dx==0) {
			posicionEsperadaX= (int)shape.getX()+dx;
		}else {
			posicionEsperadaX= (int)shape.getX()+dx+(int)shape.getWidth();
		}
		System.err.println("		Posicion esperada X:...."+posicionEsperadaX);
		System.err.println("		Posicion esperada Y:...."+posicionEsperadaY);
		
		Boolean dentroY=ymax>posicionEsperadaY && posicionEsperadaY>y0;
		Boolean dentroX=(xmax>posicionEsperadaX && posicionEsperadaY>x0);
		System.err.println("		dentroX?:...."+dentroX); 
		System.err.println("		dentroY?:...."+dentroY); 
		return dentroX && dentroY;
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
					System.out.println("		entra IA dificil");
					System.out.println("		direccion: (true=arriba,false=abajo)  :"+true);		
					jug2.moverRaqueta(true);
				}else {//bajar raqueta
					System.out.println("		entra IA dificil");
					System.out.println("		direccion: (true=arriba,false=abajo)  :"+false);
					jug2.moverRaqueta(false);
				}
			}
		}

	}
}