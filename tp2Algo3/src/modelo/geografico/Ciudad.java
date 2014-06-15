package modelo.geografico;

import modelo.descripciones.Descripcion;

import java.util.*;

public class Ciudad {

	private ArrayList<Lugar> lugares;
    private ArrayList<Ciudad> ciudades;
    private String nombre;
    private ArrayList<Color> bandera;
    private Moneda moneda;

    public Ciudad() {
        this.lugares = new ArrayList<Lugar>();
        this.ciudades = new ArrayList<Ciudad>();
        this.nombre = "";
        this.bandera = new ArrayList<Color>();
    }
    
    public Ciudad(String nombre){
    	this.nombre = nombre;
        this.lugares = new ArrayList<Lugar>();
        this.ciudades = new ArrayList<Ciudad>();
        this.nombre = "";
        this.bandera = new ArrayList<Color>();
    }

    public ArrayList<Ciudad> obtenerCiudadesDestinoDisponibles() {
        return ciudades;
    }

	public ArrayList<Lugar> obtenerLugaresDisponibles(){
		return lugares;
	}
	
	public int distanciaA(Ciudad unaCiudad){return 0;}

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public void setBandera(ArrayList<Color> colores) {
    	this.bandera = colores;
    }

    public void setMoneda(Moneda moneda) {
    	this.moneda = moneda;
    }

    public void setPuntoInteres(PuntoInteres monteHuascaran) {}

    public void setArte(Arte arte) {}

    public void setIndustrias(Industria moda) {}

    public void agregarLugar(Lugar lugar) {
        lugares.add(lugar);
    }
}
