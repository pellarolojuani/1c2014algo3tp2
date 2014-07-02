package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class PistaHeridaCuchilloTest {

	@Test
	public void testContenidoComoString() {

		assertEquals("¡Te han arrojado un cuchillo!", new PistaHeridaCuchillo().contenidoComoString());
	
	}
}
