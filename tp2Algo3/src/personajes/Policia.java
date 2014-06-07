package personajes;

import elementosDelJuego.*;
import geografico.*;

public class Policia {
	private Tiempo tiempoFinal;
	private Tiempo tiempoInicial;
	private Grado grado;
	private Ciudad ciudadActual;
	private Lugar lugarActual;

	public Policia(){
		this.tiempoFinal = new Tiempo();
		this.tiempoInicial = new Tiempo();
		this.grado=Grado.NOVATO;
	}
	
	public void visitarLugar(Lugar unLugar) throws NoSePuedeVisitarLugarExcepcion {
        if(ciudadActual.obtenerLugaresDisponibles().contains(unLugar)) {
            this.lugarActual = unLugar;
            unLugar.visitar();
        }
        else throw new NoSePuedeVisitarLugarExcepcion();
	};
	
	public void viajarA(Ciudad destino){
		this.ciudadActual = destino;
	};
	
	public Grado obtenerGrado(){
		return this.grado;
	};
	
	public Ciudad obtenerCiudadActual(){
		return this.ciudadActual;
	};
	
	public boolean debeDormir(){
		return true;
	};

    public void dormir(){}
}
