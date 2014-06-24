package modelo.elementosDelJuego;

import modelo.descripciones.Descripcion;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;

import java.util.ArrayList;

//El cuartel general tambien tendria que ser unico
public class CuartelGeneral {
    private ArrayList<Sospechoso> sospechosos;
    private OrdenDeArresto orden;
    private Policia policia;

    public CuartelGeneral() {
        this.sospechosos = new ArrayList<Sospechoso>();
    }

    public void cargarSospechosos(ArrayList<Sospechoso> sospechosos) {
        this.sospechosos=sospechosos;
    }
    
    public void cargarSospechoso(Sospechoso unSospechoso){
    	this.sospechosos.add(unSospechoso);
    }

    public ArrayList<Sospechoso> buscarSospechoso(Descripcion otraDescripcion) {
        //Hay que implementar esto para que busque bien, y encuentre un sospechoso aunque la descripcion sea parcial.
        ArrayList<Sospechoso> s=new ArrayList<Sospechoso>();
        for(Sospechoso sospechoso: sospechosos){
            if(sospechoso.obtenerDescripcion().puedeSerIgualA(otraDescripcion))s.add(sospechoso);
        }
        return s;
    }
    
    public boolean sospechosoEsUnico(Descripcion unaDescripcion){
    	int cantidadDeMatches = 0;
    	for (int i=0; i<sospechosos.size()-1; i++){
    		if ( sospechosos.get(i).describeLoMismo(unaDescripcion) ){
    			cantidadDeMatches++;
    		}
    	}
    	
    	if ( cantidadDeMatches == 1 ){
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public void notificarDeArrestoAPolicia() {
    }

    public boolean fueEmitidaOrdenPara(Sospechoso ladron) {
        if(orden==null)return false;
        return orden.obtenerSospechoso()==ladron;
    }

    public void emitirOrdenDeArrestoPara(Sospechoso sospechoso) {
        orden=new OrdenDeArresto(sospechoso);
    }

    public void incorporarPolicia(Policia policia) {
        this.policia=policia;
    }
}
