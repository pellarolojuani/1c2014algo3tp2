package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;
import modelo.personajes.Sospechoso;

/**
 * Created by chris on 06/06/2014.
 */
public class PistaBanco extends Pista {

    public PistaBanco(Ciudad ciudadSiguiente, Sospechoso ladron) {
        pista="vi a alguien sospechoso que fue a una ciudad donde la moneda es "+ ciudadSiguiente.obtenerMoneda();
    }
}
