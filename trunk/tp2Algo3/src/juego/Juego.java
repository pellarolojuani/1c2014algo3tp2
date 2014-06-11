package juego;
import elementosDelJuego.CuartelGeneral;
import elementosDelJuego.ObjetoRobado;
import elementosDelJuego.Tiempo;
import geografico.Ciudad;
import personajes.*;

import java.util.ArrayList;

public class Juego {

	private Caso caso;
	private Policia policia;
    private ArrayList<ObjetoRobado> objetos;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Sospechoso> sospechosos;
    private CuartelGeneral cuartelGeneral;
    private Tiempo tiempo; 

    public Juego(ArrayList<ObjetoRobado> objetos, ArrayList<Ciudad> ciudades, ArrayList<Sospechoso> sospechosos) {
        this.objetos = objetos;
        this.ciudades = ciudades;
        this.sospechosos = sospechosos;
        this.cuartelGeneral=new CuartelGeneral();
        this.cuartelGeneral.cargarSospechosos(sospechosos);
        this.tiempo = new Tiempo();
    }
    public void asignarPolicia(Policia policia){
        this.policia = policia;
    }
    public void crearCaso(){
        caso = new Caso(ciudades,policia.obtenerGrado(),objetos,sospechosos);
	}
    public Caso obtenerCaso() {
        return caso;
    }
}
