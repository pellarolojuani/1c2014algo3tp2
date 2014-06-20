package modelo.juego;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.personajes.*;
import controlador.ControlXML.CreadorDeCiudades;

import java.util.ArrayList;


public class Juego {

	private Caso caso;
	private Policia policia;
    private ArrayList<ObjetoRobado> objetos;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Sospechoso> sospechosos;
    private CuartelGeneral cuartelGeneral;
    private Tiempo tiempo; 
    private CreadorDeCiudades creadorciudades;
    
    public Juego(ArrayList<ObjetoRobado> objetos, ArrayList<Sospechoso> sospechosos) {
        this.objetos = objetos;
        this.sospechosos = sospechosos;
        this.cuartelGeneral=new CuartelGeneral();
        this.cuartelGeneral.cargarSospechosos(sospechosos);
        this.tiempo = new Tiempo();
        this.creadorciudades = new CreadorDeCiudades();
    }
    public void asignarPolicia(Policia policia){
        this.policia = policia;
        //asigno al policia la primera ciudad de la lista de ciudades.
        this.policia.asignarCiudadActual(this.ciudades.get(0));
    }
    public void crearCaso(){
        caso = new Caso(ciudades,policia.obtenerGrado(),objetos,sospechosos);
	}
    public Caso obtenerCaso() {
        return caso;
    }
    
}
