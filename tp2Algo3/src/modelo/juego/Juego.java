package modelo.juego;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Tiempo;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Ciudad;
import modelo.personajes.*;
import controlador.ControlXML.CreadorDeCiudades;
import controlador.ControlXML.CreadorDeSospechosos;

import java.util.ArrayList;


public class Juego {

	private Caso caso;
	private Policia policia;
    private CreadorDeSospechosos sospechosos;
    private CuartelGeneral cuartelGeneral;
    private Tiempo tiempo; 
    private CreadorDeCiudades creadorciudades;
    
    //Creador del juego
    public Juego() {
        this.policia = new Policia();
        this.sospechosos = new CreadorDeSospechosos();
        this.cuartelGeneral=new CuartelGeneral();
        this.cuartelGeneral.cargarSospechosos(sospechosos.obtenerSospechosos());
        Tiempo.iniciar();
        this.creadorciudades = new CreadorDeCiudades();

        //asigno al policia la primera ciudad de la lista de ciudades.
        this.policia.asignarCiudadActual(this.creadorciudades.obtenerCiudades().get(0));

        //se crea el caso inicial, el constructor de juego solo crea el primer caso.	
        this.caso = new Caso(this.creadorciudades.obtenerCiudades(),this.creadorciudades.obtenergrafociudades(),Valor.COMUN,this.policia,this.sospechosos.obtenerSospechosos());
	}
    
    public Caso obtenerCaso() {
        return caso;
    }
    
}
