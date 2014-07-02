package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;


public class PistaAeropuerto extends Pista {
	
    public PistaAeropuerto(Ciudad ciudadSiguiente, Sospechoso ladron) {
    	super(ladron);
        if(ciudadSiguiente==null){ this.pista = "El ladron esta cerca!";}
        
        else pista = this.auxTextoPistaCiudad(ciudadSiguiente) + this.auxTextoPistaLadron(ladron);
        
    }

        // Voy a querer manejar la dificultad de las pistas complejizando los siguientes m�todos
        // Voy a usar probabilidad
    	private String auxTextoPistaCiudad(Ciudad ciudad){
    		
    		return this.textoBanderaAeropuerto + ciudad.obtenerBandera();
    	}
    	
    	private String auxTextoPistaLadron(Sospechoso ladron){
    		
    		return this.textoSenia + ladron.seniaComoString();
    	}
    }
