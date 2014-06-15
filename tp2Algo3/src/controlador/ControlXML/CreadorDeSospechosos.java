package controlador.ControlXML;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import modelo.descripciones.Descripcion;
import modelo.personajes.Sospechoso;

import java.util.ArrayList;

public class CreadorDeSospechosos {
    private String f;
    private ArrayList<Sospechoso> sospechosos;

    public CreadorDeSospechosos(String f) {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(f);
            Element element=document.getDocumentElement();
            NodeList sospechososList=element.getChildNodes();
            for (int i = 0; i < sospechososList.getLength(); i++) {
                if(sospechososList.item(0).getNodeType()==Node.ELEMENT_NODE){
                    Element e= (Element) sospechososList.item(i);
                    Descripcion d=crearDescripcion(e);
                    Sospechoso s=new Sospechoso(e.getAttribute("nombre"),d);
                    sospechosos.add(s);
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
    public ArrayList<Sospechoso> obtenerListaDeSospechosos(){
        return sospechosos;
    }
    private Descripcion crearDescripcion(Element e){
        String sexo=e.getAttribute("sexo");
        String hobby=e.getAttribute("hobby");
        String pelo=e.getAttribute("pelo");
        String senia=e.getAttribute("senia");
        String vehiculo=e.getAttribute("vehiculo");
        return new Descripcion(sexo,hobby,pelo,senia,vehiculo);

    }
}
