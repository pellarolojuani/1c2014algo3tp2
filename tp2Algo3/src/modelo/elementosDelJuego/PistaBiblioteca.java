package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaBiblioteca extends Pista {
	
    public PistaBiblioteca(Ciudad ciudadSiguiente, Sospechoso ladron)
    {
    	super(ladron);

    	if(ciudadSiguiente==null){pista="El ladron esta cerca!";}
            
        else pista = this.auxTextoPistaCiudad(ciudadSiguiente) + this.auxTextoPistaLadron(ladron);
        
}

    // Voy a querer manejar la dificultad de las pistas complejizando los siguientes métodos
    // Voy a usar probabilidad
	private String auxTextoPistaCiudad(Ciudad ciudad){
		
		return this.textoLugarinteres + ciudad.obtenerPuntosInteres();
	}
	
	private String auxTextoPistaLadron(Sospechoso ladron){
		
		return this.textoPelo + ladron.peloComoString();
	}
}