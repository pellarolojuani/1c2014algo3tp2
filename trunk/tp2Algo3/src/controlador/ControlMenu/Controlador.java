package controlador.ControlMenu;

import java.awt.Button;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Vista.Vista;
import modelo.*;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
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
			menuBase.getPolicia().viajarA(ciudadesDisponibles.get(this.pos));
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
			menuBase.setOpcionMenuPrincipal(3);
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
	//REPETIDO! BORRAR
	private class EscucharBotonVolver implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			menuBase.menuPrincipal();
		}
		
	}
	public ActionListener getListenerVolver(){
		return new EscucharBotonVolver();
	}
}

	
