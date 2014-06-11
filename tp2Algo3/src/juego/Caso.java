package juego;

import jdk.nashorn.internal.objects.NativeArray;
import personajes.*;
import geografico.*;
import elementosDelJuego.*;
import descripciones.*;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class Caso {

	private Ciudad ciudadDelRobo;
	private ObjetoRobado objetoRobado;
	private Sospechoso ladron;
    private Tiempo tiempoFinal;
    private Tiempo tiempoInicial;
    private Tiempo tiempoActual;
    private Lugar ubicacionLadron;
    private RecorridoLadron recorridoLadron;

    public Caso(ArrayList<Ciudad> ciudades, Grado grado, ArrayList<ObjetoRobado> objetos, ArrayList<Sospechoso> sospechosos) {
        objetoRobado=new ObjetoRobado(Valor.COMUN);
        ladron=sospechosos.get((int)(Math.random()*sospechosos.size()+0));//Elige un ladron al azar  enter los sospechosos.

        //Segun el grado del policia tenemos que armar un recorrido de 4,5 o 7 paises.
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

    private void plantarPistas() {
        for (Ciudad ciudad : recorridoLadron.obtenerCiudades()) {
            //Para que esto quede mejor tendriamos que crear un iterador para recorridoLadro
            Ciudad ciudadSiguiente = recorridoLadron.obtenerCiudadSiguiente(ciudad);
            CreadorDePistas creadorDePistas = new CreadorDePistas(ciudadSiguiente, ladron);
            ArrayList<Lugar> lugares = ciudad.obtenerLugaresDisponibles();
            for (Lugar lugar : lugares)
                lugar.plantarPista(creadorDePistas.crearNuevaPista(lugar.obtenerTipo()));
        }
    }

}
