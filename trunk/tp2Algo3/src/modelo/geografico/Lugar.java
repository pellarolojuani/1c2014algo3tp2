package modelo.geografico;

import elementosDelJuego.Pista;
import personajes.Sospechoso;

public class Lugar {

	Pista pista;
    private boolean visitado;
    private TipoEdificio tipo;

    public Lugar(TipoEdificio tipo) {
        this.tipo=tipo;
        this.visitado = false;
    }

    public String obtenerPista(){
        return pista.contenidoComoString();
	}

    public void visitar(){
    	this.visitado=true;
    }

	public boolean fueVisitado(){
		return visitado;
	}

    public void plantarPista(Pista pista){
        this.pista=pista;
    }

    public TipoEdificio obtenerTipo() {
        return tipo;
    }
    
}
