package Vista;

import java.awt.*;
import java.util.*;

import modelo.juego.MenuBase;

public class VistaPistas extends Vista implements Observer{
	
	private Panel panel;
		
	public VistaPistas(MenuBase unMenuBase){
		super (unMenuBase);
		this.setImagen("imagenesVista/pistas.jpg");
		
		panel = new Panel(); 
		Button lugar1 = new Button();
		
		Button lugar2 = new Button();
		
		Button lugar3 = new Button();
				
		panel.add(lugar1); 
		panel.add(lugar2); 
		panel.add(lugar3); 
		
		add("South", panel);
		panel.setVisible(true);
		
	}
	
}