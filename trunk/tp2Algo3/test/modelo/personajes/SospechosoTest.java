package modelo.personajes;

import static org.junit.Assert.*;
import modelo.descripciones.Descripcion;
import modelo.descripciones.Hobby;
import modelo.descripciones.Pelo;
import modelo.descripciones.Senia;
import modelo.descripciones.Sexo;
import modelo.descripciones.Vehiculo;

import org.junit.Before;
import org.junit.Test;

public class SospechosoTest {
	
	Sospechoso ladron;
	Descripcion desc;
	
	@Before
	public void setUp(){
		
		desc = new Descripcion(Sexo.MASCULINO, Hobby.ALPINISMO, Pelo.NEGRO, Senia.ANILLO, Vehiculo.MOTO);
		ladron = new Sospechoso("Nik", desc);
	}

    @Test
    public void testSospechosoObtenerDescripcion() {

    	assertEquals(desc, ladron.obtenerDescripcion());
    }


    @Test
    public void testGetNombre() {
    	
        assertEquals("Nik", ladron.getNombre());
    }

    @Test
    public void testSeniaComoString() {

    	assertEquals("ANILLO",ladron.seniaComoString());
    }

    @Test
    public void testHobbyComoString() {

    	assertEquals("ALPINISMO",ladron.hobbyComoString());
    }

    @Test
    public void testPeloComoString() {
        
    	assertEquals("NEGRO",ladron.peloComoString());
    }

    @Test
    public void testVehiculoComoString() {

    	assertEquals("MOTO",ladron.vehiculoComoString());
    }

}
