package ControlXML;

import descripciones.Descripcion;
import personajes.Sospechoso;

import java.util.ArrayList;

/**
 * Created by chris on 11/06/2014.
 */
public class CreadorDeSospechosos {
    //Lo creamos con el XML de los sospechosos
    public ArrayList<Sospechoso> obtenerListaDeSospechosos(){
        return null;
    }
    private Sospechoso crearSospechoso(Descripcion descripcion){
        return new Sospechoso(descripcion);
    }
    private Descripcion crearDescripcion(){
        return null;
    }
}
