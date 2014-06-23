import org.junit.Test;

import modelo.geografico.*;
import modelo.juego.*;
import modelo.personajes.*;
import modelo.elementosDelJuego.*;
import modelo.descripciones.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {



	@Test(expected = NoSePuedeVisitarLugarExcepcion.class)
	public void testPoliciaNoPuedeVisitarLugarDeOtraCiudad() throws Exception {
		Policia policia = new Policia("Nombre");
		Ciudad ciudad = new Ciudad();
		Ciudad ciudad1 = new Ciudad();
		Lugar banco = new Lugar(TipoEdificio.BANCO);
		ciudad1.agregarLugar(banco);
		policia.asignarNuevoCasoEn(ciudad);
		policia.visitarLugar(banco);
	}
	

	@Test
	public void testEnCasoAsignadoAPoliciaNovatoElObjetoEsComun() {

		Policia policia = new Policia("Nombre");
		assertEquals(Grado.NOVATO, policia.obtenerGrado());

		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		ArrayList<ObjetoRobado> objetoRobados = new ArrayList<ObjetoRobado>();
		ArrayList<Sospechoso> sospechosos = new ArrayList<Sospechoso>();
		ciudades.add(new Ciudad());
		objetoRobados.add(new ObjetoRobado(Valor.COMUN));
		objetoRobados.add(new ObjetoRobado(Valor.VALIOSO));
		sospechosos.add(new Sospechoso(new Descripcion("Masculino", "Rubio",
				"Cicatriz", "Descapotable", "Tenis")));
		// Lo de arriba seria mejor ponerlo para que se haga siempre, antes de
		// las pruebas.

		Juego juego = new Juego(objetoRobados, ciudades, sospechosos);
		juego.asignarPolicia(policia);
		juego.crearCaso();
		Caso caso = juego.obtenerCaso();
		ObjetoRobado objRobado = caso.obtenerObjetoRobado();
		assertEquals(objRobado.obtenerValor(), Valor.COMUN);

	}

	@Test
	public void testPoliciaVisitaLugarDejaALugarVisitado(){

		Policia policia = new Policia("Nombre");
		Ciudad ciudad = new Ciudad();
		Lugar banco = new Lugar(TipoEdificio.BANCO);
		ciudad.agregarLugar(banco);
		policia.asignarNuevoCasoEn(ciudad);
		policia.visitarLugar(banco);
		assertNotEquals(0, banco.obtenerNumVisitas());
	}

	@Test
	public void testBuscarSospechosoDevuelveSospechosoCorrecto() {

		CuartelGeneral cuartel = new CuartelGeneral();
		Descripcion descripcion1 = new Descripcion(Sexo.MASCULINO, Pelo.RUBIO,
				"Cicatriz", "Descapotable", "Tenis");
		Descripcion descripcion2 = new Descripcion("Femenino", "Rubio",
				"Anillo", "Deportivo", "Alpinismo");
		Sospechoso sospechoso1 = new Sospechoso(descripcion1);
		Sospechoso sospechoso2 = new Sospechoso(descripcion2);
		cuartel.cargarSospechoso(sospechoso1);
		cuartel.cargarSospechoso(sospechoso2);
		assertEquals(cuartel.buscarSospechoso(descripcion1), sospechoso1);

	}

	@Test
	public void testBuscarSospechosoQueNoEstaDevuelveNull() {

		CuartelGeneral cuartel = new CuartelGeneral();
		Descripcion descripcion = new Descripcion(null, null, null, null, null);
		Sospechoso sospechoso = new Sospechoso(descripcion);
		cuartel.cargarSospechoso(sospechoso);
		Descripcion otraDescripcion = new Descripcion(null, null, null, null,
				null);
		assertNull(cuartel.buscarSospechoso(otraDescripcion));

	}


	public Ciudad crearCiudadBsAs() {

		Ciudad bsas = new Ciudad();
		Ciudad hongKong = new Ciudad();
		Ciudad lima = new Ciudad();
		Ciudad atenas = new Ciudad();

		Lugar biblioBsAs = new Lugar(TipoEdificio.BIBLIOTECA);
		Lugar biblioLima = new Lugar(TipoEdificio.BIBLIOTECA);
		Lugar biblioHongKong = new Lugar(TipoEdificio.BIBLIOTECA);
		Lugar puertoHongKong = new Lugar(TipoEdificio.PUERTO);
		Lugar puertoBsAs = new Lugar(TipoEdificio.PUERTO);

		bsas.setNombre("Buenos Aires");
		hongKong.setNombre("Hong Kong");

		bsas.agregarCiudadVisitable(hongKong);
		bsas.agregarLugar(biblioBsAs);

		hongKong.agregarCiudadVisitable(bsas);
		hongKong.agregarCiudadVisitable(lima);
		hongKong.agregarLugar(biblioHongKong);
		hongKong.agregarLugar(puertoHongKong);

		lima.agregarCiudadVisitable(hongKong);
		lima.agregarCiudadVisitable(atenas);

		atenas.agregarCiudadVisitable(lima);

		bsas.setLatitud(-34.6);
		bsas.setLongitud(-58.38);
		hongKong.setLatitud(22.15);
		hongKong.setLongitud(114.11);

		return bsas;
	}

	@Test
	public void testPoliciaBuscaALadronYSeLeAcabaEltiempo() {
		// Caso2(Masomenos): No lo atrapa porque se acabo el tiempo:
		// - Empiezo en Buenos Aires.
		// - Busco una pista en la biblioteca, Fue a un pais asiatico. Tenia una
		// horrible cicatriz.
		// - Viajo, opciones: Hong Kong, Polonia, Lima.
		// - Elijo Hong Kong
		// - Busco una pista en el banco, Consulta por el tipo de cambio del
		// yen.
		// - Busco una pista en el puerto, Fue a un pais con bandera blanca y
		// roja. Llevaba una raqueta.
		// - Viajo, opciones: Tokio, Brasilia, Moscu, Buenos Aires.
		// - Elijo Buenos Aires.
		// - Busco pista en...
		// - Viajo a Hong Kong
		// - Busco pista en...
		// - etc
		// - Se acaba el tiempo

		Ciudad bsas = this.crearCiudadBsAs();
		ObjetoRobado bolaDeOro = new ObjetoRobado(Valor.COMUN, bsas,
				"Solid Gold Bola");
		Policia poli = new Policia("Juan Carlos");

		// Sospechoso ladron = this.crearLadronNickBrunch();

		Caso caso2 = new Caso(poli, bolaDeOro);

		assertEquals(bsas, poli.obtenerCiudadActual()); // Ciudad actual: Buenos
														// Aires

		// Lunes 7 AM
		assertEquals(0, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Lunes 7 Hs", Tiempo.tiempoComoString());

		final int BIBLIO_BSAS = 0;
		Lugar biblioteca = poli.obtenerCiudadActual()
				.obtenerLugaresDisponibles().get(BIBLIO_BSAS);

		poli.visitarLugar(biblioteca);

		assertEquals(1, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Lunes 8 Hs", Tiempo.tiempoComoString());

		poli.visitarLugar(biblioteca);
		// Visita edificio la 2da vez, 2hs. Lunes 10 AM
		assertEquals(3, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Lunes 10 Hs", Tiempo.tiempoComoString());

		Ciudad hongKong = poli.obtenerCiudadActual()
				.obtenerCiudadesDestinoDisponibles().get(0);
		poli.viajarA(hongKong);// Viaje , dura 20hs, duerme en el viaje
		// Martes 6 AM
		assertEquals(23, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Martes 6 Hs", Tiempo.tiempoComoString());

		final int BANCO_HK = 0;
		Lugar bancoHongkong = poli.obtenerCiudadActual()
				.obtenerLugaresDisponibles().get(BANCO_HK);

		poli.visitarLugar(bancoHongkong);

		assertEquals(24, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Martes 7 Hs", Tiempo.tiempoComoString());

		poli.visitarLugar(bancoHongkong);

		assertEquals(26, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Martes 9 Hs", Tiempo.tiempoComoString());

		poli.visitarLugar(bancoHongkong);
		// Martes 12 AM
		assertEquals(29, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Martes 12 Hs", Tiempo.tiempoComoString());

		poli.viajarA(bsas); // Primera y unica ciudad almacenada : Buenos Aires
		// Miercoles 8 AM, duerme en el viaje
		assertEquals(49, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Miercoles 8 Hs", Tiempo.tiempoComoString());

		poli.viajarA(hongKong);
		// Llega Jueves 4 AM, cansado, duerme 8 hs
		// Por lo tanto ---> Jueves 12 AM
		assertEquals(77, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Jueves 12 Hs", Tiempo.tiempoComoString());

		poli.viajarA(bsas);
		// Viernes 6 AM, durmio en el viaje
		assertEquals(97, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Viernes 8 Hs", Tiempo.tiempoComoString());

		poli.viajarA(hongKong);
		// Llega Sabado 2 AM, y duerme 8 HS --> Sabado 10 AM
		assertEquals(125, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Sabado 12 Hs", Tiempo.tiempoComoString());

		poli.viajarA(bsas);
		// Domingo 6 AM, durmio en el viaje
		assertEquals(145, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Domingo 8 Hs", Tiempo.tiempoComoString());

		poli.visitarLugar(biblioteca);
		assertEquals(148, caso2.obtenerTiempoTranscurridoEnHs());
		assertEquals("Domingo 11 Hs", Tiempo.tiempoComoString());

		// Para probar que se acabo el tiempo,
		// creo que conviene usar una excepcion: SeAcaboElTiempoDelCasoExcepcion

	}

}
