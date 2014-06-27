 package modelo.personajes;

 import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;

import java.util.Observable;

public class Policia extends Observable{
    private Grado grado;
	private Ciudad ciudadActual;
    private String nombre;
    private int velocidadKmHora;
    private String ultimaPista;
    private int nroArrestos;


    public Policia(){
		nombre="unNombre";
		grado = Grado.NOVATO;
		velocidadKmHora=grado.obtenerVelocidad();
	}
	
    public void visitarLugar(Lugar lugar){
		
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
        CuartelGeneral.getInstance().notificarVisitaA(lugar);
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

	public void promoverGrado(){
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
        CuartelGeneral.getInstance().emitirOrdenDeArrestoPara(sospechoso);
    }

    public String obtenerUltimaPista() {
        return ultimaPista;
    }

    public void arrestarLadron() {
        nroArrestos++;
    }

    public int obtenerNroArrestos(){
        return nroArrestos;
    }
}
