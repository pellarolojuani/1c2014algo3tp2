package controlador.ControlXML;

import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Ciudad;
import modelo.geografico.RecorridoLadron;
import modelo.geografico.TipoEdificio;
import modelo.juego.Caso;
import modelo.juego.Juego;
import modelo.personajes.Grado;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 27/06/2014.
 */
public class CargadorXML {
    public Juego cargar() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Juego juego = null;
        try {
            CreadorDeCiudades creadorDeCiudades=new CreadorDeCiudades();
            ArrayList<Ciudad> ciudades= creadorDeCiudades.obtenerCiudadesSinVisitables();

            Map<String, Ciudad> ciudadesMap = new HashMap<String, Ciudad>();
            for (Ciudad c : ciudades) ciudadesMap.put(c.getNombre(), c);

            File xmlFile = new File("juego.xml");
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            cargarCiudadesVisitables(document,ciudadesMap);

            Policia policia=cargarPolicia(document, ciudadesMap);

            CreadorDeObjetos creadorDeObjetos = new CreadorDeObjetos(ciudades);
            ArrayList<ObjetoRobado> objetos = creadorDeObjetos.obtenerListaDeObjetos();

            RecorridoLadron recorridoLadron=cargarRecorridoLadron(document, ciudadesMap);

            CreadorDeSospechosos creadorDeSospechosos = new CreadorDeSospechosos();
            ArrayList<Sospechoso> sospechosos = creadorDeSospechosos.obtenerSospechosos();

            Caso caso=cargarCaso(document,sospechosos,objetos,recorridoLadron);

            juego = new Juego(sospechosos, objetos, ciudades, caso, policia);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return juego;

    }

    private void cargarCiudadesVisitables(Document doc, Map<String,Ciudad> ciudadesMap){

        NodeList ciudadesList = doc.getElementsByTagName("Ciudad");
        for (int i = 0; i < ciudadesList.getLength(); i++) {
            Element c = (Element) ciudadesList.item(i);
            String nombre = c.getAttribute("nombre");
            String cVisitables[] = c.getAttribute("ciudadesDestino").split(",");
            for (String ciudad : cVisitables) {
                ciudadesMap.get(nombre).agregarCiudadVisitable(ciudadesMap.get(ciudad));
            }
        }
    }

    private Policia cargarPolicia(Document doc,Map<String,Ciudad> ciudadesMap){

        Element eP = (Element) doc.getElementsByTagName("Policia").item(0);
        int nroArrestos = Integer.parseInt(eP.getAttribute("nroArrestos"));
        Grado grado = Grado.valueOf(eP.getAttribute("grado"));
        String nombre = eP.getAttribute("nombre");
        Ciudad ciudadActual = ciudadesMap.get(eP.getAttribute("ciudadActual"));
        Policia policia = new Policia(nombre, grado, nroArrestos, ciudadActual);
        return  policia;
    }

    private RecorridoLadron cargarRecorridoLadron(Document document,Map<String,Ciudad> ciudadesMap){
        ArrayList<Ciudad> recorrido = new ArrayList<Ciudad>();
        Element eR = (Element) document.getElementsByTagName("RecorridoLadron").item(0);
        String recorridoString[] = eR.getAttribute("ciudades").split(",");
        for (String s : recorridoString) {
            Ciudad c = ciudadesMap.get(s);
            recorrido.add(c);
        }
        TipoEdificio tipoEdificio = TipoEdificio.valueOf(eR.getAttribute("lugarFinal"));
        RecorridoLadron recorridoLadron = new RecorridoLadron(recorrido, tipoEdificio);
        return recorridoLadron;
    }

    private Caso cargarCaso(Document document, ArrayList<Sospechoso> sospechosos, ArrayList<ObjetoRobado> objetos,RecorridoLadron recorridoLadron){
        Sospechoso ladron = null;
        ObjetoRobado objeto = null;
        Element eC = (Element) document.getElementsByTagName("Caso").item(0);
        for (Sospechoso s : sospechosos) {
            if (s.getNombre().equals(eC.getAttribute("ladron"))) {
                ladron = s;
                break;
            }
        }
        for (ObjetoRobado obj : objetos) {
            if (obj.obtenerDescripcion().equals(eC.getAttribute("objetoRobado"))) {
                objeto = obj;
                break;
            }
        }
        Caso caso = new Caso(recorridoLadron, objeto, Valor.valueOf(eC.getAttribute("valorObjeto")), ladron);
        return caso;
    }
}
