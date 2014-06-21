package modelo.geografico;

import modelo.elementosDelJuego.Pista;
import modelo.personajes.Sospechoso;

public class Lugar {

	Pista pista;
    private boolean visitado;
    public String tipo;
    private int numVisitas;

    public Lugar(String tipo) {
        this.tipo=tipo;
        this.visitado = false;
        this.numVisitas = 0;
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

    public String obtenerTipo() {
        return this.tipo;
    }
	public int obtenerNumVisitas() {
		return numVisitas;
	}

	public void aumentarNumVisitas() {
		this.numVisitas += 1;
		
	}
    
}
