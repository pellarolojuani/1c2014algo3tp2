package modelo.personajes;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import modelo.geografico.*;


public class PoliciaTest {

	Policia unPoliNovato;
	
	@Before
	public void setUp(){
		
		unPoliNovato = new Policia("Carlos");
		
	}
	
	@Test
	public void testPoliciaEsCreadoNovato() {

		Policia policia = new Policia("Nombre");
		assertEquals(policia.obtenerGrado(), Grado.NOVATO);

	}
	
	@Test
	public void testPoliciaEsAsignadoAUnCasoEnUnaCiudad() {

		Ciudad ciudad = EasyMock.createMock(Ciudad.class);
		
		unPoliNovato.asignarNuevoCasoEn(ciudad);
		
		assertEquals(ciudad, unPoliNovato.obtenerCiudadActual());
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
//		unPoliNovato.asignarCiudadActual(unaCiudadActual);
//		
//		unPoliNovato.visitarLugar(new Lugar(TipoEdificio.BIBLIOTECA));
//		assertEquals(unPoliNovato.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
//		
//		unPoliNovato.visitarLugar(new Lugar(TipoEdificio.BOLSA));
//		assertNotEquals(unPoliNovato.getLugarActual().obtenerTipo(), TipoEdificio.BOLSA);
//		assertEquals(unPoliNovato.getLugarActual().obtenerTipo(), TipoEdificio.BIBLIOTECA);
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
		
		assertEquals(Grado.NOVATO, unPoliNovato.obtenerGrado());
	}


	
	@Test
	public void testPromoverGradoDeNovatoADetective(){
		
		
		unPoliNovato.promoverGrado();
		assertEquals (unPoliNovato.obtenerGrado(), Grado.DETECTIVE);
		
	}
	
	@Test
	public void testPromoverGradoDetectiveAInvestigador(){
		
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		assertEquals (unPoliNovato.obtenerGrado(), Grado.INVESTIGADOR);
		
	}
	
	@Test
	public void testPromoverGradoInvestigadorASargento(){
		

		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();

		assertEquals (unPoliNovato.obtenerGrado(), Grado.SARGENTO);
		
	}
	
	@Test
	public void testPromoverAUnSargentoSigueDejandoAPoliciaComoSargento(){
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();

		assertEquals (unPoliNovato.obtenerGrado(), Grado.SARGENTO);
		
		unPoliNovato.promoverGrado();
		
		assertEquals (unPoliNovato.obtenerGrado(), Grado.SARGENTO);


	}

	@Test
	public void testPoliciaViajaCambiaCiudadActual() {

		
		Ciudad ciudad1 = EasyMock.createMock(Ciudad.class);
		Ciudad ciudad2 = EasyMock.createMock(Ciudad.class);

		
		unPoliNovato.asignarNuevoCasoEn(ciudad1);
		assertEquals(unPoliNovato.obtenerCiudadActual(), ciudad1);
		
		unPoliNovato.viajarA(ciudad2);
		assertNotEquals(ciudad1, ciudad2);
		assertEquals(unPoliNovato.obtenerCiudadActual(), ciudad2);
		
		
	}
	
	@Test
	public void testPoliciaViajaALaMismaCiudadNoCambiaCiudadActual() {

		
		Ciudad ciudad1 = EasyMock.createMock(Ciudad.class);
		
		unPoliNovato.asignarNuevoCasoEn(ciudad1);
		assertEquals(unPoliNovato.obtenerCiudadActual(), ciudad1);
		
		unPoliNovato.viajarA(ciudad1);
		assertEquals(unPoliNovato.obtenerCiudadActual(), ciudad1);
		
		
	}
	
	@Test
	public void testPoliciaNovatoViajaA900KmPorHora(){
		
		
		assertEquals(Grado.NOVATO, unPoliNovato.obtenerGrado() );
		assertEquals(900, unPoliNovato.obtenerVelocidadViaje() );
		
	}
	
	@Test
	public void testPoliciaDetectiveViajaA1100KmPorHora(){
		
		unPoliNovato.promoverGrado();
		
		assertEquals(Grado.DETECTIVE, unPoliNovato.obtenerGrado() );
		assertEquals(1100, unPoliNovato.obtenerVelocidadViaje() );
		
	}

	@Test
	public void testPoliciaInvestigadorViajaA1300KmPorHora(){
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();

		
		assertEquals(Grado.INVESTIGADOR, unPoliNovato.obtenerGrado() );
		assertEquals(1300, unPoliNovato.obtenerVelocidadViaje() );
		
	}
	
	@Test
	public void testPoliciaSargentoViajaA1500KmPorHora(){
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();

		
		assertEquals(Grado.SARGENTO, unPoliNovato.obtenerGrado() );
		assertEquals(1500, unPoliNovato.obtenerVelocidadViaje() );
		
	}
	
	@Test
	public void testPromoverGradoASargentoDejaALaVelocidadEn1500KmPorHora(){
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();

		
		assertEquals(Grado.SARGENTO, unPoliNovato.obtenerGrado() );
		assertEquals(1500, unPoliNovato.obtenerVelocidadViaje() );
		
		unPoliNovato.promoverGrado();
		unPoliNovato.promoverGrado();
		
		assertEquals(1500, unPoliNovato.obtenerVelocidadViaje() );

	}
}
