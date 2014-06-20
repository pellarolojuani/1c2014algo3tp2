package modelo.geografico;

import java.util.ArrayList;
import java.util.*;

public class RecorridoLadron {
    private ArrayList<Ciudad> recorrido;

    public RecorridoLadron(ArrayList<Ciudad> recorrido) {
    	
    	
    }
    public Ciudad obtenerCiudadFinal(){
        return recorrido.get(recorrido.size()-1);
    }
    public Ciudad obtenerCiudadSiguiente(Ciudad ciudad){
        return null;
    }
    public ArrayList<Ciudad> obtenerCiudades(){return recorrido;}
}
