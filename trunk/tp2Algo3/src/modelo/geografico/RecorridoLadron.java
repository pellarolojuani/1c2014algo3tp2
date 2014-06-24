package modelo.geografico;

import java.util.*;

public class RecorridoLadron {
    private ArrayList<Ciudad> recorrido;
    private Random Generadoralazar;
    private Lugar lugarFinal;

    public RecorridoLadron(ArrayList<Ciudad> ciudades, int numciudadesarecorrer, Grafo grafo) {

        this.recorrido = new ArrayList<Ciudad>();
        this.recorrido.add(ciudades.get(0));

        List<Integer> disponibles = new LinkedList<Integer>();
        disponibles = grafo.obtenerciudadesvisitables(0);

        int contador = 0;

        do {
            Generadoralazar = new Random();
            int indice = Generadoralazar.nextInt(disponibles.size());
            int numciudadsiguiente = (Integer) disponibles.get(indice);
            this.recorrido.add(ciudades.get(numciudadsiguiente));
            disponibles = grafo.obtenerciudadesvisitables(numciudadsiguiente);
            contador++;

        } while (contador < numciudadesarecorrer);
        ArrayList<Lugar> lugares = recorrido.get(recorrido.size()-1).obtenerLugaresDisponibles();
        lugarFinal = lugares.get((int) Math.random() * lugares.size());

    }

    public Ciudad obtenerCiudadFinal() {
        return this.recorrido.get(this.recorrido.size() - 1);
    }

    public Ciudad obtenerCiudadSiguiente(Ciudad ciudad) {
        for (int i = 0; i < this.recorrido.size() - 1; i++) {
            if (this.recorrido.get(i) == ciudad)
                return this.recorrido.get(i + 1);
        }
        return null;
    }

    public ArrayList<Ciudad> obtenerCiudades() {
        return this.recorrido;
    }

    public boolean contiene(Ciudad ciudad) {
        return recorrido.contains(ciudad);
    }

    public Lugar obtenerLugarFinal() {
        return lugarFinal;
    }

    public Ciudad obtenerCiudadDelRobo() {
        return recorrido.get(0);
    }
}