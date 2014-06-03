package juego;

import personajes.*;
import geografico.*;
import elementosDelJuego.*;
import descripciones.*;

public class Caso {
	
	//declaracion de atributos:
	private Ciudad ciudadDelRobo;
	private ObjetoRobado objetoRobado;
	private Ladron ladron;
	
	//declaracion de metodos:
	
	//constructor
	public Caso(Ciudad unaCiudad, ObjetoRobado unObjeto, Ladron unLadron){
		this.ciudadDelRobo = unaCiudad;
		this.objetoRobado = unObjeto;
		this.ladron = unLadron;
	}
	
	public Ciudad obtenerCiudadRobo(){
		return this.ciudadDelRobo;
	}
	
	public ObjetoRobado obtenerObjetoRobado(){
		return this.objetoRobado;
	}
	
	public Sexo obtenerSexoLadron(){
		return this.ladron.getDescripcion().getSexo();
	}

}
