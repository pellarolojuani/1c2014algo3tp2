package modelo.elementosDelJuego;

import modelo.descripciones.Descripcion;
import modelo.geografico.Lugar;
import modelo.juego.Juego;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Observable;

//El cuartel general tambien tendria que ser unico
public class CuartelGeneral extends Observable {

    private static ArrayList<Sospechoso> sospechosos;
    private static OrdenDeArresto orden;
    private static Policia policia;
    private static CuartelGeneral instance;

    public CuartelGeneral(){
    }

    public void asignarAJuego(Juego unJuego){
        addObserver(unJuego);
    }

    public static CuartelGeneral getInstance(){
        if(instance==null)instance=new CuartelGeneral();
        return instance;
    }

    public void cargarSospechosos(ArrayList<Sospechoso> sospechososList) {
        sospechosos=sospechososList;
    }

    public ArrayList<Sospechoso> buscarSospechoso(Descripcion otraDescripcion) {
        //Hay que implementar esto para que busque bien, y encuentre un sospechoso aunque la descripcion sea parcial.
        ArrayList<Sospechoso> s=new ArrayList<Sospechoso>();
        for(Sospechoso sospechoso: sospechosos){
            if(sospechoso.obtenerDescripcion().puedeSerIgualA(otraDescripcion))s.add(sospechoso);
        }
        return s;
    }

    public void notificarDeArrestoAPolicia() {
        policia.arrestarLadron();
        if(policia.obtenerNroArrestos()==policia.obtenerGrado().arrestosParaPromover())policia.promoverGrado();
    }

    public boolean fueEmitidaOrdenPara(Sospechoso ladron) {
        if(orden==null)return false;
        return orden.obtenerSospechoso()==ladron;
    }

    public void emitirOrdenDeArrestoPara(Sospechoso sospechoso) {
        orden=new OrdenDeArresto(sospechoso);
    }

    public void incorporarPolicia(Policia unPolicia) {
        policia=unPolicia;
    }

    public void notificarVisitaA(Lugar lugar) {
        setChanged();
        notifyObservers(lugar);
    }

    public Element serializar(Document doc){
        Element elementoCuartel=doc.createElement("CuartelGeneral");
        if(orden==null) elementoCuartel.setAttribute("orden","");
        else elementoCuartel.setAttribute("orden",orden.obtenerSospechoso().getNombre());
        return elementoCuartel;
    }
}
