package personajes;

import descripciones.*;

public class Ladron {
	
	//declaracion de atributos:
	private Descripcion descripcion;
	
	//declaracion de metodos:
	
	//constructor
	public Ladron(Descripcion unaDescripcion){
		this.descripcion = unaDescripcion;
	}
	
	public Descripcion getDescripcion(){
		return this.descripcion;
	}
	

}
