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
    private CuartelGeneral cuartelGeneral;
    private CreadorDeCiudades creadorDeCiudades;
    
    //Creador del juego
    public Juego() {

        this.creadorDeSospechosos = new CreadorDeSospechosos();
        this.cuartelGeneral=new CuartelGeneral();
        this.policia = new Policia(this,cuartelGeneral);
        this.cuartelGeneral.cargarSospechosos(creadorDeSospechosos.obtenerSospechosos());
        this.creadorDeCiudades = new CreadorDeCiudades();
        this.policia.asignarNuevoCasoEn(this.creadorDeCiudades.obtenerCiudades().get(0));
        this.caso = new Caso(this.creadorDeCiudades.obtenerCiudades(),this.creadorDeCiudades.obtenergrafociudades(),Valor.COMUN,this.policia,this.creadorDeSospechosos.obtenerSospechosos());
        Tiempo.iniciar();
	}


    public Caso obtenerCaso() {
        return caso;
    }

  
    public void update(Observable o, Object arg) {
        Lugar lugar= (Lugar) arg;
        if(o==this.policia){
            //El policia ha pedido pistas, entonces verificar que la ciudad actual esta en el recorrido del ladron.
            if(caso.ciudadTienePistas(lugar.estaEnCiudad())){
                if (caso.ladronEstaEnLugar(lugar) && cuartelGeneral.fueEmitidaOrdenPara(caso.obtenerLadron())){
                    //Avisarle al policia que arresto al sospechoso correcto.
                    cuartelGeneral.notificarDeArrestoAPolicia();
                }
                caso.plantarPistasEnCiudadSiguienteA(lugar.estaEnCiudad());
            }
        }
    }

    public Policia obtenerPolicia() {
        return policia;
    }
}
