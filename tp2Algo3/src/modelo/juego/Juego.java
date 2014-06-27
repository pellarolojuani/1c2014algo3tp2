package modelo.juego;

import controlador.ControlXML.CreadorDeCiudades;
import controlador.ControlXML.CreadorDeSospechosos;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.Tiempo;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Lugar;
import modelo.personajes.Policia;

import java.util.Observable;
import java.util.Observer;


public class Juego implements Observer{

	private Caso caso;
	private Policia policia;
    private CreadorDeSospechosos creadorDeSospechosos;
    private CreadorDeCiudades creadorDeCiudades;
    
    //Creador del juego
    public Juego(){

        this.creadorDeSospechosos = new CreadorDeSospechosos();
        this.policia = new Policia();
        CuartelGeneral.getInstance().asignarAJuego(this);
        CuartelGeneral.getInstance().cargarSospechosos(creadorDeSospechosos.obtenerSospechosos());
        CuartelGeneral.getInstance().incorporarPolicia(policia);
        crearNuevoCaso();
	}


    public Caso obtenerCaso() {
        return caso;
    }

  
    public void update(Observable o, Object arg) {
        Lugar lugar= (Lugar) arg;
        if(o==CuartelGeneral.getInstance()){
            if(caso.ladronEstaEnCiudad(lugar.estaEnCiudad())){
                //Si esta en la ciudad del robo
                if (caso.ladronEstaEnLugar(lugar)){
                    if (CuartelGeneral.getInstance().fueEmitidaOrdenPara(caso.obtenerLadron())) {
                        System.out.println("---EL LADRON HA SIDO ARRESTADO!---");
                        CuartelGeneral.getInstance().notificarDeArrestoAPolicia();
                        crearNuevoCaso();
                    }
                    else System.out.println("---EL LADRON SE ESCAPO POR NO TENER LA ORDEN DE ARRESTO!---");
                }
            }
            else caso.plantarPistasEnCiudadSiguienteA(lugar.estaEnCiudad());
        }
    }

    private void crearNuevoCaso() {
        this.creadorDeCiudades = new CreadorDeCiudades();
        this.policia.asignarNuevoCasoEn(this.creadorDeCiudades.obtenerCiudades().get(0));
        this.caso = new Caso(this.creadorDeCiudades.obtenerCiudades(), Valor.COMUN,this.policia,this.creadorDeSospechosos.obtenerSospechosos());
        Tiempo.iniciar();
    }

    public Policia obtenerPolicia() {
        return policia;
    }
}
