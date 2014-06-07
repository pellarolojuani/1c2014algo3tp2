package geografico;

import descripciones.Descripcion;

import java.util.*;

public class Ciudad {

	private ArrayList<Lugar> lugares;
    private ArrayList<Ciudad> ciudades;

    public Ciudad() {
        this.lugares = new ArrayList<Lugar>();
    }

    public ArrayList<Ciudad> obtenerCiudadesDestinoDisponibles() {
        return ciudades;
    }

	public ArrayList<Lugar> obtenerLugaresDisponibles(){
		return lugares;
	}
	
	public int distanciaA(Ciudad unaCiudad){return 0;}

    public void setNombre(String nombre) {}

    public void setBandera(ArrayList<Color> colores) {}

    public void setMoneda(Moneda moneda) {}

    public void setPuntoInteres(PuntoInteres monteHuascaran) {}

    public void setArte(Arte arte) {}

    public void setIndustrias(Industria moda) {}

    public void agregarLugar(Lugar lugar) {
        lugares.add(lugar);
    }
}
