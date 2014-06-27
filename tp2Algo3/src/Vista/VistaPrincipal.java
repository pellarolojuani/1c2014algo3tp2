package Vista;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import modelo.juego.MenuBase;
import controlador.ControlMenu.Controlador;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class VistaPrincipal extends Vista implements Observer{
	
	public VistaPrincipal(MenuBase unMenuBase){
		
		super (unMenuBase);
		this.setImagen("imagenesVista/presentacionCarmenSandiego.jpg");
	
	}

		
}
