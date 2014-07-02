package modelo.elementosDelJuego;



public class Pista {

	public String pista;

	

    public Pista(String pista){
        this.pista=pista;
    }

	public Pista() {
		this.pista = "Lo siento, no he visto a nadie sospechoso.";
	}

	


	public String contenidoComoString() {
		return this.pista;
	}

	public Pista ampliarPista(Pista pistaLadron) {
		if(pistaLadron==null) return this;
		
		this.pista = this.pista + pistaLadron.contenidoComoString();
		return this;
	}
}
