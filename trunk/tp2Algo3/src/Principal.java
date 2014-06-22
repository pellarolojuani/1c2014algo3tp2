import Vista.*;
import controlador.ControlMenu.*;

public class Principal {
	

	
	public static void main(String args[]){
		
		Controlador control = new Controlador();
		
		new Vista(control);
	}

}