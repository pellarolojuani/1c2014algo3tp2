package Vista;

import java.awt.*;
import java.util.*;

import modelo.juego.MenuBase;

public class VistaOrdenArresto extends Vista implements Observer{
		
	public VistaOrdenArresto(MenuBase unMenuBase){
		super (unMenuBase);
		this.setImagen("imagenesVista/ordenDeArresto.jpg");
		

	}
	
}