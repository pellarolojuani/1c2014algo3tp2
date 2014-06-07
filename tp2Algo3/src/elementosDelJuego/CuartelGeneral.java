package elementosDelJuego;

import descripciones.Descripcion;
import personajes.Sospechoso;

import java.util.ArrayList;

/**
 * Created by chris on 06/06/2014.
 */
public class CuartelGeneral {
    private ArrayList<Sospechoso> sospechosos;

    public CuartelGeneral() {
        this.sospechosos = new ArrayList<Sospechoso>();
    }

    public void cargarSospechoso(Sospechoso sospechoso) {
        sospechosos.add(sospechoso);
    }

    public Object buscarSospechoso(Descripcion otraDescripcion) {
        return sospechosos.get(0);
    }
}
