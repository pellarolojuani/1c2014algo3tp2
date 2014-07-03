package Vista;


import controlador.ControlMenu.Controlador;
import modelo.descripciones.*;
import modelo.elementosDelJuego.Tiempo;
import modelo.geografico.Ciudad;
import modelo.geografico.Lugar;
import modelo.juego.MenuBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Vista extends JFrame implements Observer {

    private MenuBar menuBarra;
    private Menu menu1, menu2, menu3;
    private MenuItem nuevo, guardar, salir, creditos, cargarPartida;
    protected VistaPrincipalImagen imagen;
    protected Controlador control;

    private static final long serialVersionUID = 1L;

    //Clase auxiliar para escuchar el evento de cerrado de un Frame secundario
    public static class CloseFrameListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            e.getWindow().dispose();
        }
    }


    public Vista(String fondo) {
        super(fondo);

    }

    public Vista(MenuBase unMenuBase) {

        control = new Controlador(unMenuBase);

        this.setName("ALGOTHIEF GRUPO X");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);

        menuBarra = new MenuBar();
        menu1 = new Menu("Juego");
        nuevo = new MenuItem("Nuevo");
        nuevo.addActionListener(control.getListenerNuevo());
        guardar = new MenuItem("Guardar");
        guardar.addActionListener(control.getListenerGuardar(this));
        cargarPartida = new MenuItem("Cargar partida guardada");
        cargarPartida.addActionListener(control.getListenerCargarPartida(this));
        salir = new MenuItem("Salir");
        salir.addActionListener(control.getListenerSalir());

        menu1.add(nuevo);
        menu1.add(guardar);
        menu1.add(cargarPartida);
        menu1.addSeparator();
        menu1.add(salir);

        menu2 = new Menu("Opciones");

        menu3 = new Menu("Acerca de");
        creditos = new MenuItem("Creditos");
        creditos.addActionListener(getListenerCreditos());

        menu3.add(creditos);

        menuBarra.add(menu1);
        menuBarra.add(menu2);
        menuBarra.add(menu3);

        this.setMenuBar(this.menuBarra);


    }

    public void VistaJuegoNuevo(MenuBase unMenuBase) {
        this.setImagen("imagenesVista/juegoNuevo.jpg");
        this.setVisible(true);
        String descripcionRobo = unMenuBase.getJuego().obtenerCaso().obtenerDescripcionDelRobo();
        String horario = Tiempo.tiempoComoString();
        String texto = "<html><font style=color:#7FE817><font size= 4>" + descripcionRobo.substring(0, 9) +
                "<br><font style=color:#7FE817><font size= 3>" + descripcionRobo.substring(10, descripcionRobo.length()) +
                "<br><font style=color:#7FE817><font size= 3>" + horario + "</font></html>";
        System.out.println(descripcionRobo.substring(0, 9) + descripcionRobo.substring(10, descripcionRobo.length()) + "\n" + horario);
        JLabel panelTexto = new JLabel();
        panelTexto.setText(texto);

        this.add("North", panelTexto);

        JButton botonComenzar = new JButton();
        botonComenzar.setText("Comenzar investigacion");
        botonComenzar.addActionListener(control.getListenerComenzarInvestigacion());
        add("South", botonComenzar);

        this.setVisible(true);
    }

    public void vistaGameOver() {
        this.setImagen("imagenesVista/game over.jpg");
        this.setVisible(true);

        JButton botonNuevoCaso = new JButton();
        botonNuevoCaso.setText("Nuevo caso");
        botonNuevoCaso.addActionListener(control.getListenerNuevo());

        JButton botonSalir = new JButton();
        botonSalir.setText("Salir");
        botonSalir.addActionListener(control.getListenerSalir());

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonNuevoCaso);
        panelBotones.add(botonSalir);

        add("South", panelBotones);

        this.setVisible(true);

        String texto = "<html><font style=color:#7FE817><font size= 4>" +
                "Tiempo agotado...</font></html>";

        JLabel label = new JLabel();
        Dimension dimension = new Dimension();
        dimension.height = 100;
        dimension.width = getSize().width;
        label.setSize(dimension);
        label.setText(texto);
        add("North", label);
        this.setVisible(true);
    }


    public void setImagen(String imagenPath) {
        imagen = new VistaPrincipalImagen(imagenPath);
        imagen.setBorder(new EmptyBorder(5, 5, 5, 5));
        imagen.setLayout(new BorderLayout(0, 0));
        setContentPane(imagen);
    }

    public void vistaCiudad(MenuBase unMenuBase, Ciudad unaCiudad) {

        String ubicacionCiudad = "imagenesVista/ciudades/" + unaCiudad.getNombre().toLowerCase() + ".jpg";
        VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
        p.setBorder(new EmptyBorder(5, 5, 5, 5));
        p.setLayout(new BorderLayout(0, 0));
        setContentPane(p);

        String horario = "<html><font style=color:#BDEDFF><font size =3>" +
                Tiempo.tiempoComoString() +
                "</font>" + "<br><br><font style=color:#BDEDFF><font size =3>" +
                "Usted se encuentra en: " + unaCiudad.getNombre() + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</font></html>";

        JLabel panelTiempo = new JLabel();
        panelTiempo.setText(horario);
        add("East", panelTiempo);

        JButton botonViajar = new JButton();
        botonViajar.setText("Viajar a otra ciudad");
        botonViajar.addActionListener(control.getListenerViajar());
        JButton botonVisitarLugar = new JButton();
        botonVisitarLugar.setText("Visitar lugar");
        botonVisitarLugar.addActionListener(control.getListenerVisitarLugar(unMenuBase, this, unaCiudad.obtenerLugaresDisponibles()));
        JButton botonEmitirOrdenArresto = new JButton();
        botonEmitirOrdenArresto.setText("Emitir orden de arresto");
        botonEmitirOrdenArresto.addActionListener(control.getListenerOrdenArresto());
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonViajar);
        panelBotones.add(botonVisitarLugar);
        panelBotones.add(botonEmitirOrdenArresto);

        add("South", panelBotones);

        this.setVisible(true);

        if (Tiempo.getTiempoEnHs() >= 150) {
            Vista frameAdvertencia = new Vista("ATENCION");
            frameAdvertencia.setSize(350, 200);  //seteamos las dimensiones del marco
            frameAdvertencia.setLocation(50, 50);
            frameAdvertencia.setLocationRelativeTo(rootPane);
            frameAdvertencia.addWindowListener(new CloseFrameListener());
            JLabel label = new JLabel();
            label.setSize(frameAdvertencia.getSize());

            String texto = "<html><font style=color:#F70D1A><font size= 4>" +
                    "ATENCION!!<br><font size = 3>Se esta agotando el tiempo.<br><br>" +
                    "Emita orden de arresto cuanto antes.</font><html>";
            label.setText(texto);
            frameAdvertencia.add("North", label);

            this.setVisible(true);
            frameAdvertencia.setVisible(true);  //mostramos el marco
        }


    }

    public void vistaLugar(Lugar unLugar, String pista) {

        String ubicacionCiudad = "imagenesVista/lugares/" + unLugar.obtenerTipo().toString().toLowerCase() + ".jpg";
        VistaPrincipalImagen p = new VistaPrincipalImagen(ubicacionCiudad);
        p.setBorder(new EmptyBorder(5, 5, 5, 5));
        p.setLayout(new BorderLayout(0, 0));
        setContentPane(p);

        Vista framePista = new Vista("Pista");
        framePista.setImagen("imagenesVista/pistas.jpg");
        framePista.setSize(350, 200);  //seteamos las dimensiones del marco
        framePista.setLocation(50, 50);
        framePista.setLocationRelativeTo(rootPane);
        framePista.addWindowListener(new CloseFrameListener());
        JLabel label = new JLabel();
        label.setSize(framePista.getSize());

        String texto = "<html><font size = 4><font style=color:#000000>" +
                pista + "</font><html>";
        label.setText(texto);
        framePista.add("North", label);


        JButton botonVolver = new JButton();
        botonVolver.setText("Volver");
        botonVolver.addActionListener(control.getListenerVolver(this, framePista));

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonVolver);

        add("South", panelBotones);

        this.setVisible(true);
        framePista.setVisible(true);  //mostramos el marco

    }


    public void vistaViajar(ArrayList<Ciudad> ciudadesDisponibles) {

        this.setImagen("imagenesVista/mapa.jpg");
        this.setVisible(true);

        JPanel panel = new JPanel();
        int i = 0;

        for (Ciudad unaCiudad : ciudadesDisponibles) {
            String nombre = unaCiudad.getNombre();
            Button unBoton = new Button(nombre);
            unBoton.addActionListener(control.getListenerViajarACiudad(i, ciudadesDisponibles, this));
            panel.add(unBoton);
            i++;
        }

        add("South", panel);
        panel.setVisible(true);
        this.setVisible(true);

        String texto = "<html><font size = 4>Elija ciudad entre las disponibles:</font></html>";

        JLabel panelTexto = new JLabel();
        panelTexto.setText(texto);
        add("North", panelTexto);
        this.setVisible(true);
    }

    public void vistaOrdenDeArresto() {

        this.setImagen("imagenesVista/ordenDeArresto.jpg");
        this.setVisible(true);

        JPanel sexoPanel = new JPanel();
        sexoPanel.setOpaque(false);
        JLabel sexoLabel = new JLabel("<html><font size= 5><font style=color:#FFD801>Sexo:</font></html>");
        sexoPanel.add(sexoLabel);
        JComboBox sexoBox = new JComboBox();
        sexoBox.addItemListener(control.getListenerSeleccionSexo());
        sexoBox.addItem("");
        for (Sexo s : Sexo.values()) {
            sexoBox.addItem(s);
        }
        sexoPanel.add(sexoBox);

        JPanel hobbyPanel = new JPanel();
        hobbyPanel.setOpaque(false);
        JLabel hobbyLabel = new JLabel("<html><font size= 5><font style=color:#FFD801>Hobby:</font></html>");
        hobbyPanel.add(hobbyLabel);
        JComboBox hobbyBox = new JComboBox();
        hobbyBox.addItemListener(control.getListenerSeleccionHobby());
        hobbyBox.addItem("");
        for (Hobby h : Hobby.values()) {
            hobbyBox.addItem(h);
        }
        hobbyPanel.add(hobbyBox);

        JPanel peloPanel = new JPanel();
        peloPanel.setOpaque(false);
        JLabel peloLabel = new JLabel("<html><font size= 5><font style=color:#FFD801>Pelo:</font></html>");
        peloPanel.add(peloLabel);
        JComboBox peloBox = new JComboBox();
        peloBox.addItemListener(control.getListenerSeleccionPelo());
        peloBox.addItem("");
        for (Pelo p : Pelo.values()) {
            peloBox.addItem(p);
        }
        peloPanel.add(peloBox);

        JPanel seniaPanel = new JPanel();
        seniaPanel.setOpaque(false);
        JLabel seniaLabel = new JLabel("<html><font size= 5><font style=color:#FFD801>Seña:</font></html>");
        seniaPanel.add(seniaLabel);
        JComboBox seniaBox = new JComboBox();
        seniaBox.addItemListener(control.getListenerSeleccionSenia());
        seniaBox.addItem("");
        for (Senia s : Senia.values()) {
            seniaBox.addItem(s);
        }
        seniaPanel.add(seniaBox);

        JPanel vehiculoPanel = new JPanel();
        vehiculoPanel.setOpaque(false);
        JLabel vehiculoLabel = new JLabel("<html><font size= 5><font style=color:#FFD801>Vehiculo:</font><html>");
        vehiculoPanel.add(vehiculoLabel);
        JComboBox vehiculoBox = new JComboBox();
        vehiculoBox.addItemListener(control.getListenerSeleccionVehiculo());
        vehiculoBox.addItem("");
        for (Vehiculo v : Vehiculo.values()) {
            vehiculoBox.addItem(v);
        }
        vehiculoPanel.add(vehiculoBox);

        JButton botonEmitir = new JButton("Emitir Orden");
        botonEmitir.addActionListener(control.getListenerEmitirOrden());
        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(control.getListenerVolver(this, null));

        JLabel texto = new JLabel("<html><font size = 5><font style=color:#9F000F>Caracteristicas del Sospechoso:<br></font></html>");
        JPanel textoPanel = new JPanel();
        textoPanel.add(texto);
        add("North", textoPanel);

        setLayout(new GridLayout(0, 1));

        add(sexoPanel);
        sexoPanel.setVisible(true);
        add(hobbyPanel);
        hobbyPanel.setVisible(true);
        add(peloPanel);
        peloPanel.setVisible(true);
        add(seniaPanel);
        seniaPanel.setVisible(true);
        add(vehiculoPanel);
        vehiculoPanel.setVisible(true);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonEmitir);
        panelBotones.add(botonVolver);
        add("South", panelBotones);


        this.setVisible(true);
    }

    private class EscuchaBotonCreditos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frameCreditos = new JFrame("Creditos");
            frameCreditos.setSize(400, 300);  //seteamos las dimensiones del marco
            frameCreditos.setLocationRelativeTo(rootPane);
            frameCreditos.addWindowListener(new CloseFrameListener());
            JLabel label = new JLabel();
            label.setSize(frameCreditos.getSize());
            String texto = "<html><body>Este juego fue creado por alumnos de la Facultad de Ingenieria" +
                    " de la Universidad de Buenos Aires, correspondiendo al Trabajo" +
                    " Practico Grupal de la materia 'Algoritmos y Programacion 3, " +
                    "Catedra Carlos Fontela'.<br><br>" +
                    "Sus creadores son los siguientes:<br><br>" +
                    "-Christian Pedersen<br>" +
                    "-Frank Douglas Anze<br>" +
                    "-Juan Ignacio Pellarolo<br>" +
                    "-Nicolas Truksinas<br>" +
                    "-Sebastian Savulsky<br>" +
                    "</body></html>";
            label.setText(texto);
            frameCreditos.add("North", label);
            frameCreditos.setVisible(true);  //mostramos el marco

        }
    }

    public ActionListener getListenerCreditos() {
        return new EscuchaBotonCreditos();
    }


    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }


}

