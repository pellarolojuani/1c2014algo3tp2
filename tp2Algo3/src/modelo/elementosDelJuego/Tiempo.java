package modelo.elementosDelJuego;


public class Tiempo {

	private static final int MAX_HS_PARA_CASO = 154;//Domingo 17hs contando desde el lunes a las 7 Hs
	private static final int NUM_HS_PARA_DORMIR = 8;
	private static final int HORA_COMIENZO_CASO = 7;//Comienza lunes 7 Hs
	private static final int HORA_SUENIO = 22; //Quiero que duerma a las 22hs


	private static int horas;
	private static int horaSuenio;

	public Tiempo(){}
	
	public static void aumentarHoras(int cantidadHoras) {
		
		horas += cantidadHoras;
		
		if( horas > MAX_HS_PARA_CASO )
			throw new SeAcaboElTiempoDelCasoExcepcion();
		
		//LA CONDICION EXTRA ASEGURA QUE PUEDA DORMIR EN VIAJES NOCTURNOS LARGOS
		if( horas >= horaSuenio && (horas-horaSuenio) < NUM_HS_PARA_DORMIR )
			{
				//IMPRIMIR "DURMIENDO......"
				horas += NUM_HS_PARA_DORMIR;
				horaSuenio += 24; //Le va a dar suenio el dia siguiente
			}
		else if( horas > horaSuenio )
			horaSuenio += 24;
		
	}
	public static int getTiempo(){
		
		return horas;
	}
	public static String tiempoComoString(){
		
		// Como no empieza el lunes a las 0Hs, necesito sumarle la hora
		// del comienzo del caso para que al dividir por 24 me de el dia.
		// Igual para obtener la hora
		int auxDia = (horas + HORA_COMIENZO_CASO)/24;
		int auxHora = (horas + HORA_COMIENZO_CASO) % 24;
		
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
		horaSuenio = HORA_SUENIO - HORA_COMIENZO_CASO ;
		
	}
}