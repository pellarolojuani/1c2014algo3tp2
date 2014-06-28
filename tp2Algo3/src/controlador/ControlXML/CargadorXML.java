package controlador.ControlXML;

import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
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
            File xmlFile = new File("ciudades.xml");
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList ciudadesList = document.getElementsByTagName("Ciudad");
            ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

            for (int i = 0; i < ciudadesList.getLength(); i++) {
                Element e = (Element) ciudadesList.item(i);
                //Se extrae la lista de edificios de la ciudad, se la divide con split y se la carga a objetos lugar.
                //Se agregan a cada ciudad.
                String[] listaedificios = e.getAttribute("edificios").split(",");
                ArrayList<Lugar> lugares = new ArrayList<Lugar>();
                Ciudad s = new Ciudad(e.getAttribute("nombre"), e.getAttribute("bandera"), e.getAttribute("moneda"), e.getAttribute("lugaresdeinteres"), e.getAttribute("personaje"), e.getAttribute("industria"), e.getAttribute("fauna"), e.getAttribute("idiomas"), null, lugares, Double.parseDouble(e.getAttribute("Latitud")), Double.parseDouble(e.getAttribute("Longitud")));
                for (int i1 = 0; i1 < listaedificios.length; i1++) {
                    TipoEdificio tipo = TipoEdificio.valueOf(listaedificios[i1]);
                    Lugar a = new Lugar(tipo, s);
                    lugares.add(a);
                }
                s.agregarLugares(lugares);
                ciudades.add(s);

            }
            Map<String, Ciudad> ciudadesMap = new HashMap<String, Ciudad>();
            for (Ciudad c : ciudades) {
                ciudadesMap.put(c.getNombre(), c);
            }

            //Ya tenemos las ciudades cargadas, ahora hay que cargarles las ciudades visitables.
            xmlFile = new File("juego.xml");
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            ciudadesList = document.getElementsByTagName("Ciudad");
            for (int i = 0; i < ciudadesList.getLength(); i++) {
                Element c = (Element) ciudadesList.item(i);
                String nombre = c.getAttribute("nombre");
                String cVisitables[] = c.getAttribute("ciudadesDestino").split(",");
                for (String ciudad : cVisitables) {
                    ciudadesMap.get(nombre).agregarCiudadVisitable(ciudadesMap.get(ciudad));
                }
            }
            //Ahora en ciudades tenemos la lista de ciudades, cada una con su lista de ciudades visitables.

            //Creamos el policia

            Element eP = (Element) document.getElementsByTagName("Policia").item(0);
            int nroArrestos = Integer.parseInt(eP.getAttribute("nroArrestos"));
            Grado grado = Grado.valueOf(eP.getAttribute("grado"));
            String nombre = eP.getAttribute("nombre");
            Ciudad ciudadActual = ciudadesMap.get(eP.getAttribute("ciudadActual"));
            Policia policia = new Policia(nombre, grado, nroArrestos, ciudadActual);

            //Ya esta el policia creado

            //Cargamos los objetos robados
            CreadorDeObjetos creadorDeObjetos = new CreadorDeObjetos(ciudades);
            ArrayList<ObjetoRobado> objetos = creadorDeObjetos.obtenerListaDeObjetos();
            //Ya tenemos la lista de objetos

            //Creamos el recorrido del ladron
            ArrayList<Ciudad> recorrido = new ArrayList<Ciudad>();
            Element eR = (Element) document.getElementsByTagName("RecorridoLadron").item(0);
            String recorridoString[] = eR.getAttribute("ciudades").split(",");
            for (String s : recorridoString) {
                Ciudad c = ciudadesMap.get(s);
                recorrido.add(c);
            }
            TipoEdificio tipoEdificio = TipoEdificio.valueOf(eR.getAttribute("lugarFinal"));
            RecorridoLadron recorridoLadron = new RecorridoLadron(recorrido, tipoEdificio);
            //Ya tenemos el recorrido armado

            //Creamos la lista de sospechosos
            CreadorDeSospechosos creadorDeSospechosos = new CreadorDeSospechosos();
            ArrayList<Sospechoso> sospechosos = creadorDeSospechosos.obtenerSospechosos();

            //Creamos el caso
            Sospechoso ladron=null;
            ObjetoRobado objeto=null;
            Element eC = (Element) document.getElementsByTagName("Caso").item(0);
            for (Sospechoso s : sospechosos) {
                if (s.getNombre().equals( eC.getAttribute("ladron"))) {
                    ladron = s;
                    break;
                }
            }
            for (ObjetoRobado obj : objetos) {
                if (obj.obtenerDescripcion().equals( eC.getAttribute("objetoRobado"))) {
                    objeto = obj;
                    break;
                }
            }
            Caso caso = new Caso(recorridoLadron, objeto, Valor.valueOf(eC.getAttribute("valorObjeto")), ladron);
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
}
