package controlador.ControlMenu;

import java.awt.event.*;

import Vista.VistaPrincipal;
import modelo.*;

public class Controlador {
	
	public Controlador(){
		
	}
	
	private class EscuchaBotonNuevo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// ACA DEBERIAMOS HACER EL new Juego();
			System.out.println("El juego se crea");
		}
	}
	
	public ActionListener getListenerNuevo() {
		return new EscuchaBotonNuevo();
	}

	private class EscuchaBotonGuardar implements ActionListener{
		private VistaPrincipal vista;
		
		public EscuchaBotonGuardar(VistaPrincipal unaVista){
			this.vista = unaVista;
		}
		
		public void actionPerformed(ActionEvent e){
			// ACA DEBERIAMOS HACER EL GUARDAR;
			
			this.vista.printText("Partida guardada.");
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
