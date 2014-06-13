package personajes;

import static org.junit.Assert.*;
import geografico.Ciudad;

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
		
		assertEquals(Grado.NOVATO,new Policia().obtenerGrado());
		
	}

	@Test
	public void testVisitarLugar() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testDebeDormir() {
		fail("Not yet implemented");
	}

	@Test
	public void testDormir() {
		fail("Not yet implemented");
	}

}
