package controlador.ControlXML;

import modelo.geografico.Ciudad;
import modelo.geografico.CreadorDeGrafoCiudades;
import modelo.geografico.Lugar;
import modelo.geografico.TipoEdificio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CreadorDeCiudades {
    //Lo creamos con el archivo XML que tiene las ciudades. Creamos cada ciudad con crearCiudad, creamos sus lugares con crearLugar.

    private CreadorDeGrafoCiudades creadordegrafo;
    public ArrayList<Ciudad>ciudades;
    
    public CreadorDeCiudades() {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try { 
        	File xmlFile = new File("ciudades.xml");  
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList ciudadesList=document.getElementsByTagName("Ciudad");
            ArrayList<Ciudad>ciudades= new ArrayList<Ciudad>();
            
            for (int i = 0; i < ciudadesList.getLength(); i++) {
                    Element e= (Element)ciudadesList.item(i);
                    
                    //Se extrae la lista de edificios de la ciudad, se la divide con split y se la carga a objetos lugar. 
                    //Se agregan a cada ciudad.
                    String[] listaedificios = e.getAttribute("edificios").split(",");

               	 	ArrayList<Lugar>lugares = new ArrayList<Lugar>();
                    for(int i1 = 0; i1 < listaedificios.length; i1++){
                        if(listaedificios[i1].equals("AEROPUERTO")) {	
                        	Lugar a = new Lugar(TipoEdificio.AEROPUERTO);
                        	lugares.add(a);
                        	};
                        if(listaedificios[i1].equals("PUERTO")) {	
                          	Lugar a = new Lugar(TipoEdificio.PUERTO);
                        	lugares.add(a);
                           	};
                        if(listaedificios[i1].equals("BANCO")) {	
                            Lugar a = new Lugar(TipoEdificio.BANCO);
                        	lugares.add(a);
                            };     	
                        if(listaedificios[i1].equals("BOLSA")) {	
                           	Lugar a = new Lugar(TipoEdificio.BOLSA);
                        	lugares.add(a);
                           	};
                        if(listaedificios[i1].equals("BIBLIOTECA")) {	
                            Lugar a = new Lugar(TipoEdificio.BIBLIOTECA);
                        	lugares.add(a);
                            };
	                 }
	                
	                Ciudad s=new Ciudad(e.getAttribute("nombre"),e.getAttribute("bandera"),e.getAttribute("moneda"),e.getAttribute("lugaresdeinteres"),e.getAttribute("personaje"),e.getAttribute("industria"),e.getAttribute("fauna"),e.getAttribute("idiomas"),null,lugares,Double.parseDouble(e.getAttribute("Latitud")),Double.parseDouble(e.getAttribute("Longitud")));                 
	                ciudades.add(s);
                   
            }       
            
            Collections.shuffle(ciudades);
            this.ciudades=ciudades;

            this.creadordegrafo = new CreadorDeGrafoCiudades(this.ciudades.size());
            this.setearciudadesvisitables(this.ciudades);
            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private void setearciudadesvisitables(ArrayList<Ciudad> ciudades){
    	for(int i2 = 0; i2 < ciudades.size(); i2++){
    		List<Integer> visitables=this.creadordegrafo.obtenerciudadesvisitables(i2);
    		
    		for(int i3 = 0; i3 < visitables.size(); i3++){
    		this.ciudades.get(i2).agregarCiudadVisitable(this.ciudades.get(visitables.get(i3)));
    		
    		}
    	}
    	
    }
    
    public ArrayList<Ciudad> obtenerCiudades(){
        return this.ciudades;
    }
}
