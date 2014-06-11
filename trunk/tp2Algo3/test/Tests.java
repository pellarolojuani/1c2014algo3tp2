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
        Policia policia=new Policia("Nombre",Grado.NOVATO);
        assertEquals(policia.obtenerGrado(),Grado.NOVATO);
    }

    @Test
    public void testPoliciaViajaCambiaCiudadActual() throws Exception {
        Policia policia=new Policia("Nombre", Grado.NOVATO);
        Ciudad ciudad=new Ciudad();
        policia.asignarNuevoCasoEn(ciudad);
        assertEquals(policia.obtenerCiudadActual(),ciudad);
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



}


