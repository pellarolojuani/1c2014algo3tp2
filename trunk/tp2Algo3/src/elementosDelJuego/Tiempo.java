package elementosDelJuego;

//El tiempo es un elemento discreto en el juego
public class Tiempo {

	//declaracion de atributos:
	int horas;
	
	//declaracion de metodos:
	
	//constructor
	public Tiempo(){
		this.horas = 0; //Indicar la hora inicial del juego
	}
	
	public void aumentarHoras(int cantidadHoras){
		this.horas += cantidadHoras;
	}
}
