package modelo.elementosDelJuego;

import modelo.descripciones.Senia;
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

public class Pista {

	public String pista;

	

    public Pista(String pista){
        this.pista=pista;
    }

	public Pista() {
		this.pista = "Lo siento, no he visto a nadie sospechoso.";
	}

	


	public String contenidoComoString() {
		return this.pista;
	}

	public Pista ampliarPista(Pista pistaLadron) {
		if(pistaLadron==null) return this;
		
		this.pista = this.pista + pistaLadron.contenidoComoString();
		return this;
	}
}
