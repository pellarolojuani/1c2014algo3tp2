import org.junit.Test;

import modelo.geografico.*;
import modelo.juego.*;
import modelo.personajes.*;
import modelo.elementosDelJuego.*;
import modelo.descripciones.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testPoliciaEsCreadoNovato() throws Exception {
        Policia policia=new Policia("Nombre",Grado.NOVATO);
        assertEquals(policia.obtenerGrado(),Grado.NOVATO);
    }

    @Test
    public void testPoliciaEsAsignadoUnCasoEnUnaCiudad() throws Exception {
        Policia policia=new Policia("Nombre", Grado.NOVATO);
        Ciudad ciudad = new Ciudad();
        policia.asignarNuevoCasoEn(ciudad);
        assertEquals(policia.obtenerCiudadActual(),ciudad);
    }
    
    @Test
    public void testPoliciaViajaCambiaCiudadActual() throws Exception {
        Policia policia=new Policia("Nombre", Grado.NOVATO);
        Ciudad ciudad1=new Ciudad();
        ciudad1.setNombre("Buenos Aires");
        Ciudad ciudad2=new Ciudad();
        ciudad2.setNombre("Londres");
        
        policia.viajarA(ciudad1);
        assertEquals(policia.obtenerCiudadActual(),ciudad1);
        policia.viajarA(ciudad2);
        assertEquals(policia.obtenerCiudadActual(),ciudad2);
    }

    @Test(expected = NoSePuedeVisitarLugarExcepcion.class)
    public void testPoliciaNoPuedeVisitarLugarDeOtraCiudad() throws Exception {
        Policia policia=new Policia("Nombre", Grado.NOVATO);
        Ciudad ciudad=new Ciudad();
        Ciudad ciudad1=new Ciudad();
        Lugar banco=new Lugar("Banco");
        ciudad1.agregarLugar(banco);
        policia.asignarNuevoCasoEn(ciudad);
        policia.visitarLugar(banco);
    }

    @Test
    public void testPoliciaDuermeDespuesDeLasDiezDeLaNoche() throws Exception {
        //Hay que ver que hacemos con el tiempo
    }

    @Test
    public void testEnCasoAsignadoAPoliciaNovatoElObjetoEsComun() {

        Policia policia = new Policia("Nombre", Grado.NOVATO);

        ArrayList<Ciudad> ciudades=new ArrayList<Ciudad>();
        ArrayList<ObjetoRobado> objetoRobados=new ArrayList<ObjetoRobado>();
        ArrayList<Sospechoso> sospechosos=new ArrayList<Sospechoso>();
        ciudades.add(new Ciudad());
        objetoRobados.add(new ObjetoRobado(Valor.COMUN));
        objetoRobados.add(new ObjetoRobado(Valor.VALIOSO));
        sospechosos.add(new Sospechoso(new Descripcion("Masculino","Rubio","Cicatriz","Descapotable","Tenis")));
        //Lo de arriba seria mejor ponerlo para que se haga siempre, antes de las pruebas.

        Juego juego=new Juego(objetoRobados,ciudades,sospechosos);
        juego.asignarPolicia(policia);
        juego.crearCaso();
        Caso caso= juego.obtenerCaso();
        ObjetoRobado objRobado = caso.obtenerObjetoRobado();
        assertEquals(objRobado.obtenerValor(), Valor.COMUN );

    }

    @Test
    public void testPoliciaVisitaLugarDejaALugarVisitado() throws NoSePuedeVisitarLugarExcepcion {

        Policia policia = new Policia("Nombre", Grado.NOVATO);
        Ciudad ciudad = new Ciudad();
        Lugar banco=new Lugar("Banco");
        ciudad.agregarLugar(banco);
        policia.asignarNuevoCasoEn(ciudad);
        policia.visitarLugar(banco);
        assertTrue(banco.fueVisitado());
    }

    @Test
    public void testBuscarSospechosoDevuelveSospechosoCorrecto(){

        CuartelGeneral cuartel = new CuartelGeneral();
        Descripcion descripcion1=new Descripcion("Masculino","Rubio","Cicatriz","Descapotable","Tenis");
        Descripcion descripcion2=new Descripcion("Femenino","Rubio","Anillo","Deportivo","Alpinismo");
        Sospechoso sospechoso1=new Sospechoso(descripcion1);
        Sospechoso sospechoso2=new Sospechoso(descripcion2);
        cuartel.cargarSospechoso(sospechoso1);
        cuartel.cargarSospechoso(sospechoso2);
        assertEquals(cuartel.buscarSospechoso(descripcion1), sospechoso1);

    }

    @Test
    public void testBuscarSospechosoQueNoEstaDevuelveNull(){

        CuartelGeneral cuartel = new CuartelGeneral();
        Descripcion descripcion=new Descripcion(null,null,null,null,null);
        Sospechoso sospechoso=new Sospechoso(descripcion);
        cuartel.cargarSospechoso(sospechoso);
        Descripcion otraDescripcion=new Descripcion(null,null,null,null,null);
        assertNull(cuartel.buscarSospechoso(otraDescripcion));

    }

    
    public Sospechoso crearLadronNickBrunch(){
//		Name:     Nick Brunch
//		Sex:      Male
//		Hobby:    Mountain Climbing
//		Hair:     Black
//		Feature:  Ring
//		Auto:     Motorcyle
			Descripcion descripcion = new Descripcion("Femenino","Rubio","Anillo","Deportivo","Alpinismo");
			Sospechoso NickBrunch = new Sospechoso("Nick Brunch",descripcion);
			return NickBrunch;
		}
	
	public Ciudad crearCiudadBsAs(){
		
		Ciudad bsas = new Ciudad();
		Ciudad hongKong = new Ciudad();
		Ciudad lima = new Ciudad();
		Ciudad atenas = new Ciudad();
		
		Lugar biblioBsAs = new Lugar("Biblioteca");
		Lugar biblioLima = new Lugar("Biblioteca");
		Lugar biblioHongKong = new Lugar("Biblioteca");
		Lugar puertoHongKong = new Lugar("Puerto");
		Lugar puertoBsAs = new Lugar("Puerto");


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
    //    Caso2(Masomenos): No lo atrapa porque se acabo el tiempo:
    //    	 - Empiezo en Buenos Aires.
    //    	 - Busco una pista en la biblioteca, âFue a un paÃ­s asiÃ¡tico. TenÃ­a una horrible cicatrizâ€�.
    //    	 - Viajo, opciones: Hong Kong, Polonia, Lima.
    //    	 - Elijo Hong Kong
    //    	 - Busco una pista en el banco, â€œConsultÃ³ por el tipo de cambio del yen.â€�.
    //    	 - Busco una pista en el puerto, â€œFue a un paÃ­s con bandera blanca y roja. Llevaba una raquetaâ€�.
    //    	 - Viajo, opciones: Tokio, Brasilia, Moscu, Buenos Aires.
    //    	 - Elijo Buenos Aires.
    //    	 - Busco pista en...
    //    	 - Viajo a Hong Kong
    //    	 - Busco pista en...
    //    	 - etc
    //    	 - Se acaba el tiempo
    	
    	Ciudad bsas = this.crearCiudadBsAs();
    	ObjetoRobado bolaDeOro = new ObjetoRobado(Valor.COMUN, bsas);
    	Policia poli = new Policia("Juan Carlos");
    	
    	//Sospechoso ladron = this.crearLadronNickBrunch();
    	
    	Caso caso2 = new Caso( poli, bolaDeOro);
    	
    	assertEquals(bsas,poli.obtenerCiudadActual() ); //Ciudad actual: Buenos Aires
    	
    	//Lunes 7 AM
    	assertEquals(0 , caso2.obtenerTiempoTranscurridoEnHs());
    	assertEquals("Lunes 7 Hs", Tiempo.tiempoComoSring());
    	
    	final int BIBLIO_BSAS=0;
    	Lugar biblioteca = poli.obtenerCiudadActual().obtenerLugaresDisponibles().get(BIBLIO_BSAS);
    	
    	poli.visitarLugar(biblioteca);
    	
    	assertEquals(1 , caso2.obtenerTiempoTranscurridoEnHs());
    	assertEquals("Lunes 8 Hs", Tiempo.tiempoComoSring());

    	
    	poli.visitarLugar(biblioteca);
    	//Visita edificio la 2da vez, 2hs. Lunes 10 AM
    	assertEquals(3 , caso2.obtenerTiempoTranscurridoEnHs());
    	assertEquals("Lunes 10 Hs", Tiempo.tiempoComoSring());

    	
    	Ciudad hongKong = poli.obtenerCiudadActual().obtenerCiudadesDestinoDisponibles().get(0);
    	poli.viajarA( hongKong  );// Viaje , dura 20hs, duerme en el viaje
    	// Martes 6 AM 
    	assertEquals(23, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Martes 6 Hs", Tiempo.tiempoComoSring());

    	    	
    	final int BANCO_HK = 0;
    	Lugar bancoHongkong = poli.obtenerCiudadActual().obtenerLugaresDisponibles().get(BANCO_HK);
    	
    	poli.visitarLugar(bancoHongkong);
    	
    	assertEquals(24, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Martes 7 Hs", Tiempo.tiempoComoSring());

    	
    	poli.visitarLugar(bancoHongkong);
    	
    	assertEquals(26, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Martes 9 Hs", Tiempo.tiempoComoSring());

    	poli.visitarLugar(bancoHongkong);
    	// Martes 12 AM
    	assertEquals(29, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Martes 12 Hs", Tiempo.tiempoComoSring());


    	poli.viajarA(bsas); //Primera y unica ciudad almacenada : Buenos Aires
    	// Miercoles 8 AM, duerme en el viaje
    	assertEquals(49,caso2.obtenerTiempoTranscurridoEnHs() );    	
    	assertEquals("Miercoles 8 Hs", Tiempo.tiempoComoSring());

    	poli.viajarA(hongKong);
    	// Llega Jueves 4 AM, cansado, duerme 8 hs
    	// Por lo tanto ---> Jueves 12 AM
    	assertEquals(77, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Jueves 12 Hs", Tiempo.tiempoComoSring());

    	poli.viajarA(bsas);
    	// Viernes 6 AM, durmio en el viaje
    	assertEquals(97, caso2.obtenerTiempoTranscurridoEnHs() ); 	
    	assertEquals("Viernes 8 Hs", Tiempo.tiempoComoSring());

    	poli.viajarA(hongKong);
    	// Llega Sabado 2 AM, y duerme 8 HS --> Sabado 10 AM
    	assertEquals(125, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Sabado 12 Hs", Tiempo.tiempoComoSring());

    	
    	poli.viajarA(bsas);
    	// Domingo 6 AM, durmio en el viaje
    	assertEquals(145, caso2.obtenerTiempoTranscurridoEnHs() );
    	assertEquals("Domingo 8 Hs", Tiempo.tiempoComoSring());

   

    	poli.visitarLugar(biblioteca);
    	assertEquals(148, caso2.obtenerTiempoTranscurridoEnHs() );  
    	assertEquals("Domingo 11 Hs", Tiempo.tiempoComoSring());


    	// Para probar que se acabo el tiempo,
    	// creo que conviene usar una excepcion: SeAcaboElTiempoDelCasoExcepcion
    	
    
    }

}


