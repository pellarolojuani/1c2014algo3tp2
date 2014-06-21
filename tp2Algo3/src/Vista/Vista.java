package Vista;

import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class Vista implements Observer {

	private Frame frame;
	private MenuBar menuBarra;

	//Clase auxiliar para escuchar el evento de cerrado de la ventana
	public static class CloseListener extends WindowAdapter
	{	public void windowClosing(WindowEvent e)
		{	e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
	
	
	//Constructor de la vista
	public Vista(){
		
		//armado de la ventana
		frame = new Frame("ALGOTHIEF GRUPO X"); //creamos el marco
		frame.setSize(800,600);  //seteamos las dimensiones del marco
		frame.setVisible(true);  //mostramos el marco
		//agregamos el listener del evento de cerrado de la ventana		
		frame.addWindowListener(new CloseListener());
		
		menuBarra = new MenuBar();
		Menu menu1 = new Menu("Juego");
		menu1.add( new MenuItem("Nuevo") );
		menu1.add( new MenuItem("Guardar") );
		menu1.addSeparator();
		menu1.add( new MenuItem ("Salir"));
		
		menuBarra.add(menu1);
		
		frame.setMenuBar(menuBarra);
	}
	
	//Metodo que es llamado por el modelo al actualizarse el mismo
	public void update(Observable t, Object o){
		
	}
	
}
