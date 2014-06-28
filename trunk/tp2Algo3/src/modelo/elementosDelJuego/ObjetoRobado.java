package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;

public class ObjetoRobado {

    private Valor valor;
	private Ciudad ciudadOrigen;
	private String descripcionObjeto;

    public ObjetoRobado(Ciudad ciudadOrigen, String descripcion) {
        this.ciudadOrigen = ciudadOrigen;
        this.descripcionObjeto = descripcion;
	}

	public String contenidoComoString(){

		return this.descripcionObjeto;
	
	}
    

	public Ciudad obtenerCiudadOrigen() {
		return this.ciudadOrigen;
	}

    public String obtenerDescripcion() {
        return descripcionObjeto;
    }
}
