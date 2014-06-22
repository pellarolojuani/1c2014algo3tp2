/*package Vista;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import controlador.ControlMenu.*;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Vista implements Observer{

	private Frame frame;
	private MenuBar menuBarra;
	private Menu menu1, menu2, menu3;
	MenuItem nuevo, guardar, salir;
	

	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	
	//Constructor de la vista
	public Vista(Controlador control){
		
		//armado de la ventana
		frame = new Frame("ALGOTHIEF GRUPO X"); //creamos el marco
		frame.setSize(800,600);  //seteamos las dimensiones del marco
		frame.setVisible(true);  //mostramos el marco
		frame.setLocation(100, 50);
		frame.addWindowListener(new CloseListener()); //agregamos el listener del evento de cerrado de la ventana
		
		menuBarra = new MenuBar();
		menu1 = new Menu("Juego");
		
		nuevo = new MenuItem("Nuevo");
		nuevo.addActionListener(control.getListenerNuevo());
		guardar = new MenuItem("Guardar");
		guardar.addActionListener(control.getListenerGuardar());
		salir = new MenuItem("Salir");
		salir.addActionListener(control.getListenerSalir());
		
		menu1.add(nuevo);
		menu1.add(guardar);
		menu1.addSeparator();
		menu1.add(salir);
		
		menu2 = new Menu("Opciones");
		
		menu3 = new Menu("Acerca de");
		
		menuBarra.add(menu1);
		menuBarra.add(menu2);
		menuBarra.add(menu3);
		
		frame.setMenuBar(menuBarra);
	}
	
	//Metodo que es llamado por el modelo al actualizarse el mismo
	public void update(Observable t, Object o){
		
	}
	
}*/
