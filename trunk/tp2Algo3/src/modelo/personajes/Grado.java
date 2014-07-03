package modelo.personajes;

public enum Grado {
    NOVATO(900, 5), DETECTIVE(1100, 10), INVESTIGADOR(1300, 15), SARGENTO(1500, 0);
    private int velocidad;
    private int nroArrestosParaPromover;

    Grado(int velocidad, int nroArrestosParaPromover) {
        this.velocidad = velocidad;
        this.nroArrestosParaPromover = nroArrestosParaPromover;
    }

    public Grado getNext() {
        if (this.ordinal() < Grado.values().length - 1) {
            return Grado.values()[this.ordinal() + 1];
        } else {
            return this;
        }
    }

    public int obtenerVelocidad() {
        return velocidad;
    }

    public int arrestosParaPromover() {
        return nroArrestosParaPromover;
    }
}
