package modelo.geografico;

import modelo.descripciones.Descripcion;
import java.util.*;
import java.lang.Math;

public class Ciudad {

	private ArrayList<Lugar> lugares;
    private ArrayList<Ciudad> ciudadesvisitables;
    private String nombre;
    private ArrayList<Color> bandera;
    private Moneda moneda;
	private double Latitud;   
	private double Longitud;
	
    public Ciudad(String nombre, ArrayList<Ciudad> ciudadesvisitables, ArrayList<Lugar> lugares, ArrayList<Color> bandera,double unalatitud, double unalongitud){
    	this.nombre = nombre;
        this.lugares = lugares;
        this.ciudadesvisitables = ciudadesvisitables;
        this.bandera = bandera;
        this.Latitud = unalatitud;
        this.Longitud = unalongitud;
    }

    public ArrayList<Ciudad> obtenerCiudadesDestinoDisponibles() {
        return this.ciudadesvisitables;
    }

	public ArrayList<Lugar> obtenerLugaresDisponibles(){
		return this.lugares;
	}
	
	//Devuelve la distancia a una ciudad desde la ciudad actual
	public double distanciaA(Ciudad unaCiudad){

		return distancia(this.Latitud, this.Longitud, unaCiudad.Latitud, unaCiudad.Longitud);
	}
	
	//Devuelve la distancia entre dos puntos dadas sus latitudes y longitudes.
	private double distancia(double lat1, double lon1, double lat2, double lon2) {
		
		double radioTerrestre = 6372.795; //radio terrestre en km
		
		double distancia = Math.acos(Math.sin(lat1)*Math.sin(lat2) + 
		                  Math.cos(lat1)*Math.cos(lat2) *
		                  Math.cos(lon2-lon1)) * radioTerrestre;

		return distancia;
	}
	
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
