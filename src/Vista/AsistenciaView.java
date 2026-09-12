package Vista;

import Controller.AsistenciaController;
import Model.Asistencia;
import Model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AsistenciaView extends JFrame {

    private final AsistenciaController controller;
    private final int idUsuario;
    private final Usuario usuario;

    private JLabel lblFecha;
    private JLabel lblHoraEntrada;
    private JLabel lblHoraSalida;
    private JLabel lblEstado;

    private JButton btnEntrada;
    private JButton btnSalida;
    private JButton btnActualizar;
    private JButton btnVolver;

    private final Color VERDE_QUIMEX = new Color(59, 126, 70);
    private final Color AZUL_OSCURO = new Color(30, 50, 90);
    private final Color FONDO = new Color(245, 247, 250);
    private final Color GRIS_TEXTO = new Color(90, 90, 90);

    public AsistenciaView(Usuario usuario) {

        this.usuario = usuario;
        this.idUsuario = usuario.getIdUsuario();
        this.controller = new AsistenciaController();

        configurarVentana();
        crearComponentes();
        cargarAsistencia();
    }

    private void configurarVentana() {

        setTitle("Quimex - Control de Asistencia");
        setSize(900, 600);

        // Quita la barra superior de Windows
        setUndecorated(true);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void crearComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(FONDO);

        // ==============================
        // HEADER
        // ==============================

        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(VERDE_QUIMEX);
        panelHeader.setPreferredSize(new Dimension(900, 85));
        panelHeader.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 10, 20)
        );

        JPanel panelMarca = new JPanel();
        panelMarca.setOpaque(false);
        panelMarca.setLayout(
                new BoxLayout(panelMarca, BoxLayout.Y_AXIS)
        );

        JLabel lblLogo = new JLabel("Quimex");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        JLabel lblSubtitulo = new JLabel(
                "Sistema de Asistencia"
        );
        lblSubtitulo.setForeground(
                new Color(220, 235, 225)
        );
        lblSubtitulo.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        panelMarca.add(lblLogo);
        panelMarca.add(lblSubtitulo);

        JPanel panelUsuario = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        15,
                        10
                )
        );

        panelUsuario.setOpaque(false);

        JPanel panelDatosUsuario = new JPanel();
        panelDatosUsuario.setOpaque(false);
        panelDatosUsuario.setLayout(
                new BoxLayout(
                        panelDatosUsuario,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel lblCorreo = new JLabel(
                usuario.getCorreo()
        );

        lblCorreo.setForeground(Color.WHITE);
        lblCorreo.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        String rol;

        if (usuario.getId_rol() == 1) {
            rol = "Administrador";
        } else {
            rol = "Empleado";
        }

        JLabel lblRol = new JLabel(rol);
        lblRol.setForeground(
                new Color(220, 235, 225)
        );

        lblRol.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        btnVolver = new JButton("Volver al menú");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(AZUL_OSCURO);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        panelDatosUsuario.add(lblCorreo);
        panelDatosUsuario.add(lblRol);

        panelUsuario.add(panelDatosUsuario);
        panelUsuario.add(btnVolver);

        panelHeader.add(
                panelMarca,
                BorderLayout.WEST
        );

        panelHeader.add(
                panelUsuario,
                BorderLayout.EAST
        );

        // ==============================
        // CONTENIDO
        // ==============================

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(FONDO);

        panelContenido.setLayout(
                new BoxLayout(
                        panelContenido,
                        BoxLayout.Y_AXIS
                )
        );

        panelContenido.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40
                )
        );

        JLabel titulo = new JLabel(
                "CONTROL DE ASISTENCIA"
        );

        titulo.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        titulo.setForeground(AZUL_OSCURO);
        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel descripcion = new JLabel(
                "Registra tu entrada y salida"
        );

        descripcion.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        descripcion.setForeground(GRIS_TEXTO);
        descripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelContenido.add(titulo);
        panelContenido.add(
                Box.createVerticalStrut(5)
        );
        panelContenido.add(descripcion);

        panelContenido.add(
                Box.createVerticalStrut(25)
        );

        // ==============================
        // TARJETAS
        // ==============================

        JPanel panelTarjetas = new JPanel(
                new GridLayout(1, 4, 15, 0)
        );

        panelTarjetas.setBackground(FONDO);
        panelTarjetas.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        140
                )
        );

        lblFecha = new JLabel();
        lblHoraEntrada = new JLabel();
        lblHoraSalida = new JLabel();
        lblEstado = new JLabel();

        JPanel tarjetaFecha = crearTarjeta(
                "Fecha",
                lblFecha
        );

        JPanel tarjetaEntrada = crearTarjeta(
                "Hora entrada",
                lblHoraEntrada
        );

        JPanel tarjetaSalida = crearTarjeta(
                "Hora salida",
                lblHoraSalida
        );

        JPanel tarjetaEstado = crearTarjeta(
                "Estado",
                lblEstado
        );

        panelTarjetas.add(tarjetaFecha);
        panelTarjetas.add(tarjetaEntrada);
        panelTarjetas.add(tarjetaSalida);
        panelTarjetas.add(tarjetaEstado);

        panelContenido.add(panelTarjetas);

        panelContenido.add(
                Box.createVerticalStrut(30)
        );

        // ==============================
        // BOTONES
        // ==============================

        btnEntrada = crearBotonVerde(
                "MARCAR ENTRADA"
        );

        btnSalida = crearBotonVerde(
                "MARCAR SALIDA"
        );

        btnActualizar = new JButton(
                "ACTUALIZAR"
        );

        btnActualizar.setBackground(Color.WHITE);
        btnActualizar.setForeground(VERDE_QUIMEX);
        btnActualizar.setFocusPainted(false);

        btnActualizar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        btnActualizar.setMaximumSize(
                new Dimension(
                        400,
                        40
                )
        );

        btnActualizar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelContenido.add(btnEntrada);
        panelContenido.add(
                Box.createVerticalStrut(10)
        );

        panelContenido.add(btnSalida);
        panelContenido.add(
                Box.createVerticalStrut(10)
        );

        panelContenido.add(btnActualizar);

        panelContenido.add(
                Box.createVerticalGlue()
        );

        JLabel lblPie = new JLabel(
                "Sistema de Asistencia Quimex"
        );

        lblPie.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        lblPie.setForeground(
                new Color(130, 130, 130)
        );

        lblPie.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelContenido.add(lblPie);

        panelPrincipal.add(
                panelHeader,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                panelContenido,
                BorderLayout.CENTER
        );

        add(panelPrincipal);

        // ==============================
        // ACCIONES
        // ==============================

        btnEntrada.addActionListener(
                e -> registrarEntrada()
        );

        btnSalida.addActionListener(
                e -> registrarSalida()
        );

        btnActualizar.addActionListener(
                e -> cargarAsistencia()
        );

        btnVolver.addActionListener(e -> {

            new MenuView(usuario).setVisible(true);
            dispose();

        });
    }

    private JPanel crearTarjeta(
            String titulo,
            JLabel valor) {

        JPanel tarjeta = new JPanel();

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setLayout(
                new BoxLayout(
                        tarjeta,
                        BoxLayout.Y_AXIS
                )
        );

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 10, 20, 10
                        )
                )
        );

        JLabel lblTitulo = new JLabel(titulo);

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        lblTitulo.setForeground(
                GRIS_TEXTO
        );

        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        valor.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        valor.setForeground(
                AZUL_OSCURO
        );

        valor.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        tarjeta.add(lblTitulo);
        tarjeta.add(
                Box.createVerticalStrut(15)
        );

        tarjeta.add(valor);

        return tarjeta;
    }

    private JButton crearBotonVerde(
            String texto) {

        JButton boton = new JButton(texto);

        boton.setBackground(VERDE_QUIMEX);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);

        boton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        boton.setMaximumSize(
                new Dimension(
                        400,
                        40
                )
        );

        boton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return boton;
    }

    private void registrarEntrada() {

        boolean resultado =
                controller.registrarEntrada(idUsuario);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrada registrada correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la entrada.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        cargarAsistencia();
    }

    private void registrarSalida() {

        boolean resultado =
                controller.registrarSalida(idUsuario);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Salida registrada correctamente."
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe registrar una entrada.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        cargarAsistencia();
    }

    private void cargarAsistencia() {

        DateTimeFormatter formatoHora =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        lblFecha.setText(
                LocalDate.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                        )
                )
        );

        Asistencia asistencia =
                controller.buscarAsistenciaHoy(idUsuario);

        if (asistencia == null) {

            lblHoraEntrada.setText("--:--");
            lblHoraSalida.setText("--:--");
            lblEstado.setText("SIN REGISTRO");

            btnEntrada.setEnabled(true);
            btnSalida.setEnabled(false);

            return;
        }

        LocalTime entrada =
                asistencia.getHoraEntrada();

        LocalTime salida =
                asistencia.getHoraSalida();

        if (entrada != null) {

            lblHoraEntrada.setText(
                    entrada.format(formatoHora)
            );

        } else {

            lblHoraEntrada.setText("--:--");
        }

        if (salida != null) {

            lblHoraSalida.setText(
                    salida.format(formatoHora)
            );

        } else {

            lblHoraSalida.setText("--:--");
        }

        if (entrada != null && salida == null) {

            lblEstado.setText("EN JORNADA");

        } else if (entrada != null) {

            lblEstado.setText(
                    "FINALIZADA"
            );

        } else {

            lblEstado.setText(
                    "SIN REGISTRO"
            );
        }

        btnEntrada.setEnabled(false);

        btnSalida.setEnabled(
                salida == null
        );
    }
}