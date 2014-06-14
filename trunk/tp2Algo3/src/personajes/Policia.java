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
	
	public Policia(String nombre){
		//si no se le indica grado, por defecto es NOVATO
		this.nombre = nombre;
		this.grado = Grado.NOVATO;
	}
	
	public void asignarCiudadActual(Ciudad unaCiudad){
		this.ciudadActual = unaCiudad;
	}
	
	public void visitarLugar(Lugar lugar) /*throws NoSePuedeVisitarLugarExcepcion */{
		TipoEdificio tipo = lugar.obtenerTipo();
		ArrayList<Lugar> lugares = ciudadActual.obtenerLugaresDisponibles();
		boolean esta = false;
		for (Lugar unLugar: lugares){
			if (unLugar.obtenerTipo() == tipo) esta = true;
		}
        if(esta) {
            this.lugarActual = lugar;
            lugar.visitar();
        }
        
       // else throw new NoSePuedeVisitarLugarExcepcion();
	};
	
	public Lugar getLugarActual(){
		return this.lugarActual;
	}
	
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
		
	// Promueve de grado al policia salvo que este sea de grado maximo
	public void promoverGrado(){
		this.grado = this.grado.getNext();
	}
	
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
