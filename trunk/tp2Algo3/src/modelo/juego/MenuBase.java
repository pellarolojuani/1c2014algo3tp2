package modelo.juego;
import java.util.Scanner;

public class MenuBase{

    public static void main(String args[]){
    	
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("AlgoThieft");
    	System.out.println("1. Comenzar juego");
    	System.out.println("2. Salir");
    	
    	boolean quit = false;
        int opcion;
        do {
	        	
	        System.out.println("Por favor elija una de las siguientes opciones");
	        opcion = in.nextInt();
	        
	        switch (opcion) {
	        
	        case 1: 
	        	Juego juego = new Juego(null, null, null);
	            break;
	        
	        case 2: 
                quit = true;
                break;
	        
	        default: System.out.println("Eleccion Invalida.");
	        
	        }
	    } while (!quit); 
        System.out.println("goodbye Gadget!!");
        
    }
}
