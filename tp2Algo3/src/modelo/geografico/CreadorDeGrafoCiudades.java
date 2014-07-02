package modelo.geografico;

import java.util.ArrayList;
import java.util.List;

public class CreadorDeGrafoCiudades{
	private Grafo grafociudades;
	
	public CreadorDeGrafoCiudades(ArrayList<Ciudad> ciudades, int cantidadciudades){
		this.grafociudades = new Grafo(cantidadciudades);
		
	grafociudades.agregarciudadvisitable(0, ciudades.get(1));
	grafociudades.agregarciudadvisitable(0, ciudades.get(2));
	grafociudades.agregarciudadvisitable(0, ciudades.get(3));
	grafociudades.agregarciudadvisitable(1, ciudades.get(0));
	grafociudades.agregarciudadvisitable(1, ciudades.get(4));
	grafociudades.agregarciudadvisitable(1, ciudades.get(5));
	grafociudades.agregarciudadvisitable(2, ciudades.get(0));
	grafociudades.agregarciudadvisitable(2, ciudades.get(6));
	grafociudades.agregarciudadvisitable(2, ciudades.get(7));
	grafociudades.agregarciudadvisitable(3, ciudades.get(0));
	grafociudades.agregarciudadvisitable(3, ciudades.get(8));
	grafociudades.agregarciudadvisitable(3, ciudades.get(9));
	grafociudades.agregarciudadvisitable(4, ciudades.get(1));
	grafociudades.agregarciudadvisitable(4, ciudades.get(10));
	grafociudades.agregarciudadvisitable(4, ciudades.get(11));
	grafociudades.agregarciudadvisitable(5, ciudades.get(1));
	grafociudades.agregarciudadvisitable(5, ciudades.get(11));
	grafociudades.agregarciudadvisitable(6, ciudades.get(2));
	grafociudades.agregarciudadvisitable(6, ciudades.get(11));
	grafociudades.agregarciudadvisitable(6, ciudades.get(12));
	grafociudades.agregarciudadvisitable(7, ciudades.get(2));
	grafociudades.agregarciudadvisitable(7, ciudades.get(12));
	grafociudades.agregarciudadvisitable(8, ciudades.get(3));
	grafociudades.agregarciudadvisitable(8, ciudades.get(12));
	grafociudades.agregarciudadvisitable(8, ciudades.get(13));
	grafociudades.agregarciudadvisitable(9, ciudades.get(3));
	grafociudades.agregarciudadvisitable(9, ciudades.get(13));
	grafociudades.agregarciudadvisitable(10, ciudades.get(4));
	grafociudades.agregarciudadvisitable(10, ciudades.get(11));
	grafociudades.agregarciudadvisitable(11, ciudades.get(4));
	grafociudades.agregarciudadvisitable(11, ciudades.get(5));
	grafociudades.agregarciudadvisitable(11, ciudades.get(6));
	grafociudades.agregarciudadvisitable(11, ciudades.get(10));
	grafociudades.agregarciudadvisitable(12, ciudades.get(6));
	grafociudades.agregarciudadvisitable(12, ciudades.get(7));
	grafociudades.agregarciudadvisitable(12, ciudades.get(8));
	grafociudades.agregarciudadvisitable(13, ciudades.get(8));
	grafociudades.agregarciudadvisitable(13, ciudades.get(9));
	}
	
	public List<Ciudad> obtenerciudadesvisitables(int v) {
        return this.grafociudades.obtenerciudadesvisitables(v); 
	}

}
