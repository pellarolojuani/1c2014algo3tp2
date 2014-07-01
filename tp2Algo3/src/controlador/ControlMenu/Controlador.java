package controlador.ControlMenu;

import java.awt.Button;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

import Vista.Vista;
import modelo.*;
import modelo.elementosDelJuego.SeAcaboElTiempoDelCasoExcepcion;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.personajes.Sospechoso;
import modelo.descripciones.*;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.juego.MenuBase;

public class Controlador {
	
	private MenuBase menuBase;
	
	public Controlador(MenuBase unMenuBase){
		menuBase = unMenuBase;
	}
	
	public MenuBase getMenuBase(){
		return menuBase;
	}
	
	
	private class EscuchaBotonNuevo implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			System.out.println("El juego se crea");
			//menuBase.setOpcionMenuBase(1);
			menuBase.nuevoJuego();
		}
		public EscuchaBotonNuevo(){
		}
	}

	
	public ActionListener getListenerNuevo() {
		return new EscuchaBotonNuevo();
	}
	//-------------------------------------------------------------
	private class EscuchaBotonGuardar implements ActionListener{
		private Vista vista;
		
		public EscuchaBotonGuardar(Vista unaVista){
			this.vista = unaVista;
		}
		
		public void actionPerformed(ActionEvent e){
			// ACA DEBERIAMOS HACER EL GUARDAR;
			
			JLabel label = new JLabel();
			label.setSize(this.vista.getSize());
			String texto = "<html><body>Partida Guardada"+
					"</body></html>";
			label.setText(texto);
			this.vista.add("South", label);
			this.vista.setVisible(true);  //mostramos el marco
			System.out.println("El juego se guarda");
		}
	}

	public ActionListener getListenerGuardar(Vista unaVista) {
		return new EscuchaBotonGuardar(unaVista);
		}
	
	private class EscuchaBotonSalir implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	public ActionListener getListenerSalir() {
		return new EscuchaBotonSalir();
	}
	
	
	private class EscuchaBotonViajar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//VIAJAR
			System.out.println("Viajando...");
			menuBase.menuViajar();
		}
	}
	
	public ActionListener getListenerViajar() {
		return new EscuchaBotonViajar();
	}	
	
	private class EscuchaBotonViajarACiudad implements ActionListener{
		private ArrayList<Ciudad> ciudadesDisponibles;
		int pos;
		
		public EscuchaBotonViajarACiudad(int posicion, ArrayList<Ciudad> ciudades){
			this.ciudadesDisponibles = ciudades;
			this.pos = posicion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
	        try{
	        	menuBase.getPolicia().viajarA(ciudadesDisponibles.get(this.pos));
	            }
	            catch(SeAcaboElTiempoDelCasoExcepcion i){
	        			System.out.println(i.AvisoAlJugador());
	        	}
	        menuBase.menuPrincipal();
		}
	}
	
	public ActionListener getListenerViajarACiudad(int pos, ArrayList<Ciudad> ciudadesDisponibles){
		return new EscuchaBotonViajarACiudad(pos, ciudadesDisponibles);
	}
	
	private class EscuchaBotonPistas implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//PISTAS
			System.out.println("Dando Pistas...");
			menuBase.setOpcionMenuPrincipal(2);
		}
	}
	
	public ActionListener getListenerPistas() {
		return new EscuchaBotonPistas();
	}	
	
	private class EscucharBotonVisitarLugar implements ActionListener{
		private ArrayList<Lugar> lugaresDisponibles;
		private Vista vista;
		private MenuBase menu;
		
		public EscucharBotonVisitarLugar(MenuBase unMenuBase, Vista unaVista, ArrayList<Lugar> lugares){
			this.lugaresDisponibles = lugares;
			this.vista = unaVista;
			this.menu = unMenuBase;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			JPanel panel = new JPanel(); 
			int i = 0;
			
			for (Lugar unLugar: lugaresDisponibles){
				String nombre = unLugar.obtenerTipo().toString();
				Button unBoton = new Button(nombre);
				EscucharBotonLugar botonLugar = new EscucharBotonLugar(menu, i, lugaresDisponibles, vista);
				unBoton.addActionListener(botonLugar.getListenerLugar(menu, i, lugaresDisponibles, vista));
				panel.add(unBoton);
				i++;
			}
	
			this.vista.add("North", panel);
			panel.setVisible(true);
			vista.setVisible(true);
		}
	}
	
	public ActionListener getListenerVisitarLugar(MenuBase unMenuBase, Vista unaVista, ArrayList<Lugar> lugaresDisponibles){
		return new EscucharBotonVisitarLugar(unMenuBase, unaVista, lugaresDisponibles);
	}
	
	private class EscucharBotonLugar implements ActionListener {
		private ArrayList<Lugar> lugares;
		private int posicion;
		private Vista vista;
		private MenuBase menu;
		
		public EscucharBotonLugar(MenuBase unMenuBase, int pos, ArrayList<Lugar> lugaresDisponibles, Vista unaVista){
			this.lugares = lugaresDisponibles;
			this.posicion = pos;
			this.vista = unaVista;
			this.menu = unMenuBase;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			Lugar unLugar = lugares.get(posicion);
			menuBase.getPolicia().visitarLugar(unLugar);
			String pista = menuBase.getPolicia().obtenerUltimaPista();
			vista.vistaLugar(menu, unLugar, pista);
		}
		
		public ActionListener getListenerLugar(MenuBase unMenuBase, int pos, ArrayList<Lugar> lugares, Vista unaVista){
			return new EscucharBotonLugar(unMenuBase, pos, lugares, unaVista);
		}
		
	}
	
	private class EscuchaBotonOrdenArresto implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//ORDEN ARRESTO
			System.out.println("Generando Orden de Arresto...");
			menuBase.menuOrdenDeArresto();
		}
	}
	
	public ActionListener getListenerOrdenArresto() {
		return new EscuchaBotonOrdenArresto();
	}
	
	private class EscuchaBotonComenzarInvestigacion implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			menuBase.menuPrincipal();
		}
	}
	public ActionListener getListenerComenzarInvestigacion(){
		return new EscuchaBotonComenzarInvestigacion();
	}
	
	private class EscuchaBotonEmitirOrden implements ActionListener{
		private ArrayList<Sospechoso> sospechosos;
		private Descripcion descripcion;
		
		public EscuchaBotonEmitirOrden(Descripcion unaDescripcion){
			this.descripcion = unaDescripcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			CuartelGeneral cuartelGeneral = CuartelGeneral.getInstance();
			sospechosos = cuartelGeneral.buscarSospechoso(descripcion);
			
			JFrame sospechososVentana = new JFrame();
			sospechososVentana.setDefaultCloseOperation(sospechososVentana.DISPOSE_ON_CLOSE);
			sospechososVentana.setSize(350, 350);
			JLabel mensajeLabel = new JLabel();
			mensajeLabel.setSize(sospechososVentana.getSize());
			String texto = "<html><body>Coincidencias:<br>";
			for (Sospechoso s: sospechosos){
				texto.concat("<br>");
				texto.concat(s.getNombre());
			}
			
			if(sospechosos.size()==0){
	            texto.concat("<br><br>No se encontro ningun sospechoso que concuerde con la decripcion seleccionada");
	        }
			if(sospechosos.size()==1){
	            menuBase.getPolicia().emitirOrdenDeArrestoPara(sospechosos.get(0));
	            texto.concat("<br><br>Emitida la orden de arresto para "+sospechosos.get(0).getNombre());
	        }
			
			texto.concat("</body></html>");
			mensajeLabel.setText(texto);
			sospechososVentana.add("North",mensajeLabel);
			sospechososVentana.setVisible(true);
		}
	}
	public ActionListener getListenerEmitirOrden(Descripcion unaDescripcion){
		return new EscuchaBotonEmitirOrden(unaDescripcion);
	}
	
	private class EscuchaBotonVolverPrincipal implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			menuBase.menuPrincipal();
		}
	}
	public ActionListener getListenerVolverPrincipal(){
		return new EscuchaBotonVolverPrincipal();
	}
	
	private class EscuchaBotonSeteadorSexo implements ActionListener{
		private Sexo sexoElegido;
		private Sexo sexoOpcion;
		
		public EscuchaBotonSeteadorSexo(Sexo sexoElegido, Sexo sexoOpcion){
			this.sexoElegido = sexoElegido;
			this.sexoOpcion = sexoOpcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			this.sexoElegido = this.sexoOpcion;
		}
	}
	public ActionListener getListenerSeteadorSexo(Sexo sexoElegido, Sexo sexoOpcion){
		return new EscuchaBotonSeteadorSexo(sexoElegido, sexoOpcion);
	}
	
	private class EscuchaBotonSeteadorHobby implements ActionListener{
		private Hobby hobbyElegido;
		private Hobby hobbyOpcion;
		
		public EscuchaBotonSeteadorHobby(Hobby hobbyElegido, Hobby hobbyOpcion){
			this.hobbyElegido = hobbyElegido;
			this.hobbyOpcion = hobbyOpcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			this.hobbyElegido = this.hobbyOpcion;
		}
	}
	public ActionListener getListenerSeteadorHobby(Hobby hobbyElegido, Hobby hobbyOpcion){
		return new EscuchaBotonSeteadorHobby(hobbyElegido, hobbyOpcion);
	}
	
	private class EscuchaBotonSeteadorPelo implements ActionListener{
		private Pelo peloElegido;
		private Pelo peloOpcion;
		
		public EscuchaBotonSeteadorPelo(Pelo peloElegido, Pelo peloOpcion){
			this.peloElegido = peloElegido;
			this.peloOpcion = peloOpcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			this.peloElegido = this.peloOpcion;
		}
	}
	public ActionListener getListenerSeteadorPelo(Pelo peloElegido, Pelo peloOpcion){
		return new EscuchaBotonSeteadorPelo(peloElegido, peloOpcion);
	}
	
	private class EscuchaBotonSeteadorSenia implements ActionListener{
		private Senia seniaElegido;
		private Senia seniaOpcion;
		
		public EscuchaBotonSeteadorSenia(Senia seniaElegido, Senia seniaOpcion){
			this.seniaElegido = seniaElegido;
			this.seniaOpcion = seniaOpcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			this.seniaElegido = this.seniaOpcion;
		}
	}
	public ActionListener getListenerSeteadorSenia(Senia seniaElegido, Senia seniaOpcion){
		return new EscuchaBotonSeteadorSenia(seniaElegido, seniaOpcion);
	}
	
	private class EscuchaBotonSeteadorVehiculo implements ActionListener{
		private Vehiculo vehiculoElegido;
		private Vehiculo vehiculoOpcion;
		
		public EscuchaBotonSeteadorVehiculo(Vehiculo vehiculoElegido, Vehiculo vehiculoOpcion){
			this.vehiculoElegido = vehiculoElegido;
			this.vehiculoOpcion = vehiculoOpcion;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			this.vehiculoElegido = this.vehiculoOpcion;
		}
	}
	public ActionListener getListenerSeteadorVehiculo(Vehiculo vehiculoElegido, Vehiculo vehiculoOpcion){
		return new EscuchaBotonSeteadorVehiculo(vehiculoElegido, vehiculoOpcion);
	}

	private class EscucharBotonVolver implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			menuBase.menuPrincipal();
		}
		
	}
	public ActionListener getListenerVolver(){
		return new EscucharBotonVolver();
	}
}

	
