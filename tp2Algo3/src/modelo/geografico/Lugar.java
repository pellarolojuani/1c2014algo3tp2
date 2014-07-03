package modelo.geografico;

import modelo.elementosDelJuego.Pista;
import modelo.elementosDelJuego.Tiempo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Lugar {

    private Pista pista;
    public TipoEdificio tipo;
    private Ciudad ciudad;
    private int numVisitas;

    public Lugar(TipoEdificio tipo, Ciudad ciudad) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.numVisitas = 0;
        this.pista = new Pista();
    }

    public Lugar(TipoEdificio tipo) {
        this.tipo = tipo;
        this.numVisitas = 0;
        this.pista = new Pista();
    }

    public String obtenerPista() {

        return this.pista.contenidoComoString();
    }

    public void visitar() {
        if (numVisitas == 0) {
            Tiempo.aumentarHoras(1);
        } else if (numVisitas == 1) {
            Tiempo.aumentarHoras(2);
        } else {
            Tiempo.aumentarHoras(3);
        }
        numVisitas++;
    }

    public void plantarPista(Pista pista_) {

        this.pista = pista_;

    }

    public TipoEdificio obtenerTipo() {
        return this.tipo;
    }

    public int obtenerNumVisitas() {
        return numVisitas;
    }

    public void setVisitas(int nroVisitas) {
        this.numVisitas = nroVisitas;
    }

    public Element serializar(Document doc) {
        Element elementoLugar = doc.createElement("Lugar");
        elementoLugar.setAttribute("tipo", tipo.name());
        elementoLugar.setAttribute("numVisitas", Integer.toString(numVisitas));
        elementoLugar.setAttribute("pista", pista.contenidoComoString());
        return elementoLugar;
    }
}
