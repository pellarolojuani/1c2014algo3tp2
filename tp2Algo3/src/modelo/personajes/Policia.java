 package modelo.personajes;

 import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Policia extends Observable{
    private Grado grado;
	private Ciudad ciudadActual;
	private Lugar lugarActual;
    private String nombre;
    private int velocidadKmHora;
	private Scanner pedirnombre;
    private int cantidadDeArrestos;
    private CuartelGeneral cuartelGeneral;
    private String ultimaPista;


    public Policia(Observer juego, CuartelGeneral cuartelGeneral){
        this.cuartelGeneral=cuartelGeneral;
        this.addObserver(juego);
		pedirnombre = new Scanner(System.in);
		System.out.print("La interpol no tiene registrado su nombre, por favor ingreselo:");
		this.nombre = pedirnombre.next();
		//si no se le indica grado, por defecto es NOVATO
		this.grado = Grado.NOVATO;
		this.velocidadKmHora = 900;
	}
	
	public Policia(String nombre) {
		
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
        ultimaPista=lugar.obtenerPista();
        setChanged();
        notifyObservers(lugar); //Se notifica al juego de que el policia ha pedido pistas en un lugar de la ciudad de la que se encuentra.
	}
	
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
    }
	
	public Grado obtenerGrado(){
		return this.grado;
	};
		
	// Promueve de grado al policia salvo que este sea de grado maximo
	private void promoverGrado(){
        this.grado = this.grado.getNext();
		// Seteo la velocidad
		if(this.velocidadKmHora < 1500)
			this.velocidadKmHora += 200;
	}
	
	public Ciudad obtenerCiudadActual(){
		return this.ciudadActual;
	};
	
	public boolean debeDormir(){
		return true;
	}

	public Object obtenerVelocidadViaje() {
		
		return this.velocidadKmHora;
	};

    public void emitirOrdenDeArrestoPara(Sospechoso sospechoso){
        cuartelGeneral.emitirOrdenDeArrestoPara(sospechoso);
    }

    public String obtenerUltimaPista() {
        return ultimaPista;
    }

    public CuartelGeneral obtenerCuartelGeneral() {
        return cuartelGeneral;
    }
}
