package modelo.juego;

import Vista.*;
import controlador.ControlXML.CargadorXML;
import controlador.ControlXML.GuardadorXML;
import modelo.descripciones.*;
import modelo.elementosDelJuego.CuartelGeneral;
import modelo.elementosDelJuego.SeAcaboElTiempoDelCasoExcepcion;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.personajes.Policia;
import modelo.personajes.Sospechoso;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBase{

    private static Scanner in;
    private static Policia policia;
    private static Juego juego;
    private int opcionMenuBase, opcionMenuPrincipal;
 
    private Vista vista;
    
    public Vista getVista(){
    	return this.vista;
    }
    public Juego getJuego(){
    	return this.juego;
    }
    
    public Policia getPolicia(){
    	return this.policia;
    }
	
	public MenuBase(){
		this.vista = new VistaPrincipal(this);
		vista.setVisible(true);
	}
	
	public void cambiarAVistaPrincipalConsola(){
		VistaPrincipalConConsola p = new VistaPrincipalConConsola(this);
		vista.setVisible(false);
		vista = p;
		vista.setVisible(true);
	}

	public void cambiarAVistaViajar(){
		VistaViajar p = new VistaViajar(this);
		vista.setVisible(false);
		vista = p;
		vista.setVisible(true);
	}
	
	public void cambiarAVistaPistas(){
		VistaPistas p = new VistaPistas(this);
		vista.setVisible(false);
		vista = p;
		vista.setVisible(true);
	}
	
	public void cambiarAVistaOrdenArresto(){
		VistaOrdenArresto p = new VistaOrdenArresto(this);
		vista.setVisible(false);
		vista = p;
		vista.setVisible(true);
	}
	
    public void nuevoJuego(){
    	
       juego = new Juego();
       policia=juego.obtenerPolicia();
       vista.VistaJuegoNuevo(this);
/*
        in = new Scanner(System.in);
        System.out.println("AlgoThieft");
        System.out.println("1. Comenzar juego");
        System.out.println("2. Salir");
        System.out.println("3. Cargar Juego");
        boolean quit = false;
        
        System.out.println("Por favor elija una de las opciones");
        
        do {
        	opcionMenuBase = in.nextInt();
            switch (opcionMenuBase) {

                case 1:
                    juego = new Juego();
                    policia=juego.obtenerPolicia();
                    System.out.println(juego.obtenerCaso().obtenerDescripcionDelRobo());
                    cambiarAVistaPrincipalConsola();
                    menuPrincipal();



                case 2:
                    quit = true;
                    break;

                case 3:
                    CargadorXML cargadorXML=new CargadorXML();
                    juego=cargadorXML.cargar();
                    policia=juego.obtenerPolicia();
                    System.out.println(juego.obtenerCaso().obtenerDescripcionDelRobo());
                    cambiarAVistaPrincipalConsola();
                    menuPrincipal();


                //default: System.out.println("Eleccion Invalida.");

            }
        } while (!quit);
        System.out.println("goodbye Gadget!!");
       */
    }
    public void menuPrincipal(){
    	
    	vista.vistaCiudad(policia.obtenerCiudadActual().getNombre());
    	
    	/*
        System.out.println("Usted se encuentra en: "+policia.obtenerCiudadActual().getNombre());
        System.out.println(Tiempo.tiempoComoString());
        System.out.println("1. Viajar a otra ciudad");
        System.out.println("2. Visitar un lugar");
        System.out.println("3. Emitir orden de arresto");
        System.out.println("4. Guardar");
        opcionMenuPrincipal = in.nextInt();
        
        switch(opcionMenuPrincipal){
            case 1:
            	cambiarAVistaViajar();
                menuViajar();
                break;
            case 2:
            	cambiarAVistaPistas();
                menuVisitar();
                break;
            case 3:
            	cambiarAVistaOrdenArresto();
                menuOrdenDeArresto();
                break;
            case 4:
                GuardadorXML guardadorXML=new GuardadorXML(juego);
                guardadorXML.guardar();
                System.out.println("El juego ha sido guardado");
                break;
            default: System.out.println("Eleccion Invalida.");
        }*/
    }

    public void menuViajar(){
        //int opcion;
        ArrayList<Ciudad> ciudadesDisponibles=policia.obtenerCiudadActual().obtenerCiudadesDestinoDisponibles();
        try{
        vista.vistaViajar(ciudadesDisponibles);
        }
        catch(SeAcaboElTiempoDelCasoExcepcion e){
    			System.out.println(e.AvisoAlJugador());
    	}
        	
        }
        /*for(Ciudad ciudad: ciudadesDisponibles){
            System.out.println((ciudadesDisponibles.indexOf(ciudad)+1)+". "+ciudad.getNombre());
        }
        opcion = in.nextInt();
        policia.viajarA(ciudadesDisponibles.get(opcion-1));
        menuPrincipal();*/
    
    
    
    public void menuVisitar(){
        int opcion;
        ArrayList<Lugar> lugaresDisponibles=policia.obtenerCiudadActual().obtenerLugaresDisponibles();
        for(Lugar lugar: lugaresDisponibles){
            System.out.println((lugaresDisponibles.indexOf(lugar)+1)+". "+lugar.obtenerTipo());
        }
        opcion=in.nextInt();
        policia.visitarLugar(lugaresDisponibles.get(opcion-1));
        String pista = policia.obtenerUltimaPista();
        System.out.println(pista);
        menuPrincipal();
    }
    
    public void menuOrdenDeArresto(){
        Sexo sexo;
        Senia senia;
        Pelo pelo;
        Vehiculo vehiculo;
        Hobby hobby;
        int n;
        System.out.println("Buscar sospechoso con:");
        System.out.println("Sexo:");

        ArrayList<String> ops =new ArrayList<String>();
        int i=1;
        for(Sexo s: Sexo.values()){
            ops.add(s.toString());
            System.out.println(i+". "+s.toString());
            i++;
        }
        System.out.println(i+". Desconocido");
        n=in.nextInt();
        sexo=n<i?Sexo.valueOf(ops.get(n-1)):null;
        i=1;

        System.out.println("Hobby:");
        ops.clear();
        for(Hobby s: Hobby.values()){
            ops.add(s.toString());
            System.out.println(i+". "+s.toString());
            i++;
        }
        System.out.println(i+". Desconocido");
        n=in.nextInt();
        hobby=n<i?Hobby.valueOf(ops.get(n-1)):null;
        i=1;

        System.out.println("Pelo:");
        ops.clear();
        for(Pelo s: Pelo.values()){
            ops.add(s.toString());
            System.out.println(i+". "+s.toString());
            i++;
        }
        System.out.println(i+". Desconocido");
        n=in.nextInt();pelo=n<i?Pelo.valueOf(ops.get(n-1)):null;
        i=1;

        System.out.println("Senia:");
        ops.clear();
        for(Senia s: Senia.values()){
            ops.add(s.toString());
            System.out.println(i+". "+s.toString());
            i++;
        }
        System.out.println(i+". Desconocido");
        n=in.nextInt();senia=n<i?Senia.valueOf(ops.get(n-1)):null;
        i=1;

        System.out.println("Vehiculo:");
        ops.clear();
        for(Vehiculo s: Vehiculo.values()){
            ops.add(s.toString());
            System.out.println(i+". "+s.toString());
            i++;
        }
        System.out.println(i+". Desconocido");
        n=in.nextInt();vehiculo=n<i?Vehiculo.valueOf(ops.get(n-1)):null;
        i=1;

        ops.clear();
        CuartelGeneral cuartelGeneral=CuartelGeneral.getInstance();
        System.out.println(sexo);
        System.out.println(hobby);
        System.out.println(pelo);
        System.out.println(senia);
        System.out.println(vehiculo);
        ArrayList<Sospechoso> sospechosos=cuartelGeneral.buscarSospechoso(new Descripcion(sexo, hobby, pelo, senia, vehiculo));
        System.out.println("Coincidencias:");
        for(Sospechoso s: sospechosos){
            System.out.println(s.getNombre());
        }
        if(sospechosos.size()==1){
            policia.emitirOrdenDeArrestoPara(sospechosos.get(0));
            System.out.println("Emitida la orden de arresto para "+sospechosos.get(0).getNombre());
        }
        menuPrincipal();
    }
    
    public void setOpcionMenuBase(int valorOpcion){
    	opcionMenuBase = valorOpcion;
    }
    
    public void setOpcionMenuPrincipal(int valorOpcion){
    	opcionMenuPrincipal = valorOpcion;
    }
    
    public String obtenerCiudadActual(){
    	return policia.obtenerCiudadActual().getNombre();
    }


}
