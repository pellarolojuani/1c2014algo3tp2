package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaAeropuerto extends Pista {
	
    public PistaAeropuerto(Ciudad ciudadSiguiente, Sospechoso ladron) {
    	super("XML");
        if(ciudadSiguiente==null){ this.pista = "El ladron esta cerca!";}
        
        else pista = this.auxTextoPistaCiudad(ciudadSiguiente) + this.auxTextoPistaLadron(ladron);
        
    }

        // Voy a querer manejar la dificultad de las pistas complejizando los siguientes métodos
        // Voy a usar probabilidad
    	private String auxTextoPistaCiudad(Ciudad ciudad){
    		
    		return this.textoBanderaAeropuerto + ciudad.obtenerBandera();
    	}
    	
    	private String auxTextoPistaLadron(Sospechoso ladron){
    		
    		return this.textoSenia + ladron.seniaComoString();
    	}
    }
