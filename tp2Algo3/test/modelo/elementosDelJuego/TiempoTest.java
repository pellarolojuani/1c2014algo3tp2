package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class TiempoTest {

	@Test
	public void testAumentarHorasAumentarElContadorDeHs() {
		Tiempo.iniciar();
		Tiempo.aumentarHoras(8);
		assertEquals(8,Tiempo.getTiempo());
	}

	@Test
	public void testGetTiempo() {
		fail("Not yet implemented");
	}

	@Test
	public void testTiempoComoString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIniciarIniciaLunesAlas7Hs() {
		Tiempo.iniciar();
		assertEquals("Lunes 7 Hs", Tiempo.tiempoComoString() );
	}
	
	@Test
	public void testIniciarPoneElTiempoEnHsCero() {
		Tiempo.iniciar();
		assertEquals(0, Tiempo.getTiempo() );
	}

}
