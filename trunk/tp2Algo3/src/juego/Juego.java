package juego;
import elementosDelJuego.ObjetoRobado;
import geografico.Ciudad;
import personajes.*;

import java.util.ArrayList;

public class Juego {
	
	//declaracion de atributos:
	private Caso caso;
	private Policia policia;
    private ArrayList<ObjetoRobado> objetos;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<Sospechoso> sospechosos;
    public Juego() {
        //Recibe los 'archivos' xml de ciudades, sospechosos y objetos.
        //Cuando creamos el juego, leemos los XML y creamos todas las ciudades, los objetos que pueden ser robados, y los sospechosos.
        //Despues llamamos a cargarCiudades, cargarObjetos, cargarSospechosos, cada uno con el archivo que corresponda.
    }

    public void cargarCiudades() {}
    public void cargarObjetos() {}
    public void cargarSospechosos() {}

    public void crearCaso(Policia unPolicia){
		this.policia= unPolicia;

        //Al caso lo creamos dandole todas las ciudades, y el grado del policia y la lista de objetos.
        this.caso=new Caso(ciudades,policia.obtenerGrado(),objetos,sospechosos);
	}
	
	public Caso mostrarCaso(){
		return this.caso;
	}

    public Caso obtenerCaso() {
        return caso;
    }
}
