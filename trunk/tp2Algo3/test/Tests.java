import org.junit.Before;
import org.junit.Test;
import geografico.*;
import juego.*;
import personajes.*;
import elementosDelJuego.*;
import descripciones.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testPoliciaEsCreadoNovato() throws Exception {

        Policia policia=new Policia();
        assertEquals(policia.obtenerGrado(),Grado.NOVATO);
    }

    @Test
    public void testPoliciaVisitaCiudadCambiaCiudadActual() throws Exception {
        Policia policia=new Policia();
        Ciudad ciudad=new Ciudad();
        policia.viajarA(ciudad);
        assertEquals(policia.obtenerCiudadActual(),ciudad);
    }

    @Test(expected = NoSePuedeVisitarLugarExcepcion.class)
    public void testPoliciaNoPuedeVisitarLugarDeOtraCiudad() throws Exception {
        Policia policia=new Policia();
        Ciudad ciudad=new Ciudad();
        Ciudad ciudad1=new Ciudad();
        Banco banco=new Banco();
        ciudad1.agregarLugar(banco);
        policia.viajarA(ciudad);
        policia.visitarLugar(banco);
    }

    @Test
    public void testPoliciaDuermeDespuesDeLasDiezDeLaNoche() throws Exception {
        //Hay que ver que hacemos con el tiempo
    }

    //Hasta aca estan +- prolijos los tests

    @Test
    public void testEnCasoAsignadoAPoliciaNovatoElObjetoEsComun() {

        Policia policia = new Policia();
        Juego juego=new Juego();
        juego.crearCaso(policia);
        Caso caso= juego.obtenerCaso();
        ObjetoRobado objRobado = caso.obtenerObjetoRobado();
        assertEquals(objRobado.obtenerValor(), Valor.COMUN );

    }

    @Test
    public void testPoliciaViajaACiudadCambiaCiudadActual (){

        Policia policia = new Policia();
        Ciudad ciudad = new Ciudad();
        policia.viajarA(ciudad);
        assertEquals(policia.obtenerCiudadActual(), ciudad);

    }

    @Test
    public void testPoliciaVisitaLugarDejaALugarVisitado() throws NoSePuedeVisitarLugarExcepcion {

        Policia policia = new Policia();
        Ciudad ciudad = new Ciudad();
        Banco banco=new Banco();
        ciudad.agregarLugar(banco);
        policia.viajarA(ciudad);
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

}


