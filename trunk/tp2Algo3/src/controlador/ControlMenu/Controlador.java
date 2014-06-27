package controlador.ControlMenu;

import java.awt.event.*;

import javax.swing.JLabel;

import Vista.Vista;
import modelo.*;
import modelo.juego.MenuBase;

public class Controlador {
	
	public Controlador(){
		
	}
	
	
	private class EscuchaBotonNuevo implements ActionListener{
		private Vista vista;
		private MenuBase menuBase;
		
		public void actionPerformed(ActionEvent e){
			System.out.println("El juego se crea");
			menuBase = vista.getMenuBase();
			menuBase.nuevoJuego();
			
		}
		public EscuchaBotonNuevo(Vista unaVista){
			this.vista = unaVista;
		}
	}

	
	public ActionListener getListenerNuevo(Vista unaVista) {
		return new EscuchaBotonNuevo(unaVista);
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
	
	
	
}
