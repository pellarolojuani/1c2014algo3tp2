package Vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class VistaNuevoJuego extends JPanel {

	private static final long serialVersionUID = 1L;

	public VistaNuevoJuego(){
		this.setSize(100, 100);
	}
	
	@Override
	public void paintComponent(Graphics g){
		this.setBackground(Color.black);
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("imagenesVista/juegoNuevo.jpg"));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}