package modelo.geografico;

import java.util.*;

public class Grafo {
		
    //Mapa de listas de adyacencia
    private Map<Integer,LinkedList<Ciudad>> ciudadeslista;

    public Grafo(int cantidadciudades) {
        //Los nombres de los nodos son enteros consecutivos que comienzan con 0 y terminan en 13 (14 ciudades). 
    	int[] nodos = new int[cantidadciudades];
    	for (int i = 0; i < nodos.length; ++i) {
            nodos[i] = i;
         }
    	
    	ciudadeslista = new HashMap<Integer, LinkedList<Ciudad>>();
        for (int i = 0; i < nodos.length; ++i) {
        	ciudadeslista.put(i, new LinkedList<Ciudad>());
        }
    }

    public void agregarciudadvisitable(int v1, Ciudad v2) {
    	this.ciudadeslista.get(v1).add(v2);
    }

    public List<Ciudad> obtenerciudadesvisitables(int v) {
        return this.ciudadeslista.get(v);
    }

}