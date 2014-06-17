package modelo.descripciones;

public class Descripcion {
	
	//declaracion de atributos
	private String senia;
	private String hobby;
	private String pelo;
	private String auto;
	private String sexo;
	
	//declaracion de metodos:
	
	//constructor
	public Descripcion(String unSexo, String unPelo, String unaSenia, String unAuto, String unHobby){
		this.sexo = unSexo;
		this.pelo = unPelo;
		this.senia = unaSenia;
		this.auto = unAuto;
		this.hobby = unHobby;
	}
	
	public String getSexo(){
		return this.sexo;
	}
	
	public String getAuto(){
		return this.auto;
	}
	
	public String getPelo(){
		return this.pelo;
	}
	
	public String getHobby(){
		return this.hobby;
	}
	
	public String getSenia(){
		return this.senia;
	}
	
	public boolean puedeSerIgualA(Descripcion unaDescripcion){
		if ( (unaDescripcion.getSexo() != null) && (this.sexo == unaDescripcion.getSexo()) ){
			return false;
		}
		if ( (unaDescripcion.getAuto() != null) && (this.auto == unaDescripcion.getAuto()) ){
			return false;
		}
		if ( (unaDescripcion.getHobby() != null) && (this.hobby == unaDescripcion.getHobby()) ){
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
