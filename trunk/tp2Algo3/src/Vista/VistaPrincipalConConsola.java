package Vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.ImageIcon;

public class VistaPrincipalConConsola extends JPanel {
	
	private String nombreVista;
	
	public VistaPrincipalConConsola(String vista){
		this.nombreVista = vista;
		this.setSize(100, 100);
		this.setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g){
	
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource(this.nombreVista));
		g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height - 100, null);
		setOpaque(false);
		super.paintComponent(g);
		this.setVisible(true);

		ImageIcon imagenConsola = new ImageIcon(getClass().getResource("imagenesVista/consola.jpg"));
		g.drawImage(imagenConsola.getImage(),0,tamanio.height - 100,tamanio.width, 100, null);
		super.paintComponent(g);
		this.setVisible(true);
		

	}
	
	public void addBottonPanel(String boton1, String boton2, String boton3){
		
		Panel p1 = new Panel(); 
		Button primerBoton = new Button(boton1);
		Button segundoBoton = new Button(boton2);
		Button tercerBoton = new Button(boton2);
		
		p1.add(primerBoton); 
		p1.add(segundoBoton); 
		p1.add(tercerBoton); 
		
		add("South", p1);
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

}