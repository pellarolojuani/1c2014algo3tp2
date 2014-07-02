package modelo.elementosDelJuego;

import static org.junit.Assert.*;
import modelo.geografico.Ciudad;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ObjetoRobadoTest {

	Ciudad unaCiudad;

	@Before
	public void setUp(){
		
		unaCiudad = new Ciudad();
	}
	@Test
	public void testContenidoComoStringDevuelveLaDescripcionDelObjeto() {

		ObjetoRobado unObjeto = new ObjetoRobado(unaCiudad,"Copa del mundo");
		assertEquals("Copa del mundo", unObjeto.obtenerDescripcion());
		
	}

	@Test
	public void testObtenerCiudadOrigen() {
				
		ObjetoRobado unObjetoComun = new ObjetoRobado(unaCiudad,"La copa del mundo");
	
		assertEquals(unaCiudad,unObjetoComun.obtenerCiudadOrigen());

	}
	
}
