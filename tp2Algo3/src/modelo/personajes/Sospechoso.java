package modelo.personajes;

import modelo.descripciones.Descripcion;


public class Sospechoso {
	private String nombre;
    private Descripcion descripcion;

    public Sospechoso(Descripcion descripcion) {
        this.descripcion = descripcion;
    }
    
    public Sospechoso(String nombre, Descripcion descripcion) {
    	this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Descripcion obtenerDescripcion() {
        return descripcion;
    }

    public String getNombre(){
       return nombre;
    }

	public String seniaComoString() {
		
		return this.obtenerDescripcion().getSenia().toString();
	}

	public String hobbyComoString() {
		return this.obtenerDescripcion().getHobby().toString();

	}

	public String peloComoString() {
		return this.obtenerDescripcion().getPelo().toString();

	}
	
	public String vehiculoComoString() {
		return this.obtenerDescripcion().getVehiculo().toString();

	}
}
