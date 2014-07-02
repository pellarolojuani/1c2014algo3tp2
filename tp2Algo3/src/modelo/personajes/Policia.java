 package modelo.personajes;

 import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Observable;

public class Policia extends Observable{
    private Grado grado;
	private Ciudad ciudadActual;
    private String nombre;
    private int velocidadKmHora;
    private String ultimaPista;
    private int nroArrestos;


   
    
    public Policia(String nombre){
    	
		this.nombre  = nombre;
		this.nroArrestos = 0;
		grado = Grado.NOVATO;
		velocidadKmHora=grado.obtenerVelocidad();
	}
    
    public void visitarLugar(Lugar lugar){
		
    	if(lugar.obtenerPista()=="¡Te han disparado!" || lugar.obtenerPista()=="¡Te han arrojado un cuchillo!")
    	{
    		this.visitarLugarHerida(lugar);
    		return;
    	}
    	
    			
    	if( lugar.obtenerNumVisitas() == 0)
    	{
    		Tiempo.aumentarHoras(1);
    		lugar.aumentarNumVisitas();
    	}
    	else if(lugar.obtenerNumVisitas() == 1)
    	{
    		lugar.aumentarNumVisitas();
    		Tiempo.aumentarHoras(2);
    	}
    	else 
    	{
    		Tiempo.aumentarHoras(3);
    		lugar.aumentarNumVisitas();
    	}
        ultimaPista=lugar.obtenerPista();
        
        CuartelGeneral.getInstance().notificarVisitaA(lugar);
	}
	
	private void visitarLugarHerida(Lugar lugar) {
		
		if( lugar.obtenerPista()=="¡Te han disparado" )
    	{
    		Tiempo.aumentarHoras(4);
    	}
    	else //herida cuchillo
    	{
    		Tiempo.aumentarHoras(2);
    	}
        ultimaPista=lugar.obtenerPista();
        
        CuartelGeneral.getInstance().notificarVisitaA(lugar);
	}

	public void viajarA(Ciudad destino){
		double auxTiempoViajeHs = this.ciudadActual.distanciaA(destino) / this.velocidadKmHora;
		this.ciudadActual = destino;
		Tiempo.aumentarHoras( (int) Math.round(auxTiempoViajeHs) );
};

    public void asignarNuevoCasoEn(Ciudad ciudad){
        this.ciudadActual=ciudad;
    }
	
	public Grado obtenerGrado(){
		return this.grado;
	};

	public void promoverGrado(){
        this.grado = this.grado.getNext();
		// Seteo la velocidad
		if(this.velocidadKmHora < 1500)
			this.velocidadKmHora += 200;
	}
	
	public Ciudad obtenerCiudadActual(){
		return this.ciudadActual;
	};

    public void emitirOrdenDeArrestoPara(Sospechoso sospechoso){
        CuartelGeneral.getInstance().emitirOrdenDeArrestoPara(sospechoso);
    }

    public String obtenerUltimaPista() {
        return ultimaPista;
    }

    public void arrestarLadron() {
        nroArrestos++;
        
        if(nroArrestos==5) this.promoverGrado();
        
        if(nroArrestos==10) this.promoverGrado();
        
        if(nroArrestos==20) this.promoverGrado();

    }

    public int obtenerNroArrestos(){
        return nroArrestos;
    }

    public Element serializar(Document doc) {
        Element elementoPolicia=doc.createElement("Policia");
        elementoPolicia.setAttribute("nombre",nombre);
        elementoPolicia.setAttribute("grado",grado.name());
        elementoPolicia.setAttribute("nroArrestos",Integer.toString(nroArrestos));
        elementoPolicia.setAttribute("ciudadActual",ciudadActual.getNombre());
        return elementoPolicia;
    }

    public Policia(String nombre, Grado grado, int nroArrestos,Ciudad ciudadActual){
        this.nroArrestos=nroArrestos;
        this.nombre=nombre;
        this.grado=grado;
        this.velocidadKmHora=grado.obtenerVelocidad();
        this.ciudadActual=ciudadActual;
        System.out.println("El poli es:"+nombre);
        System.out.println("El poli arresto:"+nroArrestos);
        System.out.println("El poli es de grado:"+grado);
        System.out.println("El poli esta en:"+ciudadActual.getNombre());
    }

	public int obtenerVelocidadViaje() {
		return this.velocidadKmHora;
	}
}
