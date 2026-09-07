package Vista;

import javax.swing.*;
import java.awt.*;
import Model.Usuario;

public class MenuView extends JFrame {

    private Usuario usuario;
    private JButton btnAsistencia;
    private JButton btnUsuarios;
    private JButton btnReportes;
    private JButton btnCerrar;


    public MenuView(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Quimex - Menú Principal");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JLabel titulo = new JLabel(
                "Bienvenido: " + usuario.getNombre()
        );

        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnAsistencia = new JButton("Mi Asistencia");
        btnAsistencia.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnUsuarios = new JButton("Gestionar Usuarios");
        btnUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnReportes = new JButton("Reportes");
        btnReportes.setAlignmentX(Component.CENTER_ALIGNMENT);


        btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);



        panel.add(Box.createVerticalStrut(30));
        panel.add(titulo);

        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAsistencia);


        // Solo administrador puede gestionar usuarios y ver reportes
        if (usuario.getId_rol() == 1) {

            panel.add(Box.createVerticalStrut(10));
            panel.add(btnUsuarios);

            panel.add(Box.createVerticalStrut(10));
            panel.add(btnReportes);
        }

        panel.add(Box.createVerticalStrut(20));
        panel.add(btnCerrar);


        add(panel);


        acciones();
    }


    private void acciones() {


        btnAsistencia.addActionListener(e -> {

            // new AsistenciaView(usuario).setVisible(true);
            dispose();

        });



        btnUsuarios.addActionListener(e -> {

            // new UsuariosView().setVisible(true);
            dispose();

        });



        btnReportes.addActionListener(e -> {

            // new ReportesView().setVisible(true);
            dispose();

        });



        btnCerrar.addActionListener(e -> {

            new LoginView().setVisible(true);
            dispose();

        });

    }


}