package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaBolsa extends Pista {
	
    public PistaBolsa(Ciudad ciudadSiguiente, Sospechoso ladron) {
        if(ciudadSiguiente==null){pista="El ladron esta cerca!";}
       else pista="Vi a alguien sospechoso que fue a una ciudad cuya actividad mas importante es "+ciudadSiguiente.obtenerIndustria();

    }
}
