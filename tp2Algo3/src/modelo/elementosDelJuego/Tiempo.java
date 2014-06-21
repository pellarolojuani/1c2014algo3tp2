package modelo.elementosDelJuego;

//Estaria bueno hacer que Tiempo sea unico (singleton), asi cuando viajamos podemos 'manejarlo' desde ahi,
// cuando emitimos una orden de arresto, etc.
public class Tiempo {



	private static int horas;
	private static int horaSueño;

	private Tiempo(){}
	
	public static void aumentarHoras(int cantidadHoras){
		
		horas += cantidadHoras;
		
		//LA CONDICION EXTRA ASEGURA QUE PUEDA DORMIR EN VIAJES NOCTURNOS LARGOS
		if( horas >= horaSueño && (horas-horaSueño) < 8 )
			{
				//IMPRIMIR "DURMIENDO......"
				horas += 8;
				horaSueño += 24;
			}
		else if( horas > horaSueño )
			horaSueño += 24;
		
	}
	public static int getTiempo(){
		
		return horas;
	}
	public String tiempoComoSring(){
		//deberiamos imprimir dia y hora segun el valor del atributo tiempo
		String fecha = "";
		//TODO
		return fecha;
	}
	public static void iniciar() {

		horas = 0;
		horaSueño = 15;
		
	}
}