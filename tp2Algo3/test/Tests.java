import org.junit.Test;
import static org.junit.Assert.*;

import modelo.geografico.*;
import modelo.juego.*;
import modelo.personajes.*;
import modelo.elementosDelJuego.*;
import modelo.descripciones.*;

import java.util.ArrayList;


public class Tests {

	@Test(expected = NoSePuedeVisitarLugarExcepcion.class)
	public void testPoliciaNoPuedeVisitarLugarDeOtraCiudad() throws Exception {
		Policia policia = new Policia("Mario");
		Ciudad ciudad = new Ciudad();
		Ciudad ciudad1 = new Ciudad();
		Lugar banco = new Lugar(TipoEdificio.BANCO);
		ciudad1.agregarLugar(banco);
		policia.asignarNuevoCasoEn(ciudad);
		policia.visitarLugar(banco);
	}
	

	@Test
	public void testEnCasoAsignadoAPoliciaNovatoElObjetoEsComun() {

		Juego juego = new Juego();
		
		assertEquals(juego.obtenerCaso().obtenerValorObjeto(), Valor.COMUN);

	}

	@Test
	public void testPoliciaVisitaLugarDejaALugarVisitado(){

		Juego juego = new Juego();
		Policia policia = juego.obtenerPolicia();
		Ciudad ciudad = new Ciudad();
		Lugar banco = new Lugar(TipoEdificio.BANCO);
		ciudad.agregarLugar(banco);
		policia.asignarNuevoCasoEn(ciudad);
		policia.visitarLugar(banco);
		assertNotSame(0, banco.obtenerNumVisitas());
	}

	@Test
	public void testBuscarSospechosoDevuelveSospechosoCorrecto() {

		CuartelGeneral cuartel = new CuartelGeneral();
		Descripcion descripcion1 = new Descripcion(Sexo.MASCULINO, Hobby.ALPINISMO, Pelo.CASTANIO, Senia.TATUAJE, Vehiculo.DEPORTIVO);
		Descripcion descripcion2 = new Descripcion(Sexo.FEMENINO, Hobby.ALPINISMO, Pelo.RUBIO, Senia.JOYAS, Vehiculo.LIMUSINA);
		Sospechoso sospechoso1 = new Sospechoso(descripcion1);
		Sospechoso sospechoso2 = new Sospechoso(descripcion2);
		ArrayList<Sospechoso> sospechososList = new ArrayList<Sospechoso>();
		sospechososList.add(sospechoso1);
		sospechososList.add(sospechoso2);
		cuartel.cargarSospechosos(sospechososList);
		assertEquals(cuartel.buscarSospechoso(descripcion1),sospechoso1);

	}

	@Test
	public void testBuscarSospechososAgregadosMeLosEncuentra() {

		CuartelGeneral cuartel = new CuartelGeneral();
		Descripcion descripcion1 = new Descripcion(Sexo.MASCULINO, Hobby.ALPINISMO, Pelo.CASTANIO, Senia.ANILLO, Vehiculo.MOTO);
		Descripcion descripcion2 = new Descripcion(Sexo.FEMENINO, Hobby.ALPINISMO, Pelo.RUBIO, Senia.JOYAS, Vehiculo.LIMUSINA);
		Sospechoso sospechoso1 = new Sospechoso(descripcion1);
		Sospechoso sospechoso2 = new Sospechoso(descripcion2);
		ArrayList<Sospechoso> sospechososList = new ArrayList<Sospechoso>();
		sospechososList.add(sospechoso1);
		sospechososList.add(sospechoso2);
		cuartel.cargarSospechosos(sospechososList);
		assert (cuartel.buscarSospechoso(descripcion1)) != null;
		assert (cuartel.buscarSospechoso(descripcion1)) != null;
	}

	@Test(expected = SeAcaboElTiempoDelCasoExcepcion.class)
	public void testPoliciaBuscaALadronYSeLeAcabaEltiempo() {
		

		Juego juego = new Juego();
	
		// Ciudad actual: Buenos Aires
		// Lunes 7 AM

		assertEquals(0, Tiempo.getTiempoEnHs());
		assertEquals("Lunes 7 Hs", Tiempo.tiempoComoString());

		final int BIBLIO_BSAS = 0;
		Lugar biblioteca = juego.obtenerPolicia().obtenerCiudadActual().obtenerLugaresDisponibles().get(BIBLIO_BSAS);

		Policia poli = juego.obtenerPolicia();
		poli.visitarLugar(biblioteca);

		assertEquals(1, Tiempo.getTiempoEnHs());
		assertEquals("Lunes 8 Hs", Tiempo.tiempoComoString());

		for (int i=0; i<51; ++i){
		poli.visitarLugar(biblioteca);
		}
		assertEquals(153, Tiempo.getTiempoEnHs());
		assertEquals("Domingo 16 Hs", Tiempo.tiempoComoString());
		poli.visitarLugar(biblioteca);

		

	}

}
