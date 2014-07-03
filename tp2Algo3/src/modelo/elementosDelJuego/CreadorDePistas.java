package modelo.elementosDelJuego;

import modelo.descripciones.Senia;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.geografico.RecorridoLadron;
import modelo.geografico.TipoEdificio;
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
import java.util.Collections;
import java.util.Iterator;

public class CreadorDePistas {

    private Ciudad ciudadSiguiente;
    private Sospechoso ladron;

    private String textoSenia;
    private String textoPelo;
    private String textoHobby;
    private String textoVehiculo;


	private String textoFauna;
	private String textoIndustria;
	private String textoIdioma;
	private String textoPersonaje;
	private String textoLugarinteres;
	private String textoBanderaAeropuerto;
	private String textoBanderaPuerto;
	private String textoMoneda;
	private ArrayList<Pista> pistasLadron; //Aca guardo las pistas de la descripcion del sospechoso
	private RecorridoLadron recorridoLadron;
	private Boolean heridaCuchillo;
	private Boolean heridaPistola;
	private int aux_heridaCuchillo; //es un contador que utilizo para saber a partir de que ciudad
									//puede haber herida de cuchillo
	private int aux_heridaPistola;
	private Valor dificultad;
	
    
    public CreadorDePistas(RecorridoLadron unRecorrido, Sospechoso ladron, Valor nivel_dificultad){
    	
    	this.recorridoLadron = unRecorrido;
    	this.ladron = ladron;
    	this.heridaCuchillo = false;
    	this.heridaPistola = false;
    	this.dificultad = nivel_dificultad;
    	
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

				
    	if( dificultad == Valor.COMUN)
    		this.crearPistasFaciles();
    	
    	else if( dificultad == Valor.VALIOSO)
    		this.crearPistasIntermedias();
    	
    	else this.crearPistasDificiles();
    	
    	

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


        if (unRecorrido.obtenerCiudades().size() == 5)
            this.crearPistasFaciles();

        else if (unRecorrido.obtenerCiudades().size() == 6)
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
    	
		this.aux_heridaCuchillo = 4;
		this.aux_heridaPistola = 10;
		
	}



	private void crearPistasIntermedias() {
		
		this.aux_heridaCuchillo = 4;
		this.aux_heridaPistola = 5;
	
		
	}


	private void crearPistasDificiles() {
		this.aux_heridaCuchillo = 5;
		this.aux_heridaPistola = 5;
		
	}

    private Pista crearNuevaPista(TipoEdificio tipo){
    	
    	if( (this.heridaPistola == true) && Math.random() < 0.3 ){
    		return crearHeridaPistola();
    	}
    	if( (this.heridaCuchillo == true) && Math.random()< 0.3 ) {
    		return crearHeridaCuchillo();
    	}

    	if(this.ciudadSiguiente!=null){
    		if(tipo==TipoEdificio.AEROPUERTO)return crearPistaAeropuerto();
    		if(tipo==TipoEdificio.PUERTO)return crearPistaPuerto();
    		if(tipo==TipoEdificio.BANCO)return crearPistaBanco();
    		if(tipo==TipoEdificio.BOLSA)return crearPistaBolsa();
    		return crearPistaBiblioteca();
    	}
    	return new Pista("Esta cerca");
    }


    private Pista crearHeridaPistola() {
		return new Pista("¡Te han disparado!");
	}


	private Pista crearHeridaCuchillo() {
		return new Pista("¡Te han arrojado un cuchillo!");
	}


    private Pista crearPistaBanco() {
        return this.crearPistaBolsa();
    }

    private Pista crearPistaBolsa() {

    	if(this.dificultad == Valor.MUY_VALIOSO){
    		
        	return new Pista(this.textoIndustria + ciudadSiguiente.obtenerIndustria());
        	
        }
        	
        if(this.dificultad == Valor.VALIOSO){
        	if(Math.random() < 0.5 )
        		return new Pista(this.textoIndustria + ciudadSiguiente.obtenerIndustria());
        	return new Pista(this.textoMoneda + ciudadSiguiente.obtenerMoneda());
        }
        // Caso FACIL

        return new Pista(this.textoMoneda + ciudadSiguiente.obtenerMoneda());
    }

    private Pista crearPistaBiblioteca() {

    	if(this.dificultad == Valor.MUY_VALIOSO){
    		
    		return new Pista(this.textoFauna + ciudadSiguiente.obtenerFauna());
    	}
        // Caso FACIL o INTERMEDIO
        if (Math.random() < 0.5)
            return new Pista(this.textoLugarinteres + ciudadSiguiente.obtenerPuntosInteres());
        return new Pista(this.textoPersonaje + ciudadSiguiente.obtenerPersonaje());


    }

   


    private Pista crearPistaPuerto(){
    	
    	if(this.dificultad == Valor.COMUN){
    		
    		if(Math.random() < 0.8 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    	}
    	
    	if(this.dificultad == Valor.VALIOSO){
    		if(Math.random() < 0.5 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    	}
    	// Caso FACIL

        return new Pista(this.textoBanderaPuerto + ciudadSiguiente.obtenerBandera());
    }


    private Pista crearPistaAeropuerto(){
    	
	if(this.dificultad == Valor.MUY_VALIOSO){
    		
    		if(Math.random() < 0.8 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    	}
    	
    	if(this.dificultad == Valor.VALIOSO){
    		if(Math.random() < 0.5 )
    			return new Pista(this.textoIdioma + ciudadSiguiente.obtenerIdioma());
    		return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    	}
    	// Caso FACIL
        
        return new Pista(this.textoBanderaAeropuerto + ciudadSiguiente.obtenerBandera());
    }

    private void plantarPistasEnCiudad(Ciudad ciudad, int i) {
    	//uso i para lograr asegurarme plantar una pista del sospechoso en cada una de las primeras cuatro
    	//ciudades
    	Iterator<Pista> iteradorPistaLadron = pistasLadron.iterator();
    	Pista aux_pistaLadron=null;

    	
    	if( i<=4 && i>0 )
    		aux_pistaLadron = pistasLadron.get(--i); 
    	 
        for(Lugar lugar: ciudad.obtenerLugaresDisponibles()){
        	      	
        	if( lugar == recorridoLadron.obtenerLugarFinal() ) {
        		lugar.plantarPista(new Pista("¡Ahí esta el ladron!"));
        		continue;
        	}
        	        		
        	lugar.plantarPista(crearNuevaPista( lugar.obtenerTipo()).ampliarPista(aux_pistaLadron));
        	
        	//Siempre agrego un atributo en una ciudad, y al azar los otros atributos
        	if(iteradorPistaLadron.hasNext() && Math.random()<0.3 && !heridaCuchillo)
             	aux_pistaLadron = iteradorPistaLadron.next(); 
             else aux_pistaLadron=null;

        }
    }



public void plantarPistas() {
	
	int i=1;
    for( Ciudad ciudad: recorridoLadron.obtenerCiudades()){
    	
    	if( i > this.aux_heridaCuchillo ) this.heridaCuchillo=true;
    	if( i > this.aux_heridaPistola ) this.heridaPistola=true;
    	
    	
        this.ciudadSiguiente = recorridoLadron.obtenerCiudadSiguiente(ciudad);
        
        this.plantarPistasEnCiudad(ciudad,i);
        
        i++;
    }

}
}