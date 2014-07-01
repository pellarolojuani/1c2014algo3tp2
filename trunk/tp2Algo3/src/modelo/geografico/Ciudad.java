package modelo.geografico;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Ciudad {

	private ArrayList<Lugar> lugares;
    private ArrayList<Ciudad> ciudadesvisitables;
    private boolean ciudadvisitadaladron;
    private String nombre;
	private double Latitud;   
	private double Longitud;
	private String moneda;
	private String bandera;
	private String lugaresdeinteres;
	private String personaje;
	private String industria;
	private String fauna;
	private String idiomas;
	
    public Ciudad(String nombre, String bandera, String unamoneda, String lugaresdeinteres, String personaje, String industria, String fauna, String idiomas, ArrayList<Ciudad> ciudadesvisitables, ArrayList<Lugar> lugares,double unalatitud, double unalongitud){
    	this.nombre = nombre;
        this.lugares = lugares;
    	this.moneda = unamoneda;
    	this.lugaresdeinteres=lugaresdeinteres;
    	this.personaje=personaje;
    	this.industria=industria;
    	this.fauna=fauna;
    	this.idiomas=idiomas;
    	ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        this.ciudadesvisitables = ciudades;
        this.ciudadvisitadaladron = false;
        this.bandera = bandera;
        this.Latitud = unalatitud;
        this.Longitud = unalongitud;
    }

    
    // NO BORRAR
    public Ciudad(){
    	this.lugares = new ArrayList<Lugar>();
    	this.ciudadesvisitables = new ArrayList<Ciudad>();
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
    
    public void agregarCiudadVisitable(Ciudad unaCiudad){
    	this.ciudadesvisitables.add(unaCiudad);
    }
    
    public String getNombre(){
    	return this.nombre;
    }
    
    // NO BORRAR
 	public void setNombre(String nombreCiudad) {
 		this.nombre = nombreCiudad;
 		
 	}

 	public void agregarLugar(Lugar lugar) {

 		this.lugares.add(lugar);
 		
 	}

 	public void setLatitud(double latitud) {
 		this.Latitud = latitud;
 		
 	}

 	public void setLongitud(double longitud) {
 		this.Longitud = longitud;
 		
 	}

    public void agregarLugares(ArrayList<Lugar> lugares) {
        this.lugares=lugares;
    }

    public String obtenerMoneda() {
        return moneda;
    }

    public String obtenerIdioma() {
        return idiomas;
    }

    public String obtenerIndustria() {
        return industria;
    }

    public String obtenerBandera() {
        return bandera;
    }

    public Element serializar(Document doc) {
        Element elementoCiudad=doc.createElement("Ciudad");
        elementoCiudad.setAttribute("nombre",nombre);
        String ciudadesDestino="";
        for (int i = 0; i < ciudadesvisitables.size()-1; i++) {
            ciudadesDestino=ciudadesDestino+ciudadesvisitables.get(i).getNombre()+",";
        }
        ciudadesDestino=ciudadesDestino+ciudadesvisitables.get(ciudadesvisitables.size()-1).getNombre();
        elementoCiudad.setAttribute("ciudadesDestino",ciudadesDestino);
        for(Lugar l: lugares){
            elementoCiudad.appendChild(l.serializar(doc));
        }
        return elementoCiudad;
    }

    public Ciudad(String nombre, String bandera, String unamoneda, String lugaresdeinteres, String personaje, String industria, String fauna, String idiomas, ArrayList<Lugar> lugares,double unalatitud, double unalongitud){
        this.nombre = nombre;
        this.lugares = lugares;
        this.moneda = unamoneda;
        this.lugaresdeinteres=lugaresdeinteres;
        this.personaje=personaje;
        this.industria=industria;
        this.fauna=fauna;
        this.idiomas=idiomas;
        this.bandera = bandera;
        this.Latitud = unalatitud;
        this.Longitud = unalongitud;
    }


	public String obtenerPuntosInteres() {
		return this.lugaresdeinteres;
	}

    public Lugar obtenerLugar(TipoEdificio tipoEdificio) {
        for(Lugar l: lugares){
            if(l.obtenerTipo()==tipoEdificio)return l;
        }
        return null;
    }
}

