package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class PistaTest {

	@Test
	public void testPistaCreadaConUnString() {
		
		Pista pistaCreada = new Pista("Importante!");
		
		assertEquals("Importante!", pistaCreada.contenidoComoString());
	}

	@Test
	public void testPistaPorDefecto() {
		Pista pistaDefecto = new Pista();
		assertEquals("Lo siento, no he visto a nadie sospechoso.", pistaDefecto.contenidoComoString());
	}


	@Test
	public void testAmpliarPistaConcatenaInformacion() {
		
		Pista pistaOriginal = new Pista("Informacion 1 ");
		Pista pistaAdicional = new Pista("Informacion 2");
		
		pistaOriginal.ampliarPista(pistaAdicional);
		
		assertEquals("Informacion 1 Informacion 2", pistaOriginal.contenidoComoString());
	}

}
