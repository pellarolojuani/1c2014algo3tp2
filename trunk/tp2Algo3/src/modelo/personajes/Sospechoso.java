package modelo.personajes;

import modelo.descripciones.Descripcion;

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
}
