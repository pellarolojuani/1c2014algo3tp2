package modelo.elementosDelJuego;

//Estaria bueno hacer que Tiempo sea unico (singleton), asi cuando viajamos podemos 'manejarlo' desde ahi,
// cuando emitimos una orden de arresto, etc.
public class Tiempo {



	private static int horas;
	private static int horaSuenio;

	private Tiempo(){}
	
	public static void aumentarHoras(int cantidadHoras){
		
		horas += cantidadHoras;
		
		//LA CONDICION EXTRA ASEGURA QUE PUEDA DORMIR EN VIAJES NOCTURNOS LARGOS
		if( horas >= horaSuenio && (horas-horaSuenio) < 8 )
			{
				//IMPRIMIR "DURMIENDO......"
				horas += 8;
				horaSuenio += 24;
			}
		else if( horas > horaSuenio )
			horaSuenio += 24;
		
	}
	public static int getTiempo(){
		
		return horas;
	}
	public static String tiempoComoSring(){
				
		int auxDia = (7+horas)/24;
		int auxHora = (7+horas) % 24;
		
		String tiempoString="";
		
		switch(auxDia){
		
		case 0 : tiempoString="Lunes ";break;
		case 1 : tiempoString="Martes ";break;
		case 2 : tiempoString="Miercoles ";break;
		case 3 : tiempoString="Jueves " ;break;
		case 4 : tiempoString="Viernes ";break;
		case 5 : tiempoString="Sabado ";break;
		case 6 : tiempoString="Domingo ";break;
		}
		
		return tiempoString + Integer.toString(auxHora) + " Hs";
		
		
		

	}
	public static void iniciar() {

		horas = 0;
		horaSuenio = 15;
		
	}
}