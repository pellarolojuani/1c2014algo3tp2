package modelo.elementosDelJuego;

import modelo.geografico.Ciudad;

public class ObjetoRobado {

    private Valor valor;
	private Ciudad ciudadOrigen;

    public ObjetoRobado(Valor valordelobjeto, Ciudad ciudadOrigen) {
        this.valor = valordelobjeto;
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
