package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import modelo.juego.MenuBase;

public class VistaPrincipalConConsola extends Vista implements Observer{
	
	private Panel panel;
	

	
	public VistaPrincipalConConsola(MenuBase unMenuBase){
		super (unMenuBase);
		this.setImagen("imagenesVista/mapa.jpg");
		
		
		panel = new Panel(); 
		Button primerBoton = new Button("Viajar a otra Ciudad");
		Button segundoBoton = new Button("Visitar un Lugar");
		Button tercerBoton = new Button("Emitir Orden de Arresto");
		
		panel.add(primerBoton); 
		panel.add(segundoBoton); 
		panel.add(tercerBoton); 
		
		add("South", panel);
		panel.setVisible(true);
		
	}

	
	/*
	public void paintComponent(Graphics g){
	
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource(this.nombreVista));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height - 100, null);
	
		this.setVisible(true);

		ImageIcon imagenConsola = new ImageIcon(getClass().getResource("imagenesVista/consola.jpg"));
		g.drawImage(imagenConsola.getImage(),0,tamanio.height - 100,tamanio.width, 100, null);
		
		this.setVisible(true);
		

	}
*/	

	
	public void imprimirTexto(String texto){
		JLabel label = new JLabel();
		Dimension dimension = new Dimension();
		dimension.height = 100;
		dimension.width = getSize().width;
		label.setSize(dimension);
		label.setText(texto);
		this.add("South", label);
		this.setVisible(true);
		
	}

}