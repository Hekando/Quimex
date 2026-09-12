package Vista;

import javax.swing.*;
import java.awt.*;

import Model.Usuario;

public class MenuView extends JFrame {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JButton btnAsistencia;
    private JButton btnUsuarios;
    private JButton btnReportes;
    private JButton btnCerrar;

    // Colores del sistema Quimex
    private final Color VERDE_QUIMEX = new Color(59, 126, 70);
    private final Color AZUL_OSCURO = new Color(30, 50, 90);
    private final Color FONDO = new Color(245, 247, 250);
    private final Color GRIS_TEXTO = new Color(90, 90, 90);

    public MenuView(Usuario usuario) {

        this.usuario = usuario;

        // Configuración de ventana
        setTitle("Quimex - Menú Principal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(FONDO);

        // =====================================================
        // HEADER
        // =====================================================

        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(VERDE_QUIMEX);
        panelHeader.setPreferredSize(new Dimension(900, 85));
        panelHeader.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 10, 20)
        );

        // Parte izquierda del header
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

        // Parte derecha del header
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
        lblCorreo.setAlignmentX(
                Component.RIGHT_ALIGNMENT
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
        lblRol.setAlignmentX(
                Component.RIGHT_ALIGNMENT
        );

        panelDatosUsuario.add(lblCorreo);
        panelDatosUsuario.add(lblRol);

        btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(AZUL_OSCURO);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFont(
                new Font("Arial", Font.BOLD, 12)
        );
        btnCerrar.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        panelUsuario.add(panelDatosUsuario);
        panelUsuario.add(btnCerrar);

        panelHeader.add(
                panelMarca,
                BorderLayout.WEST
        );

        panelHeader.add(
                panelUsuario,
                BorderLayout.EAST
        );

        // =====================================================
        // CONTENIDO
        // =====================================================

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
                        30, 40, 30, 40
                )
        );

        JLabel lblBienvenida = new JLabel(
                "Bienvenido, " + usuario.getNombre()
        );

        lblBienvenida.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        lblBienvenida.setForeground(
                AZUL_OSCURO
        );

        lblBienvenida.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblDescripcion = new JLabel(
                "Seleccione una opción para continuar"
        );

        lblDescripcion.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        lblDescripcion.setForeground(
                GRIS_TEXTO
        );

        lblDescripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelContenido.add(lblBienvenida);
        panelContenido.add(
                Box.createVerticalStrut(5)
        );
        panelContenido.add(lblDescripcion);

        panelContenido.add(
                Box.createVerticalStrut(30)
        );

        // =====================================================
        // TARJETAS
        // =====================================================

        JPanel panelTarjetas = new JPanel(
                new GridLayout(1, 3, 20, 0)
        );

        panelTarjetas.setBackground(FONDO);
        panelTarjetas.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        230
                )
        );

        // Tarjeta asistencia
        JPanel tarjetaAsistencia = crearTarjeta(
                "🕘",
                "Mi Asistencia",
                "Registrar entrada y salida"
        );

        btnAsistencia = obtenerBoton(
                tarjetaAsistencia
        );

        // Tarjeta usuarios
        JPanel tarjetaUsuarios = crearTarjeta(
                "👥",
                "Gestionar Usuarios",
                "Crear, modificar y eliminar"
        );

        btnUsuarios = obtenerBoton(
                tarjetaUsuarios
        );

        // Tarjeta reportes
        JPanel tarjetaReportes = crearTarjeta(
                "📊",
                "Reportes",
                "Atrasos, salidas e inasistencias"
        );

        btnReportes = obtenerBoton(
                tarjetaReportes
        );

        if (usuario.getId_rol() == 1) {

            // ADMINISTRADOR
            panelTarjetas.add(tarjetaAsistencia);
            panelTarjetas.add(tarjetaUsuarios);
            panelTarjetas.add(tarjetaReportes);

        } else {

            // EMPLEADO
            JPanel panelEmpleado = new JPanel(
                    new FlowLayout(
                            FlowLayout.CENTER,
                            0,
                            0
                    )
            );

            panelEmpleado.setBackground(FONDO);

            panelEmpleado.add(tarjetaAsistencia);

            panelTarjetas.setLayout(
                    new FlowLayout(
                            FlowLayout.CENTER,
                            0,
                            0
                    )
            );

            panelTarjetas.add(panelEmpleado);
        }

        panelContenido.add(panelTarjetas);

        // =====================================================
        // PIE
        // =====================================================

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

        panelContenido.add(
                Box.createVerticalGlue()
        );

        panelContenido.add(lblPie);

        // =====================================================
        // ARMAR VENTANA
        // =====================================================

        panelPrincipal.add(
                panelHeader,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                panelContenido,
                BorderLayout.CENTER
        );

        add(panelPrincipal);

        acciones();
    }

    // =========================================================
    // CREAR TARJETA
    // =========================================================

    private JPanel crearTarjeta(
            String icono,
            String titulo,
            String descripcion) {

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
                                new Color(225, 225, 225)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 15, 20, 15
                        )
                )
        );

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(
                new Font("Segoe UI Emoji", Font.PLAIN, 38)
        );
        lblIcono.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(
                new Font("Arial", Font.BOLD, 17)
        );
        lblTitulo.setForeground(
                AZUL_OSCURO
        );
        lblTitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel lblDescripcion = new JLabel(
                "<html><center>"
                        + descripcion
                        + "</center></html>"
        );

        lblDescripcion.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        lblDescripcion.setForeground(
                GRIS_TEXTO
        );

        lblDescripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton boton = new JButton("Ingresar");

        boton.setBackground(VERDE_QUIMEX);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        boton.setMaximumSize(
                new Dimension(120, 32)
        );

        boton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        tarjeta.add(lblIcono);
        tarjeta.add(
                Box.createVerticalStrut(10)
        );

        tarjeta.add(lblTitulo);
        tarjeta.add(
                Box.createVerticalStrut(8)
        );

        tarjeta.add(lblDescripcion);
        tarjeta.add(
                Box.createVerticalStrut(18)
        );

        tarjeta.add(boton);

        // Guardamos el botón dentro de la tarjeta
        tarjeta.putClientProperty(
                "boton",
                boton
        );

        return tarjeta;
    }

    // Obtiene el botón de una tarjeta
    private JButton obtenerBoton(JPanel tarjeta) {

        return (JButton) tarjeta.getClientProperty(
                "boton"
        );
    }

    // =========================================================
    // ACCIONES
    // =========================================================

    // =========================================================
    // ACCIONES
    // =========================================================

    private void acciones() {

        btnAsistencia.addActionListener(e -> {

            //new AsistenciaView().setVisible(true);
            dispose();

        });

        if (btnUsuarios != null) {

            btnUsuarios.addActionListener(e -> {

                //new UsuarioView().setVisible(true);
                dispose();

            });
        }

        if (btnReportes != null) {

            btnReportes.addActionListener(e -> {

                //new ReporteView().setVisible(true);
                dispose();

            });
        }
    }
}