package modelo.geografico;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CiudadTest {

    private Ciudad unaCiudad;
    private Ciudad otraCiudad;

    @Before
    public void setUp() {
        unaCiudad = new Ciudad("unNombre","unaBandera","unaMoneda","unLugarDeInteres","unPersonaje","unaIndustria","unaFauna","unIdioma",new ArrayList<Lugar>(),10,20);
        otraCiudad = new Ciudad("","","","","","","","",new ArrayList<Lugar>(),30,40);
    }

    @Test
    public void testObtenerCiudadesDestinoDisponibles() {
        Ciudad otraCiudad=new Ciudad();
        unaCiudad.agregarCiudadVisitable(otraCiudad);
        assertEquals(unaCiudad.obtenerCiudadesDestinoDisponibles().get(0),otraCiudad);
    }

    @Test
    public void testObtenerLugaresDisponibles() {
        Lugar unLugar = new Lugar(TipoEdificio.BANCO);
        unaCiudad.agregarLugar(unLugar);
        assertEquals(unaCiudad.obtenerLugaresDisponibles().get(0), unLugar);
    }

    @Test
    public void testDistanciaA() {
        unaCiudad.agregarCiudadVisitable(otraCiudad);
        otraCiudad.agregarCiudadVisitable(unaCiudad);
        //Verificamos que el calculo de la distancia entre las ciudades de el mismo valor, llamando a distanciaA desde ambas, con una toleracia de 1m.
        assertEquals(unaCiudad.distanciaA(otraCiudad),otraCiudad.distanciaA(unaCiudad),0.001);
    }

    @Test
    public void testAgregarCiudadVisitable() {
        unaCiudad.agregarCiudadVisitable(otraCiudad);
        assertEquals(unaCiudad.obtenerCiudadesDestinoDisponibles().contains(otraCiudad),true);
    }

    @Test
    public void testGetNombre() {
        assertEquals(unaCiudad.getNombre(),"unNombre");
    }

    @Test
    public void testAgregarLugares() {
        ArrayList<Lugar> lugares=new ArrayList<Lugar>();
        unaCiudad.agregarLugares(lugares);
        assertEquals(unaCiudad.obtenerLugaresDisponibles(),lugares);
    }

    @Test
    public void testObtenerMoneda() {
        assertEquals(unaCiudad.obtenerIndustria(), "unaIndustria");
    }

    @Test
    public void testObtenerIdioma() {
        assertEquals(unaCiudad.obtenerIdioma(), "unIdioma");
    }

    @Test
    public void testObtenerIndustria() {
        assertEquals(unaCiudad.obtenerIndustria(),"unaIndustria");
    }

    @Test
    public void testObtenerBandera() {
        assertEquals(unaCiudad.obtenerBandera(), "unaBandera");
    }

    @Test
    public void testObtenerPuntosInteres() {
        assertEquals(unaCiudad.obtenerPuntosInteres(), "unLugarDeInteres");
    }

    @Test
    public void testObtenerPersonaje() {
        assertEquals(unaCiudad.obtenerPersonaje(), "unPersonaje");
    }

    @Test
    public void testObtenerFauna() {
        assertEquals(unaCiudad.obtenerFauna(), "unaFauna");
    }

    @Test
    public void testObtenerLugar() {
        Lugar unLugar = new Lugar(TipoEdificio.BANCO);
        unaCiudad.agregarLugar(unLugar);
        assertEquals(unaCiudad.obtenerLugar(TipoEdificio.BANCO), unLugar);
    }

    @Test
    public void testAgregarLugar() {
        Lugar otroLugar=new Lugar(TipoEdificio.AEROPUERTO);
        unaCiudad.agregarLugar(otroLugar);
        assertEquals(unaCiudad.obtenerLugaresDisponibles().contains(otroLugar),true);
    }

}
