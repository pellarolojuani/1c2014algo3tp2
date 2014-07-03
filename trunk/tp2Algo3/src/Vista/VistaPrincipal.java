package Vista;

import modelo.juego.MenuBase;

import java.util.Observer;

public class VistaPrincipal extends Vista implements Observer {

    public VistaPrincipal(MenuBase unMenuBase) {

        super(unMenuBase);
        this.setImagen("imagenesVista/presentacionCarmenSandiego.jpg");

    }


}
