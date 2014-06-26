package Vista;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.border.EmptyBorder;

import modelo.juego.MenuBase;
import controlador.ControlMenu.Controlador;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class VistaPrincipal extends JFrame implements Observer{

	private MenuBar menuBarra;
	private Menu menu1, menu2, menu3;
	MenuItem nuevo, guardar, salir, creditos;

	
	private static final long serialVersionUID = 1L;
	
	//Clase auxiliar para escuchar el evento de cerrado de la ventana principal
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	//Clase auxiliar para escuchar el evento de cerrado de un Frame secundario
	public static class CloseFrameListener extends WindowAdapter {
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
		}
	}

	public VistaPrincipal(){
		
		Controlador control = new Controlador();
		
		this.setName("ALGOTHIEF GRUPO X");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 700, 500);
		
		VistaPrincipalImagen p = new VistaPrincipalImagen("imagenesVista/presentacionCarmenSandiego.jpg");
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);

		menuBarra = new MenuBar();
		menu1 = new Menu("Juego");
		nuevo = new MenuItem("Nuevo");
		nuevo.addActionListener(control.getListenerNuevo(this));
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
		creditos = new MenuItem("Creditos");
		creditos.addActionListener(getListenerCreditos());
		
		menu3.add(creditos);
		
		menuBarra.add(menu1);
		menuBarra.add(menu2);
		menuBarra.add(menu3);
		
		this.setMenuBar(this.menuBarra);
		
		
	}
	
	public void cambiarVista(String vista){
		VistaPrincipalConConsola p = new VistaPrincipalConConsola(vista);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
	}
	
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
	
	public void vistaCiudad(String unaCiudad){
		String ubicacionCiudad = "imagenesVista/ciudades/" + unaCiudad + ".jpg";
		VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
	}

	private class EscuchaBotonCreditos implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frameCreditos = new JFrame("Creditos");
			frameCreditos.setSize(400,300);  //seteamos las dimensiones del marco
			frameCreditos.setLocationRelativeTo(rootPane);
			frameCreditos.addWindowListener(new CloseFrameListener());
			JLabel label = new JLabel();
			label.setSize(frameCreditos.getSize());
			String texto = "<html><body>Este juego fue creado por alumnos de la Facultad de Ingenieria" +
					" de la Universidad de Buenos Aires, correspondiendo al Trabajo" +
					" Practico Grupal de la materia 'Algoritmos y Programacion 3, " +
					"Catedra Carlos Fontela'.<br><br>" +
					"Sus creadores son los siguientes:<br><br>" +
					"-Christian Pedersen<br>" +
					"-Frank Douglas Anze<br>" +
					"-Juan Ignacio Pellarolo<br>" +
					"-Nicolas Truksinas<br>" +
					"-Sebastian Savulsky<br>" +
					"</body></html>";
			label.setText(texto);
			frameCreditos.add("North", label);
			frameCreditos.setVisible(true);  //mostramos el marco
			
		}
	}
	
	public ActionListener getListenerCreditos() {
		return new EscuchaBotonCreditos();
	}

	
	
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void printText(String texto){
		this.add(new javax.swing.JScrollPane(new javax.swing.JTextArea(texto,50,100)));
		
	}
	
}
