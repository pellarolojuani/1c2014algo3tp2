package modelo.juego;

import Vista.Vista;
import Vista.VistaPrincipal;
import modelo.elementosDelJuego.SeAcaboElTiempoDelCasoExcepcion;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.personajes.Policia;

import java.util.ArrayList;

public class MenuBase {

    private static Policia policia;
    private static Juego juego;

    private Vista vista;


    public static Juego getJuego() {
        return juego;
    }

    public static Policia getPolicia() {
        return policia;
    }

    public static void setJuego(Juego unJuego) {
        juego = unJuego;
    }

    public static void setPolicia(Policia unPolicia) {
        policia = unPolicia;
    }

    public MenuBase() {
        this.vista = new VistaPrincipal(this);
        vista.setVisible(true);
    }

    public void nuevoJuego() {

        juego = new Juego();
        policia = juego.obtenerPolicia();
        vista.VistaJuegoNuevo(this);
    }

    public void menuPrincipal() {

        vista.vistaCiudad(this, policia.obtenerCiudadActual());
    }

    public void menuViajar() {
        ArrayList<Ciudad> ciudadesDisponibles = policia.obtenerCiudadActual().obtenerCiudadesDestinoDisponibles();
        try {
            vista.vistaViajar(ciudadesDisponibles);
        } catch (SeAcaboElTiempoDelCasoExcepcion e) {
            System.out.println(e.AvisoAlJugador());
        }

    }

    public void menuVisitar() {
        ArrayList<Lugar> lugaresDisponibles = policia.obtenerCiudadActual().obtenerLugaresDisponibles();
        for (Lugar lugar : lugaresDisponibles) {
            System.out.println((lugaresDisponibles.indexOf(lugar) + 1) + ". " + lugar.obtenerTipo());
        }
    }

    public void menuOrdenDeArresto() {
        vista.vistaOrdenDeArresto();
    }


}
