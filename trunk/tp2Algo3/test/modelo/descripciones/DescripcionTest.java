package modelo.descripciones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DescripcionTest {

	Descripcion unaDesc;
	
	@Before
	public void setUp(){
		
		unaDesc = new Descripcion(Sexo.MASCULINO, Hobby.ALPINISMO, Pelo.NEGRO, Senia.ANILLO, Vehiculo.MOTO);
	}
   
    @Test
    public void testGetSenia() {
    	
        assertEquals(Senia.ANILLO, unaDesc.getSenia());
    }

    @Test
    public void testGetHobby() {

    	assertEquals(Hobby.ALPINISMO, unaDesc.getHobby());
    }

    @Test
    public void testGetPelo() {

    	assertEquals(Pelo.NEGRO,unaDesc.getPelo());
    	
    }

    @Test
    public void testGetVehiculo() {

    	assertEquals(Vehiculo.MOTO, unaDesc.getVehiculo());
    }

    @Test
    public void testGetSexo() {
        assertEquals(Sexo.MASCULINO, unaDesc.getSexo());
    }

    @Test
    public void testPuedeSerIgualALaMismaDescripcionDevuelveTrue() {

    	assertTrue(unaDesc.puedeSerIgualA(unaDesc));
    }
    
    @Test
    public void testPuedeSerIgualAOtraDescripcionDevuelveFalse() {
    	
    	Descripcion otraDesc = new Descripcion(Sexo.FEMENINO, Hobby.TENIS, Pelo.ROJO, Senia.CICATRIZ, Vehiculo.MOTO);
    	assertFalse(unaDesc.puedeSerIgualA( otraDesc));
    }

}
