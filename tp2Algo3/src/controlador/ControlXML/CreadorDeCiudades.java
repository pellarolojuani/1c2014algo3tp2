package controlador.ControlXML;

import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreadorDeCiudades {
    //Lo creamos con el archivo XML que tiene las ciudades. Creamos cada ciudad con crearCiudad, creamos sus lugares con crearLugar.
    public ArrayList<Ciudad>ciudades;
    private ArrayList<Lugar>lugares;
    private String f;
    private int numerodeciudades;
    
    public CreadorDeCiudades(String f, int numerodeciudades) {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(f);
            Element element=document.getDocumentElement();
            NodeList ciudadesList=element.getChildNodes();
            for (int i = 0; i < numerodeciudades; i++) {
                if(ciudadesList.item(0).getNodeType()==Node.ELEMENT_NODE){
                    Element e= (Element) ciudadesList.item(i);
                    String[] listaedificios = e.getAttribute("edificios").split(":");
                    for(int i1 = 0; i1 < listaedificios.length; i1++){
                    	lugares.add(new Lugar(listaedificios[i1]));
                    }
                    Ciudad s=new Ciudad(e.getAttribute("nombre"),e.getAttribute("bandera"),e.getAttribute("moneda"),e.getAttribute("lugaresdeinteres"),e.getAttribute("personaje"),e.getAttribute("industria"),e.getAttribute("fauna"),e.getAttribute("idiomas"),null,lugares,Double.parseDouble(e.getAttribute("Latitud")),Double.parseDouble(e.getAttribute("Longitud")));
                    ciudades.add(s);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public ArrayList<Ciudad> obtenerListaDeCiudades(){
        return ciudades;
    }
    

    private Ciudad crearCiudad(){
        return null;
    }

    private Lugar crearLugar(){
        return null;
    }

}
