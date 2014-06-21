package modelo.personajes;

import modelo.elementosDelJuego.*;
import modelo.geografico.*;

import java.util.ArrayList;
import java.util.Calendar;

public class Policia {
	private Grado grado;
	private Ciudad ciudadActual;
	private Lugar lugarActual;
    private String nombre;
    private double velocidadKmHora; 


	public Policia(String nombre, Grado grado){
		this.grado=grado;
        this.nombre=nombre;
	}
	
	public Policia(String nombre){
		//si no se le indica grado, por defecto es NOVATO
		this.nombre = nombre;
		this.grado = Grado.NOVATO;
		this.velocidadKmHora = 900;
		
	}
	
	public void asignarCiudadActual(Ciudad unaCiudad){
		this.ciudadActual = unaCiudad;
	}
	
public void visitarLugar(Lugar lugar) /*throws NoSePuedeVisitarLugarExcepcion */{
		
		if( lugar.obtenerNumVisitas() == 0)
		{
			Tiempo.aumentarHoras(1);
			lugar.aumentarNumVisitas();
		}
		else if(lugar.obtenerNumVisitas() == 1)
			{
			lugar.aumentarNumVisitas();
			Tiempo.aumentarHoras(2);
			}
		else 
			{
			Tiempo.aumentarHoras(3);
			lugar.aumentarNumVisitas();
			}
			
//		TipoEdificio tipo = lugar.obtenerTipo();
//		ArrayList<Lugar> lugares = ciudadActual.obtenerLugaresDisponibles();
//		boolean esta = false;
//		for (Lugar unLugar: lugares){
//			if (unLugar.obtenerTipo() == tipo) esta = true;
//		}
//        if(esta) {
//            this.lugarActual = lugar;
//            lugar.visitar();
//        }
//        
       // else throw new NoSePuedeVisitarLugarExcepcion();
	};
	
	public Lugar getLugarActual(){
		return this.lugarActual;
	}
	
	public void viajarA(Ciudad destino){
		
		double auxTiempoViajeHs = this.ciudadActual.distanciaA(destino) / this.velocidadKmHora;
		this.ciudadActual = destino;
		 
		Tiempo.aumentarHoras( (int) Math.round(auxTiempoViajeHs) );

		
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


}
