package modelo.elementosDelJuego;

import static org.junit.Assert.*;

import org.junit.Test;

public class PistaHeridaPistolaTest {

    @Test
    public void testContenidoComoString() {

        assertEquals("�Te han disparado!", new PistaHeridaPistola().contenidoComoString());

    }

}
