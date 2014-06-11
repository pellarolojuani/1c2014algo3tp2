package elementosDelJuego;

import descripciones.Descripcion;
import personajes.Sospechoso;

import java.util.ArrayList;

//El cuartel general tambien tendria que ser unico
public class CuartelGeneral {
    private ArrayList<Sospechoso> sospechosos;

    public CuartelGeneral() {
        this.sospechosos = new ArrayList<Sospechoso>();
    }

    public void cargarSospechosos(ArrayList<Sospechoso> sospechosos) {
        this.sospechosos=sospechosos;
    }

    public Sospechoso buscarSospechoso(Descripcion otraDescripcion) {
        //Hay que implementar esto para que busque bien, y encuentre un sospechoso aunque la descripcion sea parcial.
        return sospechosos.get(0);
    }
}
