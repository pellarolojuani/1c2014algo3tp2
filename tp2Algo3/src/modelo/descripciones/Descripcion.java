package modelo.descripciones;

public class Descripcion {

	private Senia senia;
	private Hobby hobby;
	private Pelo pelo;
	private Vehiculo vehiculo;
	private Sexo sexo;

    public Descripcion(Sexo sexo, Hobby hobby, Pelo pelo, Senia senia, Vehiculo vehiculo) {
        this.senia = senia;
        this.hobby = hobby;
        this.pelo = pelo;
        this.vehiculo = vehiculo;
        this.sexo = sexo;
    }

    public Senia getSenia() {
        return senia;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public Pelo getPelo() {
        return pelo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public boolean puedeSerIgualA(Descripcion unaDescripcion){
		if ( (unaDescripcion.getSexo() != null) && (this.sexo != unaDescripcion.getSexo()) ){
			return false;
		}
		if ( (unaDescripcion.getVehiculo() != null) && (this.vehiculo != unaDescripcion.getVehiculo()) ){
			return false;
		}
		if ( (unaDescripcion.getHobby() != null) && (this.hobby != unaDescripcion.getHobby()) ){
			return false;
		}
		if ( (unaDescripcion.getPelo() != null) && (this.pelo != unaDescripcion.getPelo()) ){
			return false;
		}
		if ( (unaDescripcion.getSenia() != null) && (this.senia != unaDescripcion.getSenia()) ){
			return false;
		}
		return true;
	}
	
}
