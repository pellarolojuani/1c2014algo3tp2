package controlador.ControlXML;

import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreadorDeCiudades {
    //Lo creamos con el archivo XML que tiene las ciudades. Creamos cada ciudad con crearCiudad, creamos sus lugares con crearLugar.

    private ArrayList<Lugar>lugares;
    public ArrayList<Ciudad>ciudades;
    
    public CreadorDeCiudades(int numerodeciudades) {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try { 
        	File xmlFile = new File("ciudades.xml");  
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList ciudadesList=document.getElementsByTagName("Ciudad");
            
            for (int i = 0; i < ciudadesList.getLength(); i++) {
                    Element e= (Element)ciudadesList.item(i);
                    
                    //Se extrae la lista de edificios de la ciudad, se la divide con split y se la carga a objetos lugar. 
                    //Se agregan a cada ciudad.
                    String[] listaedificios = e.getAttribute("edificios").split(",");
                    ArrayList<Lugar>lugares = new ArrayList<Lugar>();
	                 for(int i1 = 0; i1 < listaedificios.length; i1++){
	                	 Lugar a = new Lugar(listaedificios[i1]);
	                 	 lugares.add(a);
	                 }
	                
	                Ciudad s=new Ciudad(e.getAttribute("nombre"),e.getAttribute("bandera"),e.getAttribute("moneda"),e.getAttribute("lugaresdeinteres"),e.getAttribute("personaje"),e.getAttribute("industria"),e.getAttribute("fauna"),e.getAttribute("idiomas"),null,lugares,Double.parseDouble(e.getAttribute("Latitud")),Double.parseDouble(e.getAttribute("Longitud")));                 
                    ArrayList<Ciudad>ciudades = new ArrayList<Ciudad>();
	                ciudades.add(s);
	                System.out.println(s.getNombre());
            }           

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public ArrayList<Ciudad> obtenerCiudades(){
        return ciudades;
    }
    
    
}
