package modelo.geografico;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Ciudad {

    private ArrayList<Lugar> lugares;
    private ArrayList<Ciudad> ciudadesVisitables;
    private String nombre;
    private double Latitud;
    private double Longitud;
    private String moneda;
    private String bandera;
    private String lugaresDeInteres;
    private String personaje;
    private String industria;
    private String fauna;
    private String idiomas;

    public Ciudad(String nombre, String bandera, String unamoneda, String lugaresDeInteres, String personaje, String industria, String fauna, String idiomas, ArrayList<Lugar> lugares, double unalatitud, double unalongitud) {
        this.nombre = nombre;
        this.lugares = lugares;
        this.moneda = unamoneda;
        this.lugaresDeInteres = lugaresDeInteres;
        this.personaje = personaje;
        this.industria = industria;
        this.fauna = fauna;
        this.idiomas = idiomas;
        this.ciudadesVisitables = new ArrayList<Ciudad>();
        this.bandera = bandera;
        this.Latitud = unalatitud;
        this.Longitud = unalongitud;
    }

    public Ciudad() {
        this.lugares = new ArrayList<Lugar>();
        this.ciudadesVisitables = new ArrayList<Ciudad>();
    }

    public ArrayList<Ciudad> obtenerCiudadesDestinoDisponibles() {
        return this.ciudadesVisitables;
    }

    public ArrayList<Lugar> obtenerLugaresDisponibles() {
        return this.lugares;
    }

    public double distanciaA(Ciudad unaCiudad) {
        //Devuelve la distancia a una ciudad desde la ciudad actual

        return distancia(this.Latitud, this.Longitud, unaCiudad.Latitud, unaCiudad.Longitud);
    }

    private double distancia(double lat1, double lon1, double lat2, double lon2) {
        //Devuelve la distancia entre dos puntos dadas sus latitudes y longitudes.

        double radioTerrestre = 6372.795; //radio terrestre en km

        double distancia = Math.acos(Math.sin(lat1) * Math.sin(lat2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.cos(lon2 - lon1)) * radioTerrestre;

        return distancia;
    }

    public void agregarCiudadVisitable(Ciudad unaCiudad) {
        this.ciudadesVisitables.add(unaCiudad);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void agregarLugares(ArrayList<Lugar> lugares) {
        this.lugares = lugares;
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
        Element elementoCiudad = doc.createElement("Ciudad");
        elementoCiudad.setAttribute("nombre", nombre);
        String ciudadesDestino = "";
        for (int i = 0; i < ciudadesVisitables.size() - 1; i++) {
            ciudadesDestino = ciudadesDestino + ciudadesVisitables.get(i).getNombre() + ",";
        }
        ciudadesDestino = ciudadesDestino + ciudadesVisitables.get(ciudadesVisitables.size() - 1).getNombre();
        elementoCiudad.setAttribute("ciudadesDestino", ciudadesDestino);
        for (Lugar l : lugares) {
            elementoCiudad.appendChild(l.serializar(doc));
        }
        return elementoCiudad;
    }

    public String obtenerPuntosInteres() {
        return this.lugaresDeInteres;
    }

    public String obtenerPersonaje() {
        return this.personaje;
    }

    public String obtenerFauna() {
        return this.fauna;
    }

    public Lugar obtenerLugar(TipoEdificio tipoEdificio) {
        for (Lugar l : lugares) {
            if (l.obtenerTipo() == tipoEdificio) return l;
        }
        return null;
    }

    public void agregarLugar(Lugar unLugar) {
        this.lugares.add(unLugar);

    }

}

