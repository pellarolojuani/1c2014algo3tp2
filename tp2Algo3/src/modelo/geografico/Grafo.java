package modelo.geografico;

import java.util.*;

public class Grafo {
		
    //Mapa de listas de adyacencia
    private Map<Integer,LinkedList<Ciudad>> ciudadesLista;

    public Grafo(int cantidadCiudades) {
        //Los nombres de los nodos son enteros consecutivos que comienzan con 0 y terminan en 13 (14 ciudades). 
    	int[] nodos = new int[cantidadCiudades];
    	for (int i = 0; i < nodos.length; ++i) {
            nodos[i] = i;
         }
    	
    	ciudadesLista = new HashMap<Integer, LinkedList<Ciudad>>();
        for (int i = 0; i < nodos.length; ++i) {
        	ciudadesLista.put(i, new LinkedList<Ciudad>());
        }
    }

    public void agregarCiudadVisitable(int v1, Ciudad v2) {
    	this.ciudadesLista.get(v1).add(v2);
    }

    public List<Ciudad> obtenerCiudadesVisitables(int v) {
        return this.ciudadesLista.get(v);
    }

}