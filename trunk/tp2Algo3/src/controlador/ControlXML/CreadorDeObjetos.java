package controlador.ControlXML;

import modelo.elementosDelJuego.ObjetoRobado;
import modelo.geografico.Ciudad;
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

/**
 * Created by chris on 11/06/2014.
 */
public class CreadorDeObjetos {
    private ArrayList<ObjetoRobado> objetos;
    //Lo creamos con el XML de objetos.
    public CreadorDeObjetos(ArrayList<Ciudad> ciudades){
        objetos=new ArrayList<ObjetoRobado>();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {

            File xmlFile = new File("objetos.xml");
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList objetosList=document.getElementsByTagName("Objeto");

            for (int i = 0; i < objetosList.getLength(); i++) {
                Element e= (Element) objetosList.item(i);
                Ciudad ciudad=buscarCiudad(ciudades, e.getAttribute("ciudad"));
                ObjetoRobado s=new ObjetoRobado(ciudad,e.getAttribute("descripcion"));
                objetos.add(s);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Ciudad buscarCiudad(ArrayList<Ciudad> ciudades, String nombreCiudad) {
        for(Ciudad ciudad: ciudades){
            if(ciudad.getNombre().equals(nombreCiudad))return ciudad;
        }
        return null;
    }


    public ArrayList<ObjetoRobado> obtenerListaDeObjetos(){
        return objetos;
    }
}
