package Vista;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.juego.MenuBase;
import controlador.ControlMenu.Controlador;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Vista extends JFrame implements Observer{

	private MenuBar menuBarra;
	private Menu menu1, menu2, menu3;
	private MenuItem nuevo, guardar, salir, creditos, cargarPartida;
	protected VistaPrincipalImagen imagen;
	protected Controlador control;
	
	private static final long serialVersionUID = 1L;
	
	private char comilla = (char)34;
	
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
	
	
	public Vista(){
		
	}
	
	public Vista(MenuBase unMenuBase){
		
		control = new Controlador(unMenuBase);
		
		this.setName("ALGOTHIEF GRUPO X");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 700, 500);
		
		menuBarra = new MenuBar();
		menu1 = new Menu("Juego");
		nuevo = new MenuItem("Nuevo");
		nuevo.addActionListener(control.getListenerNuevo());
		guardar = new MenuItem("Guardar");
		guardar.addActionListener(control.getListenerGuardar(this));
		cargarPartida = new MenuItem("Cargar partida guardada");
		salir = new MenuItem("Salir");
		salir.addActionListener(control.getListenerSalir());
		
		menu1.add(nuevo);
		menu1.add(guardar);
		menu1.add(cargarPartida);
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
	
	public void VistaJuegoNuevo(MenuBase unMenuBase){
		this.setImagen("imagenesVista/juegoNuevo.jpg");
		this.setVisible(true);
		String descripcionRobo = unMenuBase.getJuego().obtenerCaso().obtenerDescripcionDelRobo();
		String horario = Tiempo.tiempoComoString();
		String texto =  "<html><font color = " + comilla + "red" + comilla +  "size = 4>" + descripcionRobo.substring(0, 9) +
						"</font><br><font color = " + comilla + "red" + comilla + "size = 3>" + descripcionRobo.substring(10, descripcionRobo.length()) +
						"</font><br>" + "</font><br><font color = " + comilla + "red" + comilla + "size = 3>" + horario + "<font><html>";
		System.out.println(texto);
		JLabel panelTexto = new JLabel();
		panelTexto.setText(texto);
		
		this.add("North", panelTexto);
		
		JButton botonComenzar = new JButton();
		botonComenzar.setText("Comenzar investigacion");
		botonComenzar.addActionListener(control.getListenerComenzarInvestigacion());
		add("South", botonComenzar);
		
		this.setVisible(true);
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
	
	public void setImagen(String imagenPath){
		imagen = new VistaPrincipalImagen(imagenPath);
		imagen.setBorder(new EmptyBorder(5, 5, 5, 5));
		imagen.setLayout(new BorderLayout(0, 0));
		setContentPane(imagen);
	}
	
	public void vistaCiudad(String unaCiudad){
		
		String ubicacionCiudad = "imagenesVista/ciudades/" + unaCiudad.toLowerCase() + ".jpg";
		VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
		
		//@TODO : Corregir este mamarracho
		String horario = "<html><font color = " + comilla + "white" + comilla + "size = 4>" + 
						 Tiempo.tiempoComoString()+ 
						 "</font>" + "<br><br><font color = " + comilla + "white" + comilla + "size = 3>" +
						 "Usted se encuentra en: " + unaCiudad + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></html>";
		
		JLabel panelTiempo = new JLabel();
		panelTiempo.setText(horario);
		add("East", panelTiempo);
		
		
		
		JButton botonViajar = new JButton();
		botonViajar.setText("Viajar a otra ciudad");
		botonViajar.addActionListener(control.getListenerViajar());
		JButton botonVisitarLugar = new JButton();
		botonVisitarLugar.setText("Visitar lugar");
		JButton botonEmitirOrdenArresto = new JButton();
		botonEmitirOrdenArresto.setText("Emitir orden de arresto");
		JPanel panelBotones = new JPanel();
		panelBotones.add(botonViajar);
		panelBotones.add(botonVisitarLugar);
		panelBotones.add(botonEmitirOrdenArresto);
		
		add("South", panelBotones);
		
		this.setVisible(true);
	}
	
	public void vistaViajar(ArrayList<Ciudad> ciudadesDisponibles){
		
		this.setImagen("imagenesVista/mapa.jpg");
		this.setVisible(true);
		
		JPanel panel = new JPanel(); 
		
		for (Ciudad unaCiudad: ciudadesDisponibles){
			panel.add(new Button(unaCiudad.getNombre()));
		}
		
		add("South", panel);
		panel.setVisible(true);
		this.setVisible(true);
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
	
	
}

