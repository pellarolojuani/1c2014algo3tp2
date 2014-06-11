package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import personajes.*;
import elementosDelJuego.Tiempo;

public class PoliciaTest {
	
	@Test
	public void testPoliciaArrestaALadronCuandoLoLocaliza(){		
	}
	
	@Test
	public void testPoliciaRecuperaObjetoRobadoLuegoDeUnArresto(){
	}
	
	@Test
	public void testPoliciaDuermeOchoHorasPorLasNoches(){	
		Policia unPolicia = new Policia("pepe", Grado.NOVATO);
		Tiempo tiempo = new Tiempo();
		
		if (unPolicia.debeDormir()) unPolicia.dormir(tiempo);
		assertEquals (tiempo.getTiempo(), 8);
		
	}

}
