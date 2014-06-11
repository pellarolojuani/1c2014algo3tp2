package personajes;

import elementosDelJuego.*;
import geografico.*;

import java.util.ArrayList;
import java.util.Calendar;

public class Policia {
	private Grado grado;
	private Ciudad ciudadActual;
	private Lugar lugarActual;
    private String nombre;

	public Policia(String nombre, Grado grado){
		this.grado=grado;
        this.nombre=nombre;
	}
	
	public void visitarLugar(Lugar lugar) throws NoSePuedeVisitarLugarExcepcion {
        if(ciudadActual.obtenerLugaresDisponibles().contains(lugar)) {
            this.lugarActual = lugar;
            lugar.visitar();
        }
        else throw new NoSePuedeVisitarLugarExcepcion();
	};
	
	public void viajarA(Ciudad destino){
		this.ciudadActual = destino;
	};

    public void asignarNuevoCasoEn(Ciudad ciudad){
        this.ciudadActual=ciudad;
        //Esto lo manda a la ciudad del robo directamente, sin sacar tiempo
    }
	
	public Grado obtenerGrado(){
		return this.grado;
	};
	
	public Ciudad obtenerCiudadActual(){
		return this.ciudadActual;
	};
	
	public boolean debeDormir(){
		return true;
	};

    public void dormir(Tiempo tiempo){
    	tiempo.aumentarHoras(8);
    }
}
