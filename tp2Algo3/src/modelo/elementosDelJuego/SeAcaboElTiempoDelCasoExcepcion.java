package modelo.elementosDelJuego;

public class SeAcaboElTiempoDelCasoExcepcion extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String AvisoAlJugador(){
		return ("GAME OVER, partida nueva? S o N?");
	}
}
