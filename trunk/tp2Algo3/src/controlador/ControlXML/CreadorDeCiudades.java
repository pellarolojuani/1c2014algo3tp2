package controlador.ControlXML;

import modelo.descripciones.Descripcion;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.personajes.Sospechoso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<Lugar>lugares;
    public Ciudad ciudadespartida[];
    
    public CreadorDeCiudades(int numerodeciudades) {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try { 
        	File xmlFile = new File("ciudades.xml");  
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(xmlFile);
            Element element=document.getDocumentElement();
            NodeList ciudadesList=element.getChildNodes();
            
            for (int i = 0; i < ciudadesList.getLength(); i++) {
                if(ciudadesList.item(0).getNodeType()==Node.ELEMENT_NODE){
                    Element e= (Element) ciudadesList.item(i);
                    Ciudad s=new Ciudad();
                    ArrayList<Lugar> ciudadespre;
					ciudadespre.add(s);
                }
            
            
            
            
            
            Ciudad[] ciudadespartida = new Ciudad[numerodeciudades]; 
            Random rand = new Random();
            int contador = 0; 


            do 
            { 
                 int i;
                 Element e = (Element)ciudadesList.item(rand.nextInt(numerodeciudades + 1)); 
                 String[] listaedificios = e.getAttribute("edificios").split(":");
                 System.out.println(listaedificios);
                 

            	 ArrayList<Lugar> nuevoslugares = new ArrayList<Lugar>();
            	 
                 for(int i1 = 0; i1 < listaedificios.length; i1++){
                	 Lugar a = new Lugar(listaedificios[i1]);
                 	 nuevoslugares.add(a);
                 }
                 
                 Ciudad s=new Ciudad(e.getAttribute("nombre"),e.getAttribute("bandera"),e.getAttribute("moneda"),e.getAttribute("lugaresdeinteres"),e.getAttribute("personaje"),e.getAttribute("industria"),e.getAttribute("fauna"),e.getAttribute("idiomas"),null,nuevoslugares,Double.parseDouble(e.getAttribute("Latitud")),Double.parseDouble(e.getAttribute("Longitud")));                 
                
                 for(i = 0; i < contador; i++) 
                      if(ciudadespartida[i] == s) 
                           break; 
                 if(i == contador) 
                	 ciudadespartida[contador++] = s; 
            } while(contador < numerodeciudades); 
            

            
            for(int a = 0; a < numerodeciudades; a++) {
            	for (int b = 0; b < numerodeciudades; b++)
                if(ciudadespartida[a] != ciudadespartida[b]) 
            	ciudadespartida[a].agregarCiudadVisitable(ciudadespartida[b]);
            	
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public Ciudad[] obtenerCiudades(){
        return ciudadespartida;
    }
    
    
}
