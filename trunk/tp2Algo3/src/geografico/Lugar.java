package geografico;

import elementosDelJuego.Pista;

public class Lugar {
	
	//declaracion de atributos:
	private Pista unaPista;
	
	//declaracion de metodos:
	
	//constructor
	public Lugar(){
		/*Aca inicializar los atributos de la clase*/
		this.unaPista = new Pista();
	};
	
	public Pista obtenerPista(){
		return unaPista;
	}
	
	public boolean fueVisitado(){
		//implementacion
		
		return true;
	}
	
	public boolean estaLadron(){
		//implementacion
		
		return true;		
	}

}
