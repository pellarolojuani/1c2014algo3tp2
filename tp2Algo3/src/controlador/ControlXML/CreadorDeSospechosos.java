package controlador.ControlXML;
import modelo.descripciones.*;
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

public class CreadorDeSospechosos {
    private ArrayList<Sospechoso> sospechosos;

    public CreadorDeSospechosos() {
        sospechosos=new ArrayList<Sospechoso>();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {

        	File xmlFile = new File("sospechosos.xml");  
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList sospechososList=document.getElementsByTagName("Sospechoso");

            for (int i = 0; i < sospechososList.getLength(); i++) {
                    Element e= (Element) sospechososList.item(i);

                    Descripcion d=crearDescripcion(e);

                    Sospechoso s=new Sospechoso(e.getAttribute("nombre"),d);
                    sospechosos.add(s);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Sospechoso> obtenerSospechosos(){
        return sospechosos;
    }
    private Descripcion crearDescripcion(Element e){

        Sexo sexo= Sexo.valueOf(e.getAttribute("sexo").toUpperCase());
        Hobby hobby=Hobby.valueOf(e.getAttribute("hobby").toUpperCase());
        Pelo pelo=Pelo.valueOf(e.getAttribute("pelo").toUpperCase());
        Senia senia=Senia.valueOf(e.getAttribute("senia").toUpperCase());
        Vehiculo vehiculo=Vehiculo.valueOf(e.getAttribute("vehiculo").toUpperCase());
        return new Descripcion(sexo, hobby, pelo, senia,vehiculo);

    }
}
