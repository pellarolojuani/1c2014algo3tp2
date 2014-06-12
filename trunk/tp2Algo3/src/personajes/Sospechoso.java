package personajes;

import descripciones.Descripcion;

/**
 * Created by chris on 06/06/2014.
 */
public class Sospechoso {
    private Descripcion descripcion;

    public Sospechoso(Descripcion descripcion) {
        this.descripcion = descripcion;
    }

    public Descripcion obtenerDescripcion() {
        return descripcion;
    }
    
    public boolean describeLoMismo(Descripcion unaDescripcion){
    	return ( this.descripcion.puedeSerIgualA(unaDescripcion) );
    }
}
