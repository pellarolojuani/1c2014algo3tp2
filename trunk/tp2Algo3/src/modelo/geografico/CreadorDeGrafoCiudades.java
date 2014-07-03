package modelo.geografico;

import java.util.ArrayList;
import java.util.List;

public class CreadorDeGrafoCiudades {
    private Grafo grafociudades;

    public CreadorDeGrafoCiudades(ArrayList<Ciudad> ciudades, int cantidadciudades) {
        this.grafociudades = new Grafo(cantidadciudades);

        grafociudades.agregarCiudadVisitable(0, ciudades.get(1));
        grafociudades.agregarCiudadVisitable(0, ciudades.get(2));
        grafociudades.agregarCiudadVisitable(0, ciudades.get(3));
        grafociudades.agregarCiudadVisitable(1, ciudades.get(0));
        grafociudades.agregarCiudadVisitable(1, ciudades.get(4));
        grafociudades.agregarCiudadVisitable(1, ciudades.get(5));
        grafociudades.agregarCiudadVisitable(2, ciudades.get(0));
        grafociudades.agregarCiudadVisitable(2, ciudades.get(6));
        grafociudades.agregarCiudadVisitable(2, ciudades.get(7));
        grafociudades.agregarCiudadVisitable(3, ciudades.get(0));
        grafociudades.agregarCiudadVisitable(3, ciudades.get(8));
        grafociudades.agregarCiudadVisitable(3, ciudades.get(9));
        grafociudades.agregarCiudadVisitable(4, ciudades.get(1));
        grafociudades.agregarCiudadVisitable(4, ciudades.get(10));
        grafociudades.agregarCiudadVisitable(4, ciudades.get(11));
        grafociudades.agregarCiudadVisitable(5, ciudades.get(1));
        grafociudades.agregarCiudadVisitable(5, ciudades.get(11));
        grafociudades.agregarCiudadVisitable(6, ciudades.get(2));
        grafociudades.agregarCiudadVisitable(6, ciudades.get(11));
        grafociudades.agregarCiudadVisitable(6, ciudades.get(12));
        grafociudades.agregarCiudadVisitable(7, ciudades.get(2));
        grafociudades.agregarCiudadVisitable(7, ciudades.get(12));
        grafociudades.agregarCiudadVisitable(8, ciudades.get(3));
        grafociudades.agregarCiudadVisitable(8, ciudades.get(12));
        grafociudades.agregarCiudadVisitable(8, ciudades.get(13));
        grafociudades.agregarCiudadVisitable(9, ciudades.get(3));
        grafociudades.agregarCiudadVisitable(9, ciudades.get(13));
        grafociudades.agregarCiudadVisitable(10, ciudades.get(4));
        grafociudades.agregarCiudadVisitable(10, ciudades.get(11));
        grafociudades.agregarCiudadVisitable(11, ciudades.get(4));
        grafociudades.agregarCiudadVisitable(11, ciudades.get(5));
        grafociudades.agregarCiudadVisitable(11, ciudades.get(6));
        grafociudades.agregarCiudadVisitable(11, ciudades.get(10));
        grafociudades.agregarCiudadVisitable(12, ciudades.get(6));
        grafociudades.agregarCiudadVisitable(12, ciudades.get(7));
        grafociudades.agregarCiudadVisitable(12, ciudades.get(8));
        grafociudades.agregarCiudadVisitable(13, ciudades.get(8));
        grafociudades.agregarCiudadVisitable(13, ciudades.get(9));
    }

    public List<Ciudad> obtenerCiudadesVisitables(int v) {
        return this.grafociudades.obtenerCiudadesVisitables(v);
    }

}
