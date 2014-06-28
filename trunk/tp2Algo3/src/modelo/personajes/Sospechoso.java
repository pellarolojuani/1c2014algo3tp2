package modelo.personajes;

import modelo.descripciones.Descripcion;
import modelo.descripciones.Hobby;
import modelo.descripciones.Senia;

/**
 * Created by chris on 06/06/2014.
 */
public class Sospechoso {
	private String nombre;
    private Descripcion descripcion;

    public Sospechoso(Descripcion descripcion) {
        this.descripcion = descripcion;
    }
    
    public Sospechoso(String nombre, Descripcion descripcion) {
    	this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Descripcion obtenerDescripcion() {
        return descripcion;
    }
    
    public boolean describeLoMismo(Descripcion unaDescripcion){
    	return ( this.descripcion.puedeSerIgualA(unaDescripcion) );
    }
    public String getNombre(){
       return nombre;
    }

	public String seniaComoString() {
		
		return this.obtenerDescripcion().getSenia().toString();
	}

	public String hobbyComoString() {
		return this.obtenerDescripcion().getHobby().toString();

	}

	public String peloComoString() {
		return this.obtenerDescripcion().getPelo().toString();

	}
}
