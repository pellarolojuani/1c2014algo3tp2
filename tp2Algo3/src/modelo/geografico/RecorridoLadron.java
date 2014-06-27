package modelo.geografico;

import java.util.*;

public class RecorridoLadron {
    private ArrayList<Ciudad> recorrido;
    private Random generadorAlAzar;
    private Lugar lugarFinal;

    public RecorridoLadron(ArrayList<Ciudad> ciudades, int numCiudadesARecorrer) {

        this.recorrido = new ArrayList<Ciudad>();
        this.recorrido.add(ciudades.get(0));
        ArrayList<Ciudad> ciudadesDisponibles;
        Ciudad proximaCiudad;
        generadorAlAzar=new Random();
        for (int i = 0; i < numCiudadesARecorrer; i++) {
            ciudadesDisponibles=recorrido.get(i).obtenerCiudadesDestinoDisponibles();;
            do{
                //Elijo una ciudad al azar entre las disponibles. Si ya esta en el recorrido, busco otra.
               proximaCiudad=ciudadesDisponibles.get(generadorAlAzar.nextInt(ciudadesDisponibles.size()));
            }while(recorrido.contains(proximaCiudad));
            recorrido.add(proximaCiudad);
        }
        ArrayList<Lugar> lugares = recorrido.get(recorrido.size()-1).obtenerLugaresDisponibles();
        lugarFinal = lugares.get((int) Math.random() * lugares.size());
        System.out.println("EL LUGAR FINAL ES:   "+lugarFinal.obtenerTipo());
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