package modelo.juego;

import modelo.descripciones.Sexo;
import modelo.elementosDelJuego.CreadorDePistas;
import modelo.elementosDelJuego.ObjetoRobado;
import modelo.elementosDelJuego.Tiempo;
import modelo.elementosDelJuego.Valor;
import modelo.geografico.Ciudad;
import modelo.geografico.Grafo;
import modelo.geografico.Lugar;
import modelo.geografico.RecorridoLadron;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;

import java.util.ArrayList;

public class Caso {

	private Ciudad ciudadDelRobo;
	private Ciudad ciudadActual;
	private ObjetoRobado objetoRobado;
	private Sospechoso ladron;
    private Tiempo tiempoFinal;
    private Tiempo tiempoInicial;
    private Tiempo tiempoActual;
    private Lugar ubicacionLadron;
    private RecorridoLadron recorridoLadron;
    private ArrayList<Ciudad>ciudadesConPistas;
	private Policia policia;

    public Caso(ArrayList<Ciudad> ciudades, Grafo grafo, Valor valor, Policia policia, ArrayList<Sospechoso> sospechosos) {
    	this.objetoRobado = new ObjetoRobado(valor, ciudades.get(0),"Descripcion objeto");
        this.policia = policia;
        this.ladron= elegirLadron(sospechosos);
        this.recorridoLadron = new RecorridoLadron(ciudades,this.obtenerCantidadCiudades(valor),grafo);
        System.out.println("El recorrido del ladron es:");
        for(Ciudad ciudad: recorridoLadron.obtenerCiudades()){
            System.out.println(ciudad.getNombre());
        }
        System.out.println("Fin del recorrido");


        this.ciudadDelRobo=recorridoLadron.obtenerCiudadDelRobo();
        ciudadesConPistas=new ArrayList<Ciudad>();
        plantarPistas(ciudadDelRobo);

    }

    private Sospechoso elegirLadron(ArrayList<Sospechoso> sospechosos) {
        //Elige un ladron al azar entre los sospechosos.
        return sospechosos.get((int)(Math.random()*sospechosos.size()+0));
    }

    public Caso(Policia poli, ObjetoRobado bolaDeOro) {
		
		Tiempo.iniciar();
		
		this.policia = poli;
		
		poli.asignarNuevoCasoEn(bolaDeOro.obtenerCiudadOrigen());
		
		
	}
	
    public int obtenerCantidadCiudades(Valor valor ) {
        if (valor == Valor.COMUN) return 4;
        if (valor == Valor.VALIOSO) return 5;
        if (valor == Valor.MUY_VALIOSO) return 7;
		return 0;
    }
    
    public Ciudad obtenerCiudadRobo(){
		return this.ciudadDelRobo;
	}
	
	public ObjetoRobado obtenerObjetoRobado(){
		return this.objetoRobado;
	}
	
	public Sexo obtenerSexoLadron(){
		return this.ladron.obtenerDescripcion().getSexo();
	}

    private void plantarPistas(Ciudad ciudad) {
        // Planta las pistas sobre la ciudad siguiente en la ciudad
            Ciudad ciudadSiguiente = recorridoLadron.obtenerCiudadSiguiente(ciudad);
            CreadorDePistas creadorDePistas = new CreadorDePistas(ciudadSiguiente, ladron);
            creadorDePistas.plantarPistas(ciudad);
            ciudadesConPistas.add(ciudad);
    }

	public int obtenerTiempoTranscurridoEnHs() {
		return (Tiempo.getTiempo());
	}

    public boolean ciudadTienePistas(Ciudad arg) {
        //Devuelve true si la ciudad recibida ya tiene pistas sobre el caso.
        return ciudadesConPistas.contains(arg);
    }

    public void plantarPistasEnCiudadSiguienteA(Ciudad ciudad) {
        //Planta las pistas que correspondan en la ciudad siguiente a la recibida.
        Ciudad ciudadSiguiente=recorridoLadron.obtenerCiudadSiguiente(ciudad);
        plantarPistas(ciudadSiguiente);
    }

    public boolean ladronEstaEnLugar(Lugar lugar) {
        return recorridoLadron.obtenerLugarFinal()==lugar;
    }

    public Sospechoso obtenerLadron() {
        return ladron;
    }

    public String obtenerDescripcionDelRobo() {
        return "Atencion! Se ha registrado el robo de "+objetoRobado.obtenerDescripcion()+" en la ciudad "+ciudadDelRobo.getNombre()+".Testigos afirman que el ladron es de sexo "+obtenerSexoLadron();
    }
}
