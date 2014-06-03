package geografico;

import java.util.*;

public class Ciudad {
	
	//declaracion de atributos:
	ArrayList<Lugar> lugares;
	
	//declaracion de metodos:
	
	//constructor
	public Ciudad(){
		this.lugares = new ArrayList<Lugar>();
	}
	
	public String obtenerCiudadesDestinoDisponibles(){
		//No estoy seguro del tipo de lo que devuelve este metodo. CORREGIR!
		//inicializo un elemento para que devuelva el metodo y no me tire error
		String ciudades = new String();
			//IMPLEMENTAR
		
		return ciudades;
	}
	
	public ArrayList<Lugar> obtenerLugaresDisponibles(){
		
		ArrayList<Lugar> lugaresDisponibles = new ArrayList<Lugar>();
		
		return lugaresDisponibles;
	}
	
	public int distanciaA(Ciudad unaCiudad){
		//obtener distancia entre ciudades de google maps
		int distancia = 0;
		
		return distancia;
	}
}
