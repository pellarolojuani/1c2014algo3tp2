package personajes;

import static org.junit.Assert.*;
import elementosDelJuego.Tiempo;
import geografico.*;
import personajes.Policia;

import java.util.*;

import org.junit.Test;

public class PoliciaTest {
	
	public Ciudad crearCiudadLima(){
		
		Ciudad lima = new Ciudad();
		
		lima.setNombre("Lima");
		//lima.setBandera(new ArrayList<Color>);
		//lima.setMoneda(Moneda);
		//lima.setPuntoInteres( PuntoInteres.MONTE_HUASCARAN)
		//lima.setArte(Arte.ARTE_AYMARA);
		
		return lima;
	}

	@Test
	public void testPoliciaPorDefectoEsNovato() {
		Policia unPolicia = new Policia("pepe");
		assertEquals(Grado.NOVATO,unPolicia.obtenerGrado());
		
	}

	@Test
	public void testVisitarLugar() {
		
		Ciudad unaCiudadActual = new Ciudad();
		unaCiudadActual.setNombre("Buenos Aires");
		
		Lugar unaBiblioteca = new Lugar(TipoEdificio.BIBLIOTECA);
		Lugar unPuerto = new Lugar(TipoEdificio.PUERTO);
		Lugar unBanco = new Lugar(TipoEdificio.BANCO);
		
		unaCiudadActual.agregarLugar(unaBiblioteca);
		unaCiudadActual.agregarLugar(unPuerto);
		unaCiudadActual.agregarLugar(unBanco);
		
		Policia unPolicia = new Policia("Perez");
		unPolicia.asignarCiudadActual(unaCiudadActual);
		
		unPolicia.visitarLugar(new Lugar(TipoEdificio.BIBLIOTECA));
		assertEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
		
		unPolicia.visitarLugar(new Lugar(TipoEdificio.BOLSA));
		assertNotEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BOLSA);
		assertEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
	}

//	@Test
//	public void testViajarADestinoCambiaCiudadActual() {
//		
//		Policia poli = new Policia();
//		
//		Ciudad destino = new Ciudad();
//		
//		
//	}

	@Test
	public void testAsignarNuevoCasoEn() {
		fail("Not yet implemented");
	}

	@Test
	public void testObtenerGradoDevuelveElGradoDelPolicia() {
		
		Policia policia = new Policia("Jorge", Grado.SARGENTO);
		assertEquals(Grado.SARGENTO, policia.obtenerGrado());
	}

	@Test
	public void testObtenerCiudadActual() {
		
		Ciudad madrid = new Ciudad();
		madrid.setNombre("Madrid");
		
		Policia unPolicia = new Policia("Juan Carlos");
		unPolicia.asignarCiudadActual(madrid);
		
	}

	@Test
	public void testDebeDormir() {
		fail("Not yet implemented");
	}

	@Test
	public void testDormir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPoliciaDuermeOchoHorasPorLasNoches(){	
		Policia unPolicia = new Policia("pepe", Grado.NOVATO);
		Tiempo tiempo = new Tiempo();
		
		if (unPolicia.debeDormir()) unPolicia.dormir(tiempo);
		assertEquals (tiempo.getTiempo(), 8);
		
	}
	
	@Test
	public void testPoliciaEsPromovidoDeRango(){
		Policia unPolicia = new Policia("Matute", Grado.NOVATO);
		unPolicia.promoverGrado();
		assertEquals (unPolicia.obtenerGrado(), Grado.DETECTIVE);
	}

}
