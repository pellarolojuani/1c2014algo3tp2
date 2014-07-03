package Vista;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class VistaPrincipalImagen extends JPanel {

    private String nombreVista;

    public VistaPrincipalImagen(String vistaName) {
        this.nombreVista = vistaName;
        this.setSize(100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource(this.nombreVista));
        g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
    }

}
