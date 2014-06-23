package modelo.personajes;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import modelo.geografico.*;


public class PoliciaTest {

	@Test
	public void testPoliciaEsCreadoNovato() {

		Policia policia = new Policia("Nombre");
		assertEquals(policia.obtenerGrado(), Grado.NOVATO);

	}
	
	@Test
	public void testPoliciaEsAsignadoAUnCasoEnUnaCiudad() {

		Policia policia = new Policia("Nombre");
		Ciudad ciudad = EasyMock.createMock(Ciudad.class);
		
		policia.asignarNuevoCasoEn(ciudad);
		
		assertEquals(ciudad, policia.obtenerCiudadActual());
	}
	
//	@Test
//	public void testVisitarLugar() {
//		
//		Ciudad unaCiudadActual = new Ciudad();
//		unaCiudadActual.setNombre("Buenos Aires");
//		
//		Lugar unaBiblioteca = new Lugar(TipoEdificio.BIBLIOTECA);
//		Lugar unPuerto = new Lugar(TipoEdificio.PUERTO);
//		Lugar unBanco = new Lugar(TipoEdificio.BANCO);
//		
//		unaCiudadActual.agregarLugar(unaBiblioteca);
//		unaCiudadActual.agregarLugar(unPuerto);
//		unaCiudadActual.agregarLugar(unBanco);
//		
//		Policia unPolicia = new Policia("Perez");
//		unPolicia.asignarCiudadActual(unaCiudadActual);
//		
//		unPolicia.visitarLugar(new Lugar(TipoEdificio.BIBLIOTECA));
//		assertEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
//		
//		unPolicia.visitarLugar(new Lugar(TipoEdificio.BOLSA));
//		assertNotEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BOLSA);
//		assertEquals(unPolicia.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
//	}

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
	public void testObtenerGradoDevuelveElGradoDelPolicia() {
		
		Policia policia = new Policia("Jorge");
		// Se crea novato
		assertEquals(Grado.NOVATO, policia.obtenerGrado());
	}


	
	@Test
	public void testPromoverGradoDeNovatoADetective(){
		
		Policia unPolicia = new Policia("Matute");
		//Se crea novato
		unPolicia.promoverGrado();
		assertEquals (unPolicia.obtenerGrado(), Grado.DETECTIVE);
		
	}
	
	@Test
	public void testPromoverGradoDetectiveAInvestigador(){
		
		Policia unPolicia = new Policia("Matute");
		//Se crea novato
		unPolicia.promoverGrado();
		unPolicia.promoverGrado();
		assertEquals (unPolicia.obtenerGrado(), Grado.INVESTIGADOR);
		
	}
	
	@Test
	public void testPromoverGradoInvestigadorASargento(){
		
		Policia unPolicia = new Policia("Matute");
		//Se crea novato
		unPolicia.promoverGrado();
		unPolicia.promoverGrado();
		unPolicia.promoverGrado();

		assertEquals (unPolicia.obtenerGrado(), Grado.SARGENTO);
		
	}
	

	@Test
	public void testPoliciaViajaCambiaCiudadActual() {

		Policia policia = new Policia("Nombre");
		Ciudad ciudad1 = EasyMock.createMock(Ciudad.class);
		Ciudad ciudad2 = EasyMock.createMock(Ciudad.class);

		
		policia.asignarNuevoCasoEn(ciudad1);
		assertEquals(policia.obtenerCiudadActual(), ciudad1);
		
		policia.viajarA(ciudad2);
		assertNotEquals(ciudad1, ciudad2);
		assertEquals(policia.obtenerCiudadActual(), ciudad2);
		
		
	}

}
