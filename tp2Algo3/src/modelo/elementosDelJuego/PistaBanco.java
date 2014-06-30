package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaBanco extends Pista {

    public PistaBanco(Ciudad ciudadSiguiente, Sospechoso ladron) {
    	
    	super(ladron);//Puede ser cualquier string, lo hice sólo para no pisar el constructor por defecto
    	
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