package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import modelo.geografico.Ciudad;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;


public class ObjetoRobadoTest {

    Ciudad unaCiudad;

    @Before
    public void setUp() {

        unaCiudad = mock(Ciudad.class);
    }

    @Test
    public void testContenidoComoStringDevuelveLaDescripcionDelObjeto() {

        ObjetoRobado unObjeto = new ObjetoRobado(unaCiudad, "Copa del mundo");
        assertEquals("Copa del mundo", unObjeto.obtenerDescripcion());

    }

    @Test
    public void testObtenerCiudadOrigen() {

        ObjetoRobado unObjetoComun = new ObjetoRobado(unaCiudad, "La copa del mundo");

        assertEquals(unaCiudad, unObjetoComun.obtenerCiudadOrigen());

    }

}
