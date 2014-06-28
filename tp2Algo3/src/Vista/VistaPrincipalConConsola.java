package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import modelo.juego.MenuBase;
import modelo.personajes.Policia;

public class VistaPrincipalConConsola extends Vista implements Observer{
	
	private Panel panel;
	private Label label;

	
	public VistaPrincipalConConsola(MenuBase unMenuBase){
		super (unMenuBase);
		this.setImagen("imagenesVista/juegoNuevo.jpg");
		
		label = new Label("Usted se encuentra en: "+control.getMenuBase().obtenerCiudadActual());
		add("North", label);
		label.setAlignment(Label.CENTER);
		label.setVisible(true);
		
		panel = new Panel(); 
		Button botonViajar = new Button("Viajar a otra Ciudad");
		botonViajar.addActionListener(control.getListenerViajar());
		Button botonPistas = new Button("Visitar un Lugar");
		botonPistas.addActionListener(control.getListenerPistas());
		Button botonOrdenArresto = new Button("Emitir Orden de Arresto");
		botonOrdenArresto.addActionListener(control.getListenerOrdenArresto());
		
		panel.add(botonViajar); 
		panel.add(botonPistas); 
		panel.add(botonOrdenArresto); 
		
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
	
}