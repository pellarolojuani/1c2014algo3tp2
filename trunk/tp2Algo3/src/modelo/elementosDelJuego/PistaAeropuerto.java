package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaAeropuerto extends Pista {
	
    public PistaAeropuerto(Ciudad ciudadSiguiente, Sospechoso ladron) {
        pista="Vi a alguien sospechoso viajando a una ciudad que tiene la bandera "+ciudadSiguiente.obtenerBandera();

    }
}
