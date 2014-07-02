package modelo.elementosDelJuego;

public class PistaHeridaPistola extends Pista {
	
	public PistaHeridaPistola(){
		
		this.pista = "¡Te han disparado";
	}
	
	public String contenidoComoString(){
		
		throw new SeDisparaArmaExcepcion();
	}

}
