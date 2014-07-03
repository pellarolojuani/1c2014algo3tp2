package modelo.elementosDelJuego;

import static org.junit.Assert.*;
import modelo.personajes.Sospechoso;

import org.junit.Test;

public class OrdenDeArrestoTest {

	OrdenDeArresto unaOrden;
	
    @Test
    public void testOrdenDeArrestoYObtenerSospechoso() {
     Sospechoso unSospechoso = new Sospechoso(null);
     unaOrden = new OrdenDeArresto(unSospechoso);
     assertEquals(unSospechoso, unaOrden.obtenerSospechoso());
    }

}
