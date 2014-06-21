package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;

public class ObjetoRobado {

    private Valor valor;
	private Ciudad ciudadOrigen;

    public ObjetoRobado(Valor valor) {
        this.valor = valor;
    }

    public ObjetoRobado(Valor comun, Ciudad ciudadOrigen) {
        this.valor = valor;
        this.ciudadOrigen = ciudadOrigen;
	}

	public String contenidoComoString(){
		//declaro variable para que no tire error el metodo
		//COMPLETAR!!
		String unContenido = new String();
		
		return unContenido;
	}

    public Valor obtenerValor() {
        return valor;
    }

	public Ciudad obtenerCiudadOrigen() {
		return this.ciudadOrigen;
	}
}
