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
        return this.valor;
    }
    
    public int obtenerCantidadciudades() {
        if (this.valor == Valor.COMUN) return 4;
        if (this.valor == Valor.VALIOSO) return 5;
        if (this.valor == Valor.MUY_VALIOSO) return 7;
		return 0;
    }

	public Ciudad obtenerCiudadOrigen() {
		return this.ciudadOrigen;
	}
}
