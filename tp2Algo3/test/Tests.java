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
        Lugar banco=new Lugar(TipoEdificio.BANCO);
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
        sospechosos.add(new Sospechoso(new Descripcion(new Sexo(),new Pelo(),new Senia(),new Auto(),new Hobbie())));
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
        Lugar banco=new Lugar(TipoEdificio.BANCO);
        ciudad.agregarLugar(banco);
        policia.asignarNuevoCasoEn(ciudad);
        policia.visitarLugar(banco);
        assertTrue(banco.fueVisitado());
    }

    @Test
    public void testBuscarSospechosoDevuelveSospechosoCorrecto(){

        CuartelGeneral cuartel = new CuartelGeneral();
        Descripcion descripcion1=new Descripcion(new Sexo(),new Pelo(),new Senia(),new Auto(),new Hobbie());
        Descripcion descripcion2=new Descripcion(new Sexo(),new Pelo(),new Senia(),new Auto(),new Hobbie());
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
			Descripcion descripcion = new Descripcion(Sexo.MASCULINO, Pelo.RUBIO, new Senia(), new Auto(), new Hobbie());
			Sospechoso NickBrunch = new Sospechoso("Nick Brunch",descripcion);
			return NickBrunch;
		}
	
	public Ciudad crearCiudadBsAs(){
		
		Ciudad bsas = new Ciudad();
		Ciudad hongKong = new Ciudad();
		Lugar biblio= new Lugar(TipoEdificio.BIBLIOTECA);
	
		bsas.setNombre("Buenos Aires");
		
		bsas.ciudades.add(hongKong);
		bsas.lugares.add(biblio);
		
		hongKong.ciudades.add(bsas);
		Lugar banco = new Lugar(TipoEdificio.BANCO);
		hongkong.lugares.add(banco);

		

		return bsas;
	}
	
	
    @Test
    public void testPoliciaBuscaALadronYSeLeAcabaEltiempo() {
    //    Caso2(Masomenos): No lo atrapa porque se acabÃ³ el tiempo:
    //    	 - Empiezo en Buenos Aires.
    //    	 - Busco una pista en la biblioteca, â€œFue a un paÃ­s asiÃ¡tico. TenÃ­a una horrible cicatrizâ€�.
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
    	Ciudad bsAs = crearCiudadBsAs();
    	ObjetoRobado bolaDeOro = new ObjetoRobado(Valor.COMUN);
    	Policia poli = new Policia();
    	Sospechoso ladron = this.crearLadronNickBrunch();
    	
    	Caso caso2 = new Caso(bsAs, bolaDeOro, poli, ladron);
    	//Lunes 7 AM
    	assertEquals(0 , caso2.obtenerTiempoTranscurridoEnHs());
    	final int BIBLIO_BSAS=0;
    	Lugar biblioteca = poli.obtenerCiudadActual().obtenerLugaresDisponibles().get(BIBLIO);
    	
    	poli.visitarLugar(biblioteca);
    	
    	assertEquals(1 , caso2.obtenerTiempoTranscurridoEnHs());
    	
    	poli.visitarLugar(biblioteca);
    	//Visita edificio la 2da vez, 2hs. Lunes 10 AM
    	assertEquals(3 , caso2.obtenerTiempoTranscurridoEnHs());
    	
    	Ciudad hongKong = poli.obtenerCiudadActual().obtenerCiudadesDestinoDisponibles().get(0);
    	poli.viajarA( hongKong  );// Viaje dura 12hs
    	// Lunes 10 PM
    	assertEquals(15, caso2.obtenerTiempoTranscurridoEnHs() )
    	
    	//Como son las 10 PM, el policia duerme 8 HS
    	assertTrue( poli.debeDormir() );
    	
    	//Martes 6 PM 
    	assertEquals(23, caso2.obtenerTiempoTranscurridoEnHs() );
    	final int BANCO_HK = 0;
    	Lugar bancoHongkong = poli.obtenerCiudadActual().obtenerLugaresDisponibles().get(BANCO_HK);
    	poli.visitarLugar(bancoHongkong);
    	
    	assertEquals(24, caso2.obtenerTiempoTranscurridoEnHs() );
    	
    	poli.visitarLugar(bancoHongkong);
    	
    	assertEquals(26, caso2.obtenerTiempoTranscurridoEnHs() );
    	
    	poli.visitarLugar(bancoHongkong);
    	// Martes 12 AM
    	assertEquals(29, caso2.obtenerTiempoTranscurridoEnHs() );

    	poli.viajarA(bsAs);
    	// Miercoles 0 AM
    	assertEquals(41, caso2.obtenerTiempoTranscurridoEnHs() );    	
    	// Duerme hasta las 8 am
    	assertEquals(49,caso2.obtenerTiempoTranscurridoEnHs() );    	
    	
    	poli.viajarA(hongKong);
    	// 20 hs
    	assertEquals(53, caso2.obtenerTiempoTranscurridoEnHs() );
    	
    	poli.viajarA(bsAs);
    	// Duerme durante el viaje? o sumamos tiempo de viaje + 8 hs (por ahora sumo) 
    	assertEquals(73, caso2.obtenerTiempoTranscurridoEnHs() );  //Jueves 16hs  	
    	
    	poli.viajarA(hongKong);
    	assertEquals(85, caso2.obtenerTiempoTranscurridoEnHs() );// Viernes 4 am
    	
    	// Duerme
    	assertEquals(93, caso2.obtenerTiempoTranscurridoEnHs() );//Viernes 12am

    	poli.viajarA(bsAs);
    	// Sabado 0 AM
    	assertEquals(105, caso2.obtenerTiempoTranscurridoEnHs() );    	
    	// Duerme hasta las 8 am
    	assertEquals(113,caso2.obtenerTiempoTranscurridoEnHs() );    	
    	
    	poli.viajarA(hongKong);
    	// 20 hs
    	assertEquals(125, caso2.obtenerTiempoTranscurridoEnHs() );
    	
    	poli.viajarA(bsAs);
    	// Domingo 16hs
    	assertEquals(145, caso2.obtenerTiempoTranscurridoEnHs() );

    	poli.visitarLugar(biblioteca);// Para probar que se acabo el tiempo,
    	// creo que conviene usar una excepcion: SeAcaboElTiempoDelCasoExcepcion
    	
    
    }

}


