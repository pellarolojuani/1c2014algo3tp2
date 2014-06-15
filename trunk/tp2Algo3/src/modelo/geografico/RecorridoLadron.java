package modelo.geografico;

import java.util.ArrayList;

/**
 * Created by chris on 06/06/2014.
 */
public class RecorridoLadron {
    private ArrayList<Ciudad> recorrido;

    public RecorridoLadron(ArrayList<Ciudad> recorrido) {
        this.recorrido = recorrido;
    }
    public Ciudad obtenerCiudadFinal(){
        return recorrido.get(recorrido.size()-1);
    }
    public Ciudad obtenerCiudadSiguiente(Ciudad ciudad){
        return null;
    }
    public ArrayList<Ciudad> obtenerCiudades(){return recorrido;}
}
