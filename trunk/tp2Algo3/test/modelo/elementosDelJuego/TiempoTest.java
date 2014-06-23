package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class TiempoTest {

	@Test
	public void testIniciarPoneElTiempoEnCeroHs() {
		Tiempo.iniciar();
		assertEquals(0, Tiempo.getTiempo() );
	}
	
	@Test
	public void testIniciarIniciaLunesAlas7Hs() {
		Tiempo.iniciar();
		assertEquals("Lunes 7 Hs", Tiempo.tiempoComoString() );
	}
	
	@Test
	public void testAumentarHorasAumentarElContadorDeHs() {
		Tiempo.iniciar();
		Tiempo.aumentarHoras(8);
		assertEquals(8,Tiempo.getTiempo());
	}
	
	@Test
	public void testAumentarCeroHorasDejaAlTiempoEnLaMismaHora() {
		
		Tiempo.iniciar();
		Tiempo.aumentarHoras(8);
		assertEquals(8,Tiempo.getTiempo());
		
		Tiempo.aumentarHoras(0);
		assertEquals(8,Tiempo.getTiempo());

	}

	
	@Test( expected = SeAcaboElTiempoDelCasoExcepcion.class)
	public void testDespuesDelDomingo17HsSeArrojaExcepcionFinDelTiempoDelCaso() {
		
		Tiempo.iniciar();
		Tiempo.aumentarHoras(154);
		assertEquals("Domingo 17 Hs", Tiempo.tiempoComoString());
		
		Tiempo.aumentarHoras(1);
	}

}
