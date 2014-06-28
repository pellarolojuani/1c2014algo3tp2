package modelo.geografico;

import modelo.elementosDelJuego.Pista;

public class Lugar {

	private Pista pista;
    private boolean visitado;
    public TipoEdificio tipo;
    private Ciudad ciudad;
    private int numVisitas;

    public Lugar(TipoEdificio tipo, Ciudad ciudad) {
        this.tipo=tipo;
        this.ciudad = ciudad;
        this.visitado = false;
        this.numVisitas = 0;
        this.pista=new Pista();
    }

    public Lugar(TipoEdificio tipo) {
        this.tipo=tipo;
        this.numVisitas = 0;
        this.pista=new Pista();
	}

	public String obtenerPista(){
		
        return this.pista.contenidoComoString();
	}

    public void visitar(){
    	this.visitado=true;
    }

	public boolean fueVisitado(){
		return visitado;
	}

    public void plantarPista(Pista pista_){
    	
        this.pista = pista_;
        
    }

    public TipoEdificio obtenerTipo() {
        return this.tipo;
    }
	public int obtenerNumVisitas() {
		return numVisitas;
	}

	public void aumentarNumVisitas() {
		this.numVisitas += 1;
		
	}

    public Ciudad estaEnCiudad() {
        return ciudad;
    }
}
