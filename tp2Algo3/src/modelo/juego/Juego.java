package modelo.juego;

import controlador.ControlXML.CreadorDeCiudades;
import controlador.ControlXML.CreadorDeObjetos;
import controlador.ControlXML.CreadorDeSospechosos;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Juego implements Observer{

    private final ArrayList<Ciudad> ciudades;
    private final ArrayList<Sospechoso> sospechosos;
    private final CreadorDeObjetos creadorDeObjetos;
    private Caso caso;
	private Policia policia;
    private CreadorDeSospechosos creadorDeSospechosos;
    private CreadorDeCiudades creadorDeCiudades;
    private ArrayList<ObjetoRobado> objetos;

    public Juego(){
        creadorDeSospechosos = new CreadorDeSospechosos();
        creadorDeCiudades = new CreadorDeCiudades();
        ciudades=creadorDeCiudades.obtenerCiudadesConVisitables();
        sospechosos=creadorDeSospechosos.obtenerSospechosos();
        creadorDeObjetos=new CreadorDeObjetos(ciudades);
        objetos=creadorDeObjetos.obtenerListaDeObjetos();
        policia = new Policia();
        CuartelGeneral.getInstance().asignarAJuego(this);
        CuartelGeneral.getInstance().cargarSospechosos(sospechosos);
        CuartelGeneral.getInstance().incorporarPolicia(policia);
        crearNuevoCaso();
	}

    public Caso obtenerCaso() {
        return caso;
    }

    public void update(Observable o, Object arg) {
        Lugar lugar= (Lugar) arg;
         if (caso.ladronEstaEnLugar(lugar)){
                    if (CuartelGeneral.getInstance().fueEmitidaOrdenPara(caso.obtenerLadron())) {
                        System.out.println("---EL LADRON HA SIDO ARRESTADO!---");
                        CuartelGeneral.getInstance().notificarDeArrestoAPolicia();
                        crearNuevoCaso();
                    }
                    else System.out.println("---EL LADRON SE ESCAPO POR NO TENER LA ORDEN DE ARRESTO!---");
         }
    }

    private void crearNuevoCaso() {
        caso = new Caso(ciudades, objetos, policia.obtenerGrado(),sospechosos);
        policia.asignarNuevoCasoEn(caso.obtenerCiudadRobo());
        Tiempo.iniciar();
    }

    public Policia obtenerPolicia() {
        return policia;
    }

    public Element serializar(Document doc){
        Element elementoJuego=doc.createElement("Juego");
        Element elementoCiudades=doc.createElement("Ciudades");
        for(Ciudad ciudad: ciudades){
            elementoCiudades.appendChild(ciudad.serializar(doc));
        }
        elementoJuego.appendChild(elementoCiudades);
        elementoJuego.appendChild(caso.serializar(doc));
        elementoJuego.appendChild(policia.serializar(doc));
        elementoJuego.appendChild(CuartelGeneral.getInstance().serializar(doc));
        elementoJuego.appendChild(Tiempo.serializar(doc));
        return elementoJuego;
        
    }

    public Juego(ArrayList<Sospechoso> sospechosos, ArrayList<ObjetoRobado> objetos,ArrayList<Ciudad> ciudades, Caso caso, Policia policia){
        this.creadorDeCiudades=null;
        this.creadorDeSospechosos=null;
        this.creadorDeObjetos=null;
        this.sospechosos=sospechosos;
        this.objetos=objetos;
        this.ciudades=ciudades;
        this.caso=caso;
        this.policia=policia;
        CuartelGeneral.getInstance().asignarAJuego(this);
        CuartelGeneral.getInstance().incorporarPolicia(policia);
    }
}
