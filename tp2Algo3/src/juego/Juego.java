package juego;
import personajes.*;

public class Juego {
	
	//declaracion de atributos:
	private Caso caso;
	private Policia policia;
	
	//declaracion de metodos:
	public void crearCaso(Policia unPolicia){
		//asigna un caso al juego
		this.policia = unPolicia;
	}
	
	public Caso mostrarCaso(){
		return this.caso;
	}

}
