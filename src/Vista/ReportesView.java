package Vista;

import Controller.ReporteController;
import Model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportesView extends JFrame {

    private Usuario usuario;

    private JSpinner fecha;
    private JButton btnAtrasos;
    private JButton btnSalidas;
    private JButton btnInasistencias;
    private JButton btnVolver;

    private JTable tabla;
    private DefaultTableModel modelo;

    public ReportesView(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Quimex - Reportes");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(new Color(245, 247, 250));

        // =========================
        // HEADER
        // =========================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(59, 126, 70));
        header.setPreferredSize(new Dimension(900, 85));

        JLabel titulo = new JLabel("  Quimex");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel datos = new JPanel();
        datos.setOpaque(false);
        datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));

        JLabel nombre = new JLabel(usuario.getNombre());
        nombre.setForeground(Color.WHITE);
        nombre.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel rol = new JLabel("Administrador");
        rol.setForeground(new Color(220, 235, 225));

        datos.add(nombre);
        datos.add(rol);

        btnVolver = new JButton("Volver al menú");
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(new Color(30, 50, 90));
        btnVolver.setFocusPainted(false);

        JPanel derecha = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 20, 25)
        );

        derecha.setOpaque(false);

        derecha.add(datos);
        derecha.add(btnVolver);

        header.add(titulo, BorderLayout.WEST);
        header.add(derecha, BorderLayout.EAST);

        principal.add(header, BorderLayout.NORTH);

        // =========================
        // CONTENIDO
        // =========================

        JPanel contenido = new JPanel();
        contenido.setBackground(new Color(245, 247, 250));
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        JLabel tituloReporte = new JLabel("Reportes de Asistencia");
        tituloReporte.setFont(new Font("Arial", Font.BOLD, 24));
        tituloReporte.setForeground(new Color(30, 50, 90));
        tituloReporte.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenido.add(Box.createVerticalStrut(25));
        contenido.add(tituloReporte);
        contenido.add(Box.createVerticalStrut(20));

        // =========================
        // FECHA
        // =========================

        JPanel panelFecha = new JPanel();

        panelFecha.setBackground(new Color(245, 247, 250));

        JLabel lblFecha = new JLabel("Seleccione una fecha:");

        SpinnerDateModel modeloFecha =
                new SpinnerDateModel(
                        new Date(),
                        null,
                        null,
                        java.util.Calendar.DAY_OF_MONTH
                );

        fecha = new JSpinner(modeloFecha);

        fecha.setEditor(
                new JSpinner.DateEditor(
                        fecha,
                        "dd/MM/yyyy"
                )
        );

        panelFecha.add(lblFecha);
        panelFecha.add(fecha);

        contenido.add(panelFecha);

        contenido.add(Box.createVerticalStrut(15));

        // =========================
        // BOTONES
        // =========================

        JPanel botones = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        5
                )
        );

        botones.setBackground(
                new Color(245, 247, 250)
        );

        btnAtrasos = new JButton(
                "Reporte de atrasos"
        );

        btnSalidas = new JButton(
                "Salidas anticipadas"
        );

        btnInasistencias = new JButton(
                "Inasistencias"
        );

        botones.add(btnAtrasos);
        botones.add(btnSalidas);
        botones.add(btnInasistencias);

        contenido.add(botones);

        contenido.add(Box.createVerticalStrut(20));

        // =========================
        // TABLA
        // =========================

        modelo = new DefaultTableModel();

        tabla = new JTable(modelo);

        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);

        contenido.add(scroll);

        principal.add(
                contenido,
                BorderLayout.CENTER
        );

        add(principal);

        acciones();
    }

    // =========================
    // ACCIONES
    // =========================

    private void acciones() {

        // REPORTE DE ATRASOS

        btnAtrasos.addActionListener(e -> {

            Date fechaSeleccionada =
                    (Date) fecha.getValue();

            cargarAtrasos(fechaSeleccionada);
        });


        // REPORTE DE SALIDAS

        btnSalidas.addActionListener(e -> {

            Date fechaSeleccionada =
                    (Date) fecha.getValue();

            cargarSalidas(fechaSeleccionada);
        });


        // REPORTE DE INASISTENCIAS

        btnInasistencias.addActionListener(e -> {

            Date fechaSeleccionada =
                    (Date) fecha.getValue();

            cargarInasistencias(fechaSeleccionada);
        });


        // VOLVER

        btnVolver.addActionListener(e -> {

            dispose();

            new MenuView(usuario)
                    .setVisible(true);
        });
    }


    // =========================
    // ATRASOS
    // =========================

    private void cargarAtrasos(Date fechaSeleccionada) {

        String fechaTexto =
                new SimpleDateFormat(
                        "yyyy-MM-dd"
                ).format(fechaSeleccionada);

        ReporteController controller =
                new ReporteController();

        ArrayList<Object[]> datos =
                controller.reporteAtrasos(
                        fechaTexto
                );

        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(
                new Object[]{
                        "ID Usuario",
                        "Nombre",
                        "Fecha",
                        "Hora Entrada"
                }
        );

        for (Object[] fila : datos) {

            modelo.addRow(fila);
        }

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron atrasos para la fecha seleccionada.",
                    "Reporte",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // =========================
    // SALIDAS ANTICIPADAS
    // =========================

    private void cargarSalidas(Date fechaSeleccionada) {

        String fechaTexto =
                new SimpleDateFormat(
                        "yyyy-MM-dd"
                ).format(fechaSeleccionada);

        ReporteController controller =
                new ReporteController();

        ArrayList<Object[]> datos =
                controller.reporteSalidasAnticipadas(
                        fechaTexto
                );

        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(
                new Object[]{
                        "ID Usuario",
                        "Nombre",
                        "Fecha",
                        "Hora Salida"
                }
        );

        for (Object[] fila : datos) {

            modelo.addRow(fila);
        }

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron salidas anticipadas para la fecha seleccionada.",
                    "Reporte",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // =========================
    // INASISTENCIAS
    // =========================

    private void cargarInasistencias(Date fechaSeleccionada) {

        String fechaTexto =
                new SimpleDateFormat(
                        "yyyy-MM-dd"
                ).format(fechaSeleccionada);

        ReporteController controller =
                new ReporteController();

        ArrayList<Object[]> datos =
                controller.reporteInasistencias(
                        fechaTexto
                );

        modelo.setRowCount(0);

        modelo.setColumnIdentifiers(
                new Object[]{
                        "ID Usuario",
                        "Nombre",
                        "Fecha"
                }
        );

        for (Object[] fila : datos) {

            modelo.addRow(fila);
        }

        if (datos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron inasistencias para la fecha seleccionada.",
                    "Reporte",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}