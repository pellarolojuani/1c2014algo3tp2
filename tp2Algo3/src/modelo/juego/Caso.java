package modelo.juego;

import modelo.descripciones.Sexo;
import modelo.elementosDelJuego.CreadorDePistas;
import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.geografico.RecorridoLadron;
import modelo.personajes.Grado;
import modelo.personajes.Sospechoso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Caso {

    private Ciudad ciudadDelRobo;
    private ObjetoRobado objetoRobado;
    private Valor valorObjeto;
    private Sospechoso ladron;
    private RecorridoLadron recorridoLadron;

    public Caso(ArrayList<ObjetoRobado> objetos, Grado gradoPolicia, ArrayList<Sospechoso> sospechosos) {
        elegirObjeto(objetos, gradoPolicia);
        elegirLadron(sospechosos);
        crearRecorrido();
        this.ciudadDelRobo = recorridoLadron.obtenerCiudadDelRobo();
        plantarPistas();
    }

    private void crearRecorrido() {
        int nroCiudades = 4;
        if (valorObjeto == Valor.VALIOSO) nroCiudades = 5;
        if (valorObjeto == Valor.MUY_VALIOSO) nroCiudades = 7;
        recorridoLadron = new RecorridoLadron(ciudadDelRobo, nroCiudades);
    }

    private void elegirObjeto(ArrayList<ObjetoRobado> objetos, Grado gradoPolicia) {
        //Elegimos el valor del objeto de acuerdo al grado del policia.
        valorObjeto = Valor.COMUN;
        if (gradoPolicia == Grado.INVESTIGADOR) valorObjeto = Valor.VALIOSO;
        else if (gradoPolicia == Grado.SARGENTO) valorObjeto = Valor.MUY_VALIOSO;
        objetoRobado = objetos.get((int) (Math.random() * objetos.size()));
        ciudadDelRobo = objetoRobado.obtenerCiudadOrigen();
    }

    private void elegirLadron(ArrayList<Sospechoso> sospechosos) {
        //Elige un ladron al azar entre los sospechosos.
        ladron = sospechosos.get((int) (Math.random() * sospechosos.size() + 0));
    }

    public Ciudad obtenerCiudadRobo() {
        return this.ciudadDelRobo;
    }

    public Sexo obtenerSexoLadron() {
        return this.ladron.obtenerDescripcion().getSexo();
    }

    private void plantarPistas() {

        CreadorDePistas creadorDePistas = new CreadorDePistas(recorridoLadron, ladron,valorObjeto);
        creadorDePistas.plantarPistas();

    }

    public boolean ladronEstaEnLugar(Lugar lugar) {
        return recorridoLadron.obtenerLugarFinal() == lugar;
    }

    public Sospechoso obtenerLadron() {
        return ladron;
    }

    public Valor obtenerValorObjeto() {
        return this.valorObjeto;
    }

    public String obtenerDescripcionDelRobo() {
        return "Atencion! Se ha registrado el robo de " + objetoRobado.obtenerDescripcion() + " en la ciudad " + ciudadDelRobo.getNombre() + ".Testigos afirman que el ladron es de sexo " + obtenerSexoLadron();
    }

    public Element serializar(Document doc) {
        Element elementoCaso = doc.createElement("Caso");
        elementoCaso.setAttribute("ladron", ladron.getNombre());
        elementoCaso.setAttribute("objetoRobado", objetoRobado.obtenerDescripcion());
        elementoCaso.setAttribute("valorObjeto", valorObjeto.name());
        elementoCaso.appendChild(recorridoLadron.serializar(doc));
        return elementoCaso;
    }

    public Caso(RecorridoLadron recorridoLadron, ObjetoRobado objetoRobado, Valor valorObjeto, Sospechoso ladron) {
        this.recorridoLadron = recorridoLadron;
        this.ciudadDelRobo = recorridoLadron.obtenerCiudadDelRobo();
        this.objetoRobado = objetoRobado;
        this.valorObjeto = valorObjeto;
        this.ladron = ladron;
    }
}
