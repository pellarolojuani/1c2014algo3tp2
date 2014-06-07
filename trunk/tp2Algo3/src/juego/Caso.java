package juego;

import jdk.nashorn.internal.objects.NativeArray;
import personajes.*;
import geografico.*;
import elementosDelJuego.*;
import descripciones.*;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class Caso {
	
	//declaracion de atributos:
	private Ciudad ciudadDelRobo;
	private ObjetoRobado objetoRobado;
	private Sospechoso ladron;
    private Tiempo tiempoFinal;
    private Tiempo tiempoInicial;
    private Tiempo tiempoActual;
    private Lugar ubicacionLadron;
	
	//declaracion de metodos:
	
	//constructor

    public Caso(ArrayList<Ciudad> ciudades, Grado grado, ArrayList<ObjetoRobado> objetos, ArrayList<Sospechoso> sospechosos) {
        /*Aca primero elegimos un objeto y un ladron al azar. Despues, segun el grado del policia
         inventamos un recorrido empezando desde la ciudad que dice en el XML para el objeto elegido.
         */
    }

    public Ciudad obtenerCiudadRobo(){
		return this.ciudadDelRobo;
	}
	
	public ObjetoRobado obtenerObjetoRobado(){
		return this.objetoRobado;
	}
	
	public Sexo obtenerSexoLadron(){
		return this.ladron.obtenerDescripcion().getSexo();
	}

    private void plantarPistas(){
        //Para cada ciudad del recorrido del ladron hacemos:
        Ciudad ciudad = new Ciudad();
        Ciudad ciudadSiguiente= new Ciudad();
        CreadorDePistas creadorDePistas=new CreadorDePistas(ciudadSiguiente,ladron);
        ArrayList<Lugar> lugares=ciudad.obtenerLugaresDisponibles();
        for(Lugar lugar: lugares)
                lugar.plantarPista(creadorDePistas.crearNuevaPista(lugar.obtenerTipo()));
    }

}
