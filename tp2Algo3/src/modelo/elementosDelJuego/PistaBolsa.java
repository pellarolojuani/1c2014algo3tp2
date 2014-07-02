package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;


public class PistaBolsa extends Pista {
	
    public PistaBolsa(Ciudad ciudadSiguiente, Sospechoso ladron) {
    	
    	super(ladron);

        if(ciudadSiguiente==null){pista="El ladron esta cerca!";}
        
        else pista = this.auxTextoPistaCiudad(ciudadSiguiente) + this.auxTextoPistaLadron(ladron);
        
    }

        // Voy a querer manejar la dificultad de las pistas complejizando los siguientes m�todos
    	private String auxTextoPistaCiudad(Ciudad ciudad){
    		
    		return this.textoMoneda + ciudad.obtenerMoneda();
    	}
    	
    	private String auxTextoPistaLadron(Sospechoso ladron){
    		
    		return this.textoHobby + ladron.hobbyComoString();
    	}
    }