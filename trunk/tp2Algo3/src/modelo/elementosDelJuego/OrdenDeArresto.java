package modelo.elementosDelJuego;

import modelo.personajes.Sospechoso;

/**
 * Created by chris on 24/06/2014.
 */
public class OrdenDeArresto {

    private Sospechoso sospechoso;

    public OrdenDeArresto(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }

    public Sospechoso obtenerSospechoso(){
        return sospechoso;
    }
}