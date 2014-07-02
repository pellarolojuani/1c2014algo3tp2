package modelo.elementosDelJuego;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.descripciones.Senia;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.geografico.RecorridoLadron;
import modelo.geografico.TipoEdificio;
import modelo.personajes.Sospechoso;

public class CreadorDePistas {

    private Ciudad ciudadSiguiente;
    private Sospechoso ladron;

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
	private ArrayList<Pista> pistasLadron;
	private RecorridoLadron recorridoLadron;
	private Boolean heridaCuchillo;
	private Boolean heridaPistola;
	private int aux_heridaCuchillo; //es un contador que utilizo para saber a partir de que ciudad
									//puede haber herida de cuchillo
	private int aux_heridaPistola;
	private Dificultad dificultad;
	
    
    public CreadorDePistas(RecorridoLadron unRecorrido, Sospechoso ladron){
    	
    	this.recorridoLadron = unRecorrido;
    	this.ladron = ladron;
    	this.heridaCuchillo = false;
    	this.heridaPistola = false;
    	
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
		
		this.crearPistasSobreLadron();

				
    	if( unRecorrido.obtenerCiudades().size() == 5 )
    		this.crearPistasFaciles();
    	
    	else if(unRecorrido.obtenerCiudades().size() == 6)
    		this.crearPistasIntermedias();
    	
    	else this.crearPistasDificiles();
    	
    	
    }

    private void crearPistasSobreLadron() {
    	
    	this.pistasLadron = new ArrayList<Pista>();
		this.pistasLadron.add(new Pista(this.textoHobby + ladron.hobbyComoString()));
		this.pistasLadron.add(new Pista(this.textoSenia + ladron.seniaComoString()));
		this.pistasLadron.add(new Pista(this.textoVehiculo + ladron.vehiculoComoString()));
		this.pistasLadron.add(new Pista(this.textoPelo + ladron.peloComoString()));
		
        Collections.shuffle(this.pistasLadron);
        
	}

	private void crearPistasFaciles() {
    	
		this.aux_heridaCuchillo = 3;
		this.aux_heridaPistola = 10;
		this.dificultad = Dificultad.FACIL;
		
	}

	private void crearPistasIntermedias() {
		
		this.aux_heridaCuchillo = 3;
		this.aux_heridaPistola = 4;
		this.dificultad = Dificultad.INTERMEDIO;
		
	}

	private void crearPistasDificiles() {

		this.aux_heridaCuchillo = 3;
		this.aux_heridaPistola = 5;
		this.dificultad = Dificultad.DIFICIL;
		
	}

	public CreadorDePistas(){this.ciudadSiguiente=null;this.ladron=null;}

    public Pista crearNuevaPista(TipoEdificio tipo){
    	
    	if( (this.heridaCuchillo == true) && Math.random()< 0.3 ) {
    		return crearHeridaCuchillo();
    	}
    	if( (this.heridaPistola == true) && Math.random() < 0.3 ){
    		return crearHeridaPistola();
    	}
    	if(this.ciudadSiguiente!=null){
    		if(tipo==TipoEdificio.AEROPUERTO)return crearPistaAeropuerto();
    		if(tipo==TipoEdificio.PUERTO)return crearPistaPuerto();
    		if(tipo==TipoEdificio.BANCO)return crearPistaBanco();
    		if(tipo==TipoEdificio.BOLSA)return crearPistaBolsa();
    		return crearPistaBiblioteca();
    	}
    	return new Pista("Está cerca");
    }

    private Pista crearHeridaPistola() {
		return new PistaHeridaPistola();
	}

	private Pista crearHeridaCuchillo() {
		return new PistaHeridaCuchillo();
	}

	private Pista crearPistaBanco(){
        return this.crearPistaBolsa();
    }

    private Pista crearPistaBolsa(){

    	if(this.dificultad == Dificultad.DIFICIL){
    		
        	return new Pista(this.textoIndustria + ciudadSiguiente.obtenerIndustria());
        	
        }
        	
        if(this.dificultad == Dificultad.INTERMEDIO){
        	if(Math.random() < 0.5 )
        		return new Pista(this.textoIndustria + ciudadSiguiente.obtenerIndustria());
        	return new Pista(this.textoMoneda + ciudadSiguiente.obtenerMoneda());
        }
        // Caso FACIL
            
        return new Pista(this.textoMoneda + ciudadSiguiente.obtenerMoneda());
    }

    private Pista crearPistaBiblioteca(){

	if(this.dificultad == Dificultad.DIFICIL){
    		
    	return new Pista(this.textoFauna + ciudadSiguiente.obtenerFauna());
    	
    }
    	
    if(this.dificultad == Dificultad.INTERMEDIO);//se trata igual que el caso facil
    	
    // Caso FACIL
    if(Math.random() < 0.5 )    
    	return new Pista(this.textoLugarinteres + ciudadSiguiente.obtenerPuntosInteres());
    return new Pista(this.textoPersonaje + ciudadSiguiente.obtenerPersonaje());
    
    }

    private Pista crearPistaPuerto(){
    	
    	if(this.dificultad == Dificultad.DIFICIL){
    		
    		if(Math.random() < 0.8 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    	}
    	
    	if(this.dificultad == Dificultad.INTERMEDIO){
    		if(Math.random() < 0.5 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    	}
    	// Caso FACIL
        return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    }

    private Pista crearPistaAeropuerto(){
    	
	if(this.dificultad == Dificultad.DIFICIL){
    		
    		if(Math.random() < 0.8 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    	}
    	
    	if(this.dificultad == Dificultad.INTERMEDIO){
    		if(Math.random() < 0.5 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    	}
    	// Caso FACIL
        
        return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    }

    public void plantarPistasEnCiudad(Ciudad ciudad, Pista pistaLadron) {
    	
        for(Lugar lugar: ciudad.obtenerLugaresDisponibles()){
        	
        	if( lugar == recorridoLadron.obtenerLugarFinal() ) continue;
        	        		
        	lugar.plantarPista(crearNuevaPista( lugar.obtenerTipo()).ampliarPista(pistaLadron));
        	
        	pistaLadron = null;
        }
    }


public void plantarPistas() {
	
	Iterator<Pista> iteradorPistaLadron = pistasLadron.iterator();
	Pista aux_pistaLadron;
	
    for( Ciudad ciudad: recorridoLadron.obtenerCiudades()){
    	
    	if( this.aux_heridaCuchillo <= 0) this.heridaCuchillo=true;
    	if( this.aux_heridaPistola <= 0) this.heridaPistola=true;
    	
    	
        this.ciudadSiguiente = recorridoLadron.obtenerCiudadSiguiente(ciudad);
        
        if(iteradorPistaLadron.hasNext())
        	aux_pistaLadron = iteradorPistaLadron.next(); 
        else aux_pistaLadron=null;
        
        this.plantarPistasEnCiudad(ciudad, aux_pistaLadron);
        
        this.aux_heridaCuchillo--;
        this.aux_heridaPistola--;
    }
}
}