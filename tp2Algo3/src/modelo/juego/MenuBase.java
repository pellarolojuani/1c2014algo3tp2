package modelo.juego;
import java.io.File;
import java.util.Scanner;

import modelo.geografico.Ciudad;
import controlador.ControlXML.CreadorDeCiudades;

public class MenuBase{

    private static Scanner in;
	public static void main(String args[]){
    	

    	
    	in = new Scanner(System.in);
    	
    	System.out.println("AlgoThieft");
    	System.out.println("1. Comenzar juego");
    	System.out.println("3. CrearCiudades");
    	System.out.println("2. Salir");
    	
    	boolean quit = false;
        int opcion;
        do {
	        	
	        System.out.println("Por favor elija una de las opciones");
	        opcion = in.nextInt();
	        
	        Ciudad[] ciudadespartida;
			switch (opcion) {
	        
	        case 1: 
	        	new Juego(null, null, null);
	            break;
	        
	        case 2: 
                quit = true;
                break;
	        case 3: 

                CreadorDeCiudades a = new CreadorDeCiudades(4);
                ciudadespartida = a.obtenerCiudades();
                
                    System.out.println(ciudadespartida[1].getNombre());
               
	        
	        default: System.out.println("Eleccion Invalida.");
	        
	        }
	    } while (!quit); 
        System.out.println("goodbye Gadget!!");
        
    }
}
