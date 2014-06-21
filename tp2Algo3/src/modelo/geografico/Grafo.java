package modelo.geografico;

import java.util.*;

public class Grafo {
		
    //Mapa de listas de adyacencia
    private Map<Integer,LinkedList<Integer>> ciudadeslista;

    public Grafo(int cantidadciudades) {
        //Los nombres de los nodos son enteros consecutivos que comienzan con 0 y terminan en 13 (14 ciudades). 
    	int[] nodos = new int[cantidadciudades];
    	for (int i = 0; i < nodos.length; ++i) {
            nodos[i] = i;
         }
    	
    	ciudadeslista = new HashMap<Integer, LinkedList<Integer>>();
        for (int i = 0; i < nodos.length; ++i) {
        	ciudadeslista.put(i, new LinkedList<Integer>());
        }
    }

    public void agregarciudadvisitable(int v1, int v2) {
    	this.ciudadeslista.get(v1).add(v2);
    }

    public List<Integer> obtenerciudadesvisitables(int v) {
        return this.ciudadeslista.get(v);
    }

}