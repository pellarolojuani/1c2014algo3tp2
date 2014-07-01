package Vista;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.geografico.TipoEdificio;
import modelo.juego.MenuBase;
import controlador.ControlMenu.Controlador;
import modelo.descripciones.*;

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
	
	
	public Vista(String fondo){
		super(fondo);
		
	}
	
	public Vista(MenuBase unMenuBase){
		
		control = new Controlador(unMenuBase);
		
		this.setName("ALGOTHIEF GRUPO X");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
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
	
	public void vistaCiudad(MenuBase unMenuBase, Ciudad unaCiudad){
		
		String ubicacionCiudad = "imagenesVista/ciudades/" + unaCiudad.getNombre().toLowerCase() + ".jpg";
		VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
		
		//@TODO : Corregir este mamarracho
		String horario = "<html><font color = " + comilla + "white" + comilla + "size = 4>" + 
						 Tiempo.tiempoComoString()+ 
						 "</font>" + "<br><br><font color = " + comilla + "white" + comilla + "size = 3>" +
						 "Usted se encuentra en: " + unaCiudad.getNombre() + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></html>";
		
		JLabel panelTiempo = new JLabel();
		panelTiempo.setText(horario);
		add("East", panelTiempo);

		JButton botonViajar = new JButton();
		botonViajar.setText("Viajar a otra ciudad");
		botonViajar.addActionListener(control.getListenerViajar());
		JButton botonVisitarLugar = new JButton();
		botonVisitarLugar.setText("Visitar lugar");
		botonVisitarLugar.addActionListener(control.getListenerVisitarLugar(unMenuBase, this, unaCiudad.obtenerLugaresDisponibles()));
		JButton botonEmitirOrdenArresto = new JButton();
		botonEmitirOrdenArresto.setText("Emitir orden de arresto");
		botonEmitirOrdenArresto.addActionListener(control.getListenerOrdenArresto());
		JPanel panelBotones = new JPanel();
		panelBotones.add(botonViajar);
		panelBotones.add(botonVisitarLugar);
		panelBotones.add(botonEmitirOrdenArresto);
		
		add("South", panelBotones);
		
		this.setVisible(true);
	}
	
public void vistaLugar(MenuBase menuBase, Lugar unLugar, String pista){
		
		String ubicacionCiudad = "imagenesVista/lugares/" + unLugar.obtenerTipo().toString().toLowerCase() + ".jpg";
		VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
	
		JButton botonVolver = new JButton();
		botonVolver.setText("Volver");
		botonVolver.addActionListener(control.getListenerComenzarInvestigacion());
		
		JPanel panelBotones = new JPanel();
		panelBotones.add(botonVolver);
		
		add("South", panelBotones);
		
		this.setVisible(true);
		
		Vista frameCreditos = new Vista("Pista");
		frameCreditos.setImagen("imagenesVista/pistas.jpg");
		frameCreditos.setSize(350,200);  //seteamos las dimensiones del marco
		frameCreditos.setLocation(50, 50);
		frameCreditos.setLocationRelativeTo(rootPane);
		frameCreditos.addWindowListener(new CloseFrameListener());
		JLabel label = new JLabel();
		label.setSize(frameCreditos.getSize());
		
		String texto =  "<html><font color = " + comilla + "black" + comilla + "size = 3>" + 
				 pista + "</font><html>";
		label.setText(texto);
		frameCreditos.add("North", label);
		frameCreditos.setVisible(true);  //mostramos el marco
	}

	
	public void vistaViajar(ArrayList<Ciudad> ciudadesDisponibles){
		
		this.setImagen("imagenesVista/mapa.jpg");
		this.setVisible(true);
		
		JPanel panel = new JPanel(); 
		int i = 0;
		
		for (Ciudad unaCiudad: ciudadesDisponibles){
			String nombre = unaCiudad.getNombre();
			Button unBoton = new Button(nombre);
			unBoton.addActionListener(control.getListenerViajarACiudad(i, ciudadesDisponibles));
			panel.add(unBoton);
			i++;
		}

		add("South", panel);
		panel.setVisible(true);
		this.setVisible(true);
		
		String texto = "<html><font size = 4>Elija ciudad entre las disponibles:</font></html>";

		JLabel panelTexto = new JLabel();
		panelTexto.setText(texto);
		add("North", panelTexto);
		this.setVisible(true);
	}
	
	public void vistaOrdenDeArresto(){
		Sexo sexo = null;
		Hobby hobby = null;
		Senia senia = null;
		Pelo pelo = null;
		Vehiculo vehiculo = null;		
		
		this.setImagen("imagenesVista/ordenDeArresto.jpg");
		this.setVisible(true);
		
		JPanel sexoPanel = new JPanel();
		JLabel sexoLabel = new JLabel("Sexo:");
		sexoPanel.add(sexoLabel);
		for (Sexo s: Sexo.values()){
			JButton botonAux = new JButton(s.toString());
			botonAux.addActionListener(control.getListenerSeteadorSexo(sexo, s));
			sexoPanel.add(botonAux);
		}
		
		JPanel hobbyPanel = new JPanel();
		JLabel hobbyLabel = new JLabel("Hobby:");
		hobbyPanel.add(hobbyLabel);
		for (Hobby h: Hobby.values()){
			JButton botonAux = new JButton(h.toString());
			botonAux.addActionListener(control.getListenerSeteadorHobby(hobby, h));
			hobbyPanel.add(botonAux);
		}
		
		JPanel peloPanel = new JPanel();
		JLabel peloLabel = new JLabel("Pelo:");
		peloPanel.add(peloLabel);
		for (Pelo p: Pelo.values()){
			JButton botonAux = new JButton(p.toString());
			botonAux.addActionListener(control.getListenerSeteadorPelo(pelo, p));
			peloPanel.add(botonAux);
		}
		
		JPanel seniaPanel = new JPanel();
		JLabel seniaLabel = new JLabel("Seña:");
		seniaPanel.add(seniaLabel);
		for (Senia s: Senia.values()){
			JButton botonAux = new JButton(s.toString());
			botonAux.addActionListener(control.getListenerSeteadorSenia(senia, s));
			seniaPanel.add(botonAux);
		}
		
		JPanel vehiculoPanel = new JPanel();
		JLabel vehiculoLabel = new JLabel("Vehiculo:");
		vehiculoPanel.add(vehiculoLabel);
		for (Vehiculo v: Vehiculo.values()){
			JButton botonAux = new JButton(v.toString());
			botonAux.addActionListener(control.getListenerSeteadorVehiculo(vehiculo, v));
			vehiculoPanel.add(botonAux);
		}
		
		JButton botonEmitir = new JButton("Emitir Orden");
		botonEmitir.addActionListener(control.getListenerEmitirOrden(new Descripcion(sexo,hobby,pelo,senia,vehiculo)));
		JButton botonVolverPrincipal = new JButton("Volver");
		botonVolverPrincipal.addActionListener(control.getListenerVolverPrincipal());
		
		String texto = "<html><font size = 10><font color = 'yellow'>Caracteristicas del Sospechoso:</font></html>";
		JLabel panelTexto = new JLabel();
		panelTexto.setText(texto);
		add("North", panelTexto);
		
		setLayout(new FlowLayout());
		
		add(sexoPanel);
		sexoPanel.setVisible(true);
		add(hobbyPanel);
		hobbyPanel.setVisible(true);
		add(peloPanel);
		peloPanel.setVisible(true);
		add(seniaPanel);
		seniaPanel.setVisible(true);
		add(vehiculoPanel);
		vehiculoPanel.setVisible(true);
		add("South", botonEmitir);
		add("South", botonVolverPrincipal);
		
		
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

