package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import Controller.LoginController;
import Model.Usuario;

/**
 * Clase: Login
 * Descripción: Interfaz gráfica que permite autenticar usuarios.
 */
public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;

    // Campo para ingresar el correo
    private JTextField txtCorreo;

    // Campo para ingresar la contraseña
    private JPasswordField txtPassword;

    // Botón para iniciar sesión
    private JButton btnIngresar;

    // Etiqueta para mostrar mensajes
    private JLabel lblEstado;

    // Controlador encargado de la autenticación
    private LoginController loginController;

    // Colores utilizados para la validación visual
    private final Color COLOR_BORDE_NORMAL = new Color(200, 200, 200);
    private final Color COLOR_BORDE_ERROR = new Color(220, 70, 70);

    public LoginView() {

        // Inicialización del controlador
        loginController = new LoginController();

        // Configuración de la ventana
        setTitle("Quimex - Login");
        setSize(450, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(245, 247, 250));

        // Panel superior
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(59, 126, 70));
        panelHeader.setPreferredSize(new Dimension(450, 100));
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));

        JLabel lblLogo = new JLabel("Quimex");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 28));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Sistema de Asistencia");
        lblSubtitulo.setForeground(new Color(200, 210, 230));
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(Box.createVerticalStrut(25));
        panelHeader.add(lblLogo);
        panelHeader.add(Box.createVerticalStrut(5));
        panelHeader.add(lblSubtitulo);

        // Panel del formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        );
        panelFormulario.setLayout(
                new BoxLayout(panelFormulario, BoxLayout.Y_AXIS)
        );

        // Mensaje inicial
        lblEstado = new JLabel("Complete correo y contraseña");
        lblEstado.setOpaque(true);
        lblEstado.setBackground(new Color(235, 248, 245));
        lblEstado.setForeground(new Color(40, 90, 70));
        lblEstado.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 230, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 12, 10, 12
                        )
                )
        );
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblEstado.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 45)
        );

        // Campo correo
        txtCorreo = new JTextField();
        txtCorreo.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 32)
        );
        txtCorreo.setBorder(
                BorderFactory.createLineBorder(COLOR_BORDE_NORMAL)
        );
        agregarPlaceholder(
                txtCorreo,
                "Ej: correo@empresa.cl"
        );

        // Campo contraseña
        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 32)
        );
        txtPassword.setBorder(
                BorderFactory.createLineBorder(COLOR_BORDE_NORMAL)
        );
        agregarPlaceholderPassword(
                txtPassword,
                "Ej: 12345"
        );

        // Botón ingresar
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBackground(new Color(30, 50, 90));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIngresar.setMaximumSize(
                new Dimension(160, 38)
        );
        btnIngresar.setPreferredSize(
                new Dimension(160, 38)
        );

        // Evento del botón
        btnIngresar.addActionListener(this::autenticar);

        // Etiqueta correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        lblCorreo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Etiqueta contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        lblPassword.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Construcción del formulario
        panelFormulario.add(lblEstado);
        panelFormulario.add(
                Box.createVerticalStrut(18)
        );

        panelFormulario.add(lblCorreo);
        panelFormulario.add(
                Box.createVerticalStrut(6)
        );
        panelFormulario.add(txtCorreo);
        panelFormulario.add(
                Box.createVerticalStrut(14)
        );

        panelFormulario.add(lblPassword);
        panelFormulario.add(
                Box.createVerticalStrut(6)
        );
        panelFormulario.add(txtPassword);
        panelFormulario.add(
                Box.createVerticalStrut(14)
        );

        panelFormulario.add(btnIngresar);

        // Agregar paneles a la ventana
        panelPrincipal.add(
                panelHeader,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                panelFormulario,
                BorderLayout.CENTER
        );

        add(panelPrincipal);

        // Centrar ventana
        setLocationRelativeTo(null);
    }

    /**
     * Método que valida los campos y autentica al usuario.
     */
    private void autenticar(ActionEvent e) {

        limpiarErrores();

        String correo = txtCorreo.getText().trim();
        String password = new String(
                txtPassword.getPassword()
        ).trim();

        boolean valido = true;

        // Validación del correo
        if (correo.isEmpty()
                || correo.equals("Ej: correo@empresa.cl")) {

            marcarError(txtCorreo);
            valido = false;
        }

        // Validación de contraseña
        if (password.isEmpty()
                || password.equals("Ej: 12345")) {

            marcarError(txtPassword);
            valido = false;
        }

        // Si falta algún campo
        if (!valido) {
            mostrarError(
                    "Debe completar todos los campos correctamente."
            );
            return;
        }

        // Enviar datos al controlador
        Usuario usuario =
                loginController.autenticar(
                        correo,
                        password
                );

        // Resultado de la autenticación
        if (usuario != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bienvenido/a\n" + usuario.getNombre(),
                    "Login exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Cerrar ventana de login
            this.dispose();

            new MenuView(usuario).setVisible(true);

        } else {

            mostrarError(
                    "Credenciales incorrectas."
            );

            txtPassword.setText("");
            txtCorreo.requestFocus();
        }
    }

    // Marca un campo visualmente como error
    private void marcarError(JComponent campo) {

        campo.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE_ERROR,
                        2
                )
        );
    }

    // Limpia los errores visuales
    private void limpiarErrores() {

        txtCorreo.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE_NORMAL
                )
        );

        txtPassword.setBorder(
                BorderFactory.createLineBorder(
                        COLOR_BORDE_NORMAL
                )
        );

        lblEstado.setText(
                "Complete correo y contraseña"
        );

        lblEstado.setBackground(
                new Color(235, 248, 245)
        );

        lblEstado.setForeground(
                new Color(40, 90, 70)
        );
    }

    // Muestra un mensaje de error
    private void mostrarError(String mensaje) {

        lblEstado.setText(mensaje);

        lblEstado.setBackground(
                new Color(252, 235, 235)
        );

        lblEstado.setForeground(
                new Color(150, 50, 50)
        );
    }

    /**
     * Agrega un texto de ejemplo al campo de correo.
     */
    private void agregarPlaceholder(
            JTextField campo,
            String texto) {

        campo.setText(texto);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(FocusEvent e) {

                        if (campo.getText().equals(texto)) {
                            campo.setText("");
                            campo.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        if (campo.getText().trim().isEmpty()) {
                            campo.setText(texto);
                            campo.setForeground(Color.GRAY);
                        }
                    }
                }
        );
    }

    /**
     * Agrega un placeholder al campo de contraseña.
     */
    private void agregarPlaceholderPassword(
            JPasswordField campo,
            String texto) {

        campo.setEchoChar((char) 0);
        campo.setText(texto);
        campo.setForeground(Color.GRAY);

        campo.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(FocusEvent e) {

                        String valor =
                                new String(campo.getPassword());

                        if (valor.equals(texto)) {

                            campo.setText("");
                            campo.setForeground(Color.BLACK);
                            campo.setEchoChar('•');
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        String valor =
                                new String(campo.getPassword());

                        if (valor.trim().isEmpty()) {

                            campo.setEchoChar((char) 0);
                            campo.setText(texto);
                            campo.setForeground(Color.GRAY);
                        }
                    }
                }
        );
    }
}