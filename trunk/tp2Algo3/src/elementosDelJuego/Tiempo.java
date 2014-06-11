package elementosDelJuego;

//Estaria bueno hacer que Tiempo sea unico (singleton), asi cuando viajamos podemos 'manejarlo' desde ahi,
// cuando emitimos una orden de arresto, etc.
public class Tiempo {

	int horas;

	public Tiempo(){
		this.horas = 0;
	}
	public void aumentarHoras(int cantidadHoras){
		this.horas += cantidadHoras;
	}
	public int getTiempo(){
		return this.horas;
	}
	public String tiempoComoSring(){
		//deberiamos imprimir dia y hora segun el valor del atributo tiempo
		String fecha = "";
		//TODO
		return fecha;
	}
}
