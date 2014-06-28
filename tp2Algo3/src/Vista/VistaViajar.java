package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import modelo.juego.MenuBase;

public class VistaViajar extends Vista implements Observer{
	
	private Panel panel;
		
	public VistaViajar(MenuBase unMenuBase){
		super (unMenuBase);
		this.setImagen("imagenesVista/mapa.jpg");
		
		panel = new Panel(); 
		Button ciudad1 = new Button();
		
		Button ciudad2 = new Button();
		
		Button ciudad3 = new Button();
				
		panel.add(ciudad1); 
		panel.add(ciudad2); 
		panel.add(ciudad3); 
		
		add("South", panel);
		panel.setVisible(true);
		
	}
	
}