package modelo.elementosDelJuego;

import static org.junit.Assert.*;
import modelo.geografico.Ciudad;

import org.easymock.EasyMock;
import org.junit.Test;

public class ObjetoRobadoTest {


	@Test
	public void testContenidoComoStringDevuelveLaDescripcionDelObjeto() {

		Ciudad unaCiudad = EasyMock.createMock(Ciudad.class);
		ObjetoRobado unObjeto = new ObjetoRobado(Valor.MUY_VALIOSO, unaCiudad,"Copa del mundo");
		assertEquals("Copa del mundo", unObjeto.contenidoComoString());
		
	}

	@Test
	public void testObtenerValor() {
		
		Ciudad unaCiudad = EasyMock.createMock(Ciudad.class);
		
		ObjetoRobado unObjetoComun = new ObjetoRobado(Valor.COMUN,unaCiudad ,"");
		ObjetoRobado unObjetoValioso = new ObjetoRobado(Valor.VALIOSO, unaCiudad,"");
		ObjetoRobado unObjetoMuyValioso = new ObjetoRobado(Valor.MUY_VALIOSO, unaCiudad,"");

		assertEquals(Valor.COMUN,unObjetoComun.obtenerValor());
		assertEquals(Valor.VALIOSO,unObjetoValioso.obtenerValor());
		assertEquals(Valor.MUY_VALIOSO,unObjetoMuyValioso.obtenerValor());

	}
	
	@Test
	public void testObtenerCiudadOrigen() {

		Ciudad unaCiudad = EasyMock.createMock(Ciudad.class);

		ObjetoRobado unObjeto = new ObjetoRobado(Valor.COMUN, unaCiudad , "");
		
		assertEquals(unaCiudad , unObjeto.obtenerCiudadOrigen());
	}
	
	


}
