package descripciones;

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
}
