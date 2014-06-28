package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaBolsa extends Pista {
	
    public PistaBolsa(Ciudad ciudadSiguiente, Sospechoso ladron) {
    	
    	super("XML");

        if(ciudadSiguiente==null){pista="El ladron esta cerca!";}
        
        else pista = this.auxTextoPistaCiudad(ciudadSiguiente) + this.auxTextoPistaLadron(ladron);
        
    }

        // Voy a querer manejar la dificultad de las pistas complejizando los siguientes métodos
    	private String auxTextoPistaCiudad(Ciudad ciudad){
    		
    		return this.textoMoneda + ciudad.obtenerMoneda();
    	}
    	
    	private String auxTextoPistaLadron(Sospechoso ladron){
    		
    		return this.textoHobby + ladron.hobbyComoString();
    	}
    }