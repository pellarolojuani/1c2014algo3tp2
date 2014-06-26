package controlador.ControlMenu;

import java.awt.event.*;

import javax.swing.JLabel;

import Vista.VistaPrincipal;
import modelo.*;
import modelo.juego.MenuBase;

public class Controlador {
	
	public Controlador(){
		
	}
	
	
	private class EscuchaBotonNuevo implements ActionListener{
		private VistaPrincipal vista;
		private MenuBase menuBase;
		
		public void actionPerformed(ActionEvent e){
			//this.vista.cambiarVista("imagenesVista/juegoNuevo.jpg");
			System.out.println("El juego se crea");
			this.menuBase.nuevoJuego();
			
		}
		public EscuchaBotonNuevo(VistaPrincipal unaVista, MenuBase unMenuBase){
			this.menuBase = unMenuBase;
			this.vista = unaVista;
		}
	}

	
	public ActionListener getListenerNuevo(VistaPrincipal unaVista, MenuBase unMenuBase) {
		return new EscuchaBotonNuevo(unaVista, unMenuBase);
	}
	//-------------------------------------------------------------
	private class EscuchaBotonGuardar implements ActionListener{
		private VistaPrincipal vista;
		
		public EscuchaBotonGuardar(VistaPrincipal unaVista){
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

	public ActionListener getListenerGuardar(VistaPrincipal unaVista) {
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
