package controlador.ControlXML;

import modelo.geografico.Ciudad;
import modelo.geografico.CreadorDeGrafoCiudades;
import modelo.geografico.Lugar;
import modelo.geografico.TipoEdificio;
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
import java.util.Collections;
import java.util.List;

public class CreadorDeCiudades {

    private CreadorDeGrafoCiudades creadorDeGrafo;
    public ArrayList<Ciudad> ciudades;

    private void setearCiudadesVisitables(ArrayList<Ciudad> ciudades) {
        for (int i2 = 0; i2 < ciudades.size(); i2++) {
            List<Ciudad> visitables = this.creadorDeGrafo.obtenerCiudadesVisitables(i2);

            for (Ciudad visitable : visitables) {
                this.ciudades.get(i2).agregarCiudadVisitable(visitable);

            }
        }

    }

    public CreadorDeCiudades() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
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
                Ciudad s = new Ciudad(e.getAttribute("nombre"), e.getAttribute("bandera"), e.getAttribute("moneda"), e.getAttribute("lugaresdeinteres"), e.getAttribute("personaje"), e.getAttribute("industria"), e.getAttribute("fauna"), e.getAttribute("idiomas"), lugares, Double.parseDouble(e.getAttribute("Latitud")), Double.parseDouble(e.getAttribute("Longitud")));
                for (String tipoEdificio : listaedificios) {
                    TipoEdificio tipo = TipoEdificio.valueOf(tipoEdificio);
                    Lugar a = new Lugar(tipo, s);
                    lugares.add(a);
                }
                s.agregarLugares(lugares);
                ciudades.add(s);

            }

            Collections.shuffle(ciudades);
            this.ciudades = ciudades;


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ciudad> obtenerCiudadesConVisitables() {

        this.creadorDeGrafo = new CreadorDeGrafoCiudades(this.ciudades, this.ciudades.size());
        this.setearCiudadesVisitables(this.ciudades);
        return ciudades;
    }

    public ArrayList<Ciudad> obtenerCiudadesSinVisitables() {
        return this.ciudades;
    }
}
