package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;

public class ObjetoRobado {

    private Ciudad ciudadOrigen;
    private String descripcionObjeto;

    public ObjetoRobado(Ciudad ciudadOrigen, String descripcion) {
        this.ciudadOrigen = ciudadOrigen;
        this.descripcionObjeto = descripcion;
    }

    public Ciudad obtenerCiudadOrigen() {
        return this.ciudadOrigen;
    }

    public String obtenerDescripcion() {
        return descripcionObjeto;
    }
}
