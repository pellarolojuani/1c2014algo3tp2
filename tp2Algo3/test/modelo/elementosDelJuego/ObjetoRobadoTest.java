package modelo.elementosDelJuego;

import static org.junit.Assert.*;
import modelo.geografico.Ciudad;
import modelo.personajes.Policia;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ObjetoRobadoTest {

	Ciudad unaCiudad;

	@Before
	public void setUp(){
		
		unaCiudad = EasyMock.createMock(Ciudad.class);
		
	}
	@Test
	public void testContenidoComoStringDevuelveLaDescripcionDelObjeto() {

		ObjetoRobado unObjeto = new ObjetoRobado(Valor.MUY_VALIOSO, unaCiudad,"Copa del mundo");
		assertEquals("Copa del mundo", unObjeto.contenidoComoString());
		
	}

	@Test
	public void testObtenerValorDeUnObjetoCreadoComun() {
				
		ObjetoRobado unObjetoComun = new ObjetoRobado(Valor.COMUN,unaCiudad ,"");
	
		assertEquals(Valor.COMUN,unObjetoComun.obtenerValor());

	}
	
	@Test
	public void testObtenerValorDeUnObjetoCreadoValioso() {
				
		ObjetoRobado unObjetoValioso = new ObjetoRobado(Valor.VALIOSO, unaCiudad,"");

		assertEquals(Valor.VALIOSO,unObjetoValioso.obtenerValor());

	}
	
	@Test
	public void testObtenerValorDeUnObjetoCreadoMuyValioso() {
	
		ObjetoRobado unObjetoMuyValioso = new ObjetoRobado(Valor.MUY_VALIOSO, unaCiudad,"");

		assertEquals(Valor.MUY_VALIOSO,unObjetoMuyValioso.obtenerValor());

	}
	
	@Test
	public void testObtenerCiudadOrigen() {

		ObjetoRobado unObjeto = new ObjetoRobado(Valor.COMUN, unaCiudad , "");
		
		assertEquals(unaCiudad , unObjeto.obtenerCiudadOrigen());
		
	}
	
	


}
