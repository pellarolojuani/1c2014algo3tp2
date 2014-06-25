package controlador.ControlMenu;

import java.awt.event.*;

import javax.swing.JLabel;

import Vista.VistaPrincipal;
import modelo.*;

public class Controlador {
	
	public Controlador(){
		
	}
	
	private class EscucharTecla implements KeyListener{
		
		private VistaPrincipal vista;
		
		public EscucharTecla(VistaPrincipal vista){
			this.vista = vista;
		}

		public void keyPressed(KeyEvent e) {
			//if (e.getKeyChar() == KeyEvent.VK_A) return;
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		
	}
	
	private class EscuchaBotonNuevo implements ActionListener{
		private VistaPrincipal vista;
		
		public void actionPerformed(ActionEvent e){
			this.vista.cambiarVista("imagenesVista/juegoNuevo.jpg");
			System.out.println("El juego se crea");
		}
		public EscuchaBotonNuevo(VistaPrincipal unaVista){
			this.vista = unaVista;
		}
	}

	
	public ActionListener getListenerNuevo(VistaPrincipal unaVista) {
		return new EscuchaBotonNuevo(unaVista);
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
