package controlador.ControlMenu;

import java.awt.event.*;

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
		public void actionPerformed(ActionEvent e){
			// ACA DEBERIAMOS HACER EL GUARDAR;
			System.out.println("El juego se guarda");
		}
	}
	
	public ActionListener getListenerGuardar() {
		return new EscuchaBotonGuardar();
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
