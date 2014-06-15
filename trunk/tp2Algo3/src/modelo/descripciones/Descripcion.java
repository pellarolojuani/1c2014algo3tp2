package modelo.descripciones;

public class Descripcion {
	
	//declaracion de atributos
	private Senia senia;
	private Hobbie hobbie;
	private Pelo pelo;
	private Auto auto;
	private Sexo sexo;
	
	//declaracion de metodos:
	
	//constructor
	public Descripcion(Sexo unSexo, Pelo unPelo, Senia unaSenia, Auto unAuto, Hobbie unHobbie){
		this.sexo = unSexo;
		this.pelo = unPelo;
		this.senia = unaSenia;
		this.auto = unAuto;
		this.hobbie = unHobbie;
	}
	
	public Sexo getSexo(){
		return this.sexo;
	}
	
	public Auto getAuto(){
		return this.auto;
	}
	
	public Pelo getPelo(){
		return this.pelo;
	}
	
	public Hobbie getHobbie(){
		return this.hobbie;
	}
	
	public Senia getSenia(){
		return this.senia;
	}
	
	public boolean puedeSerIgualA(Descripcion unaDescripcion){
		if ( (unaDescripcion.getSexo() != null) && (this.sexo == unaDescripcion.getSexo()) ){
			return false;
		}
		if ( (unaDescripcion.getAuto() != null) && (this.auto == unaDescripcion.getAuto()) ){
			return false;
		}
		if ( (unaDescripcion.getHobbie() != null) && (this.hobbie == unaDescripcion.getHobbie()) ){
			return false;
		}
		if ( (unaDescripcion.getPelo() != null) && (this.pelo == unaDescripcion.getPelo()) ){
			return false;
		}
		if ( (unaDescripcion.getSenia() != null) && (this.senia == unaDescripcion.getSenia()) ){
			return false;
		}
		return true;
	}
	
}
