package modelo.geografico;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class RecorridoLadron {
    private ArrayList<Ciudad> recorrido;
    private Lugar lugarFinal;

    public RecorridoLadron(Ciudad ciudadDelRobo, int numCiudadesARecorrer) {
        this.recorrido = new ArrayList<Ciudad>();

        do{
            //Genera recorridos hasta encontrar uno con la cantidad de ciudades que corresponda
            generarRecorrido(ciudadDelRobo, numCiudadesARecorrer);
        }while(recorrido.size()<numCiudadesARecorrer);

        ArrayList<Lugar> lugares = recorrido.get(recorrido.size()-1).obtenerLugaresDisponibles();
        lugarFinal = lugares.get((int) (Math.random() * lugares.size()));
        System.out.println("EL LUGAR FINAL ES:   "+lugarFinal.obtenerTipo());
    }

    private void generarRecorrido(Ciudad ciudadDelRobo, int numCiudadesARecorrer){
        recorrido=new ArrayList<Ciudad>();
        recorrido.add(ciudadDelRobo);
        ArrayList<Ciudad> ciudadesDisponibles;
        Ciudad proximaCiudad;
        for (int i = 0; i < numCiudadesARecorrer; i++) {
            ciudadesDisponibles=recorrido.get(i).obtenerCiudadesDestinoDisponibles();
            //Si todas las ciudades disponibles desde la actual ya estan en el recorrido, lo devuelve como esta.
            if(recorrido.containsAll(ciudadesDisponibles))return;
            //Si no, elige una al azar entre las disponibles.
            do{
                proximaCiudad=ciudadesDisponibles.get((int)(Math.random()*ciudadesDisponibles.size()));
            }while(recorrido.contains(proximaCiudad));
            recorrido.add(proximaCiudad);
        }
    }

    public Ciudad obtenerCiudadFinal() {
        return this.recorrido.get(this.recorrido.size() - 1);
    }

    public Ciudad obtenerCiudadSiguiente(Ciudad ciudad) {
        for (int i = 0; i < this.recorrido.size() - 1; i++) {
            if (this.recorrido.get(i) == ciudad)
                return this.recorrido.get(i + 1);
        }
        return null;
    }

    public ArrayList<Ciudad> obtenerCiudades() {
        return this.recorrido;
    }

    public Lugar obtenerLugarFinal() {
        return lugarFinal;
    }

    public Ciudad obtenerCiudadDelRobo() {
        return recorrido.get(0);
    }

    public Element serializar(Document doc) {
        Element elementoRecorrido=doc.createElement("RecorridoLadron");
        String recorridoComoString="";
        for (int i = 0; i < recorrido.size()-1; i++) {
            recorridoComoString=recorridoComoString+recorrido.get(i).getNombre()+",";
        }
        recorridoComoString=recorridoComoString+recorrido.get(recorrido.size()-1).getNombre();
        elementoRecorrido.setAttribute("ciudades",recorridoComoString);
        elementoRecorrido.setAttribute("lugarFinal",lugarFinal.obtenerTipo().name());
        return elementoRecorrido;
    }

    public RecorridoLadron(ArrayList<Ciudad> ciudades, TipoEdificio tipoLugarFinal){
        recorrido=ciudades;
        for(Lugar l: ciudades.get(ciudades.size()-1).obtenerLugaresDisponibles()){
            if(l.obtenerTipo()==tipoLugarFinal){
                lugarFinal=l;
                break;
            }
        }
    }
}