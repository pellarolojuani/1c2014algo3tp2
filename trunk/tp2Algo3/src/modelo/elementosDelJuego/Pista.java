package modelo.elementosDelJuego;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.descripciones.Descripcion;
import modelo.descripciones.Senia;
import modelo.personajes.Sospechoso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Pista {

	public String pista;

	protected String textoSenia;
	protected String textoPelo;
	protected String textoHobby;
	protected String textoVehiculo;

	protected String textoFauna;
	protected String textoIndustria;
	protected String textoIdioma;
	protected String textoPersonaje;
	protected String textoLugarinteres;
	protected String textoBanderaAeropuerto;
	protected String textoBanderaPuerto;
	protected String textoMoneda;

	public Pista() {
		this.pista = "Lo siento, no he visto a nadie sospechoso.";
	}

	public Pista(Sospechoso ladron) {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			File xmlFile = new File("pistas.xml");

			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();

			NodeList listaPistas = document.getElementsByTagName("Pista");

			Element e = (Element) listaPistas.item(0);

			if (ladron.obtenerDescripcion().getSenia() == Senia.JOYAS)
				textoSenia = e.getAttribute("textoSeniaJoyas");
			else if (ladron.obtenerDescripcion().getSenia() == Senia.ANILLO)
				textoSenia = e.getAttribute("textoSeniaAnillo");
			else
				textoSenia = e.getAttribute("textoSeniaCicatrizTatuaje");

			textoPelo = e.getAttribute("textoPelo");
			textoHobby = e.getAttribute("textoHobby");
			textoVehiculo = e.getAttribute("textoVehiculo");

			textoFauna = e.getAttribute("textoFauna");
			textoIndustria = e.getAttribute("textoIndustria");
			textoIdioma = e.getAttribute("textoIdioma");
			textoPersonaje = e.getAttribute("textoPersonaje");
			textoLugarinteres = e.getAttribute("textoLugarInteres");
			textoBanderaAeropuerto = e.getAttribute("textoAeropuertoBandera");
			textoBanderaPuerto = e.getAttribute("textoPuertoBandera");
			textoMoneda = e.getAttribute("textoMoneda");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.pista = "Lo siento, no he visto a nadie sospechoso.";

	}

	public String contenidoComoString() {
		return this.pista;
	}
}
