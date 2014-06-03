package personajes;

import elementosDelJuego.*;
import geografico.*;

public class Policia {

	//declaracion de atributos:
	private Tiempo tiempoFinal;
	private Tiempo tiempoInicial;
	private Grado grado;
	private Ciudad ciudadActual;	// indica en que ciudad y lugar se encuentra el 
	private Lugar lugarActual;		// policia en determinado momento
	
	//declaracion de metodos:
	
	//constructor
	public Policia(){
		this.tiempoFinal = new Tiempo();
		this.tiempoInicial = new Tiempo();
		//falta inicializar el grado del policia
	}
	
	public void visitarLugar(Lugar unLugar){
		this.lugarActual = unLugar;
		//falta descontar tiempo y etc.
		
	};
	
	public void viajarA(Ciudad destino){
		this.ciudadActual = destino;
		//flata descontar tiempo y etc.
		
	};
	
	public Grado obtenerGrado(){
		return this.grado;
	};
	
	public Ciudad obtenerCiudadActual(){
		return this.ciudadActual;
	};
	
	public boolean debeDormir(){
		//implementar!!
		return true;
	};
	
	
}
