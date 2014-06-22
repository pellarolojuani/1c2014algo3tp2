package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.border.EmptyBorder;

import controlador.ControlMenu.Controlador;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class VistaPrincipal extends JFrame implements Observer{
	
	private Frame frame;
	private MenuBar menuBarra;
	private Menu menu1, menu2, menu3;
	MenuItem nuevo, guardar, salir;
	
	private static final long serialVersionUID = 1L;
	
	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	public VistaPrincipal(){
		
		Controlador control = new Controlador();
		
		this.setName("ALGOTHIEF GRUPO X");
		this.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 700, 500);
		VistaPrincipalImagen p = new VistaPrincipalImagen();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
		
		menuBarra = new MenuBar();
		menu1 = new Menu("Juego");
		nuevo = new MenuItem("Nuevo");
		nuevo.addActionListener(control.getListenerNuevo());
		guardar = new MenuItem("Guardar");
		guardar.addActionListener(control.getListenerGuardar(this));
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
		
		this.setMenuBar(this.menuBarra);
		
		
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void printText(String texto){
		this.add(new javax.swing.JScrollPane(new javax.swing.JTextArea(texto,50,100)));
		
	}
	
}
