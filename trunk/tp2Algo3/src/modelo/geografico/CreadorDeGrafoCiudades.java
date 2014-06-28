package modelo.geografico;

import java.util.List;

public class CreadorDeGrafoCiudades{
	private Grafo grafociudades;
	
	public CreadorDeGrafoCiudades(int cantidadciudades){
		this.grafociudades = new Grafo(cantidadciudades);
		
	grafociudades.agregarciudadvisitable(0, 1);
    grafociudades.agregarciudadvisitable(0, 2);
    grafociudades.agregarciudadvisitable(0, 3);
    grafociudades.agregarciudadvisitable(1, 0);
    grafociudades.agregarciudadvisitable(1, 4);
    grafociudades.agregarciudadvisitable(1, 5);
    grafociudades.agregarciudadvisitable(2, 0);
    grafociudades.agregarciudadvisitable(2, 6);
    grafociudades.agregarciudadvisitable(2, 7);
    grafociudades.agregarciudadvisitable(3, 0);
    grafociudades.agregarciudadvisitable(3, 8);
    grafociudades.agregarciudadvisitable(3, 9);
    grafociudades.agregarciudadvisitable(4, 1);
    grafociudades.agregarciudadvisitable(4, 10);
    grafociudades.agregarciudadvisitable(4, 11);
    grafociudades.agregarciudadvisitable(5, 1);
    grafociudades.agregarciudadvisitable(5, 11);
    grafociudades.agregarciudadvisitable(6, 2);
    grafociudades.agregarciudadvisitable(6, 11);
    grafociudades.agregarciudadvisitable(6, 12);
    grafociudades.agregarciudadvisitable(7, 2);
    grafociudades.agregarciudadvisitable(7, 12);
    grafociudades.agregarciudadvisitable(8, 3);
    grafociudades.agregarciudadvisitable(8, 12);
    grafociudades.agregarciudadvisitable(8, 13);
    grafociudades.agregarciudadvisitable(9, 3);
    grafociudades.agregarciudadvisitable(9, 13);
    grafociudades.agregarciudadvisitable(10, 4);
    grafociudades.agregarciudadvisitable(10, 11);
    grafociudades.agregarciudadvisitable(11, 4);
    grafociudades.agregarciudadvisitable(11, 5);
    grafociudades.agregarciudadvisitable(11, 6);
    grafociudades.agregarciudadvisitable(11, 10);
    grafociudades.agregarciudadvisitable(12, 6);
    grafociudades.agregarciudadvisitable(12, 7);
    grafociudades.agregarciudadvisitable(12, 8);
    grafociudades.agregarciudadvisitable(13, 8);
    grafociudades.agregarciudadvisitable(13, 9);
	}
	
	public List<Integer> obtenerciudadesvisitables(int v) {
        return this.grafociudades.obtenerciudadesvisitables(v); 
	}

	public Grafo obtenergrafo(){
		return this.grafociudades;
	}
}
