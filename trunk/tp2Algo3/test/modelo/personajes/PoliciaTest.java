package modelo.personajes;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.*;
import modelo.juego.Juego;


public class PoliciaTest {

	Policia unPoliNovato;
	
	@Before
	public void setUp(){
		
		unPoliNovato = new Policia();
	}
	
	@Test
	public void testPoliciaEsCreadoNovato() {

		Policia policia = new Policia();
		assertEquals(policia.obtenerGrado(), Grado.NOVATO);

	}
	

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