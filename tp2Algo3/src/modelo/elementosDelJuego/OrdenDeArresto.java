package modelo.elementosDelJuego;

import modelo.personajes.Sospechoso;

public class OrdenDeArresto {

    private Sospechoso sospechoso;

    public OrdenDeArresto(Sospechoso sospechoso) {
        this.sospechoso = sospechoso;
    }

    public Sospechoso obtenerSospechoso() {
        return sospechoso;
    }
}