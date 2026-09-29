package Vista;

// Controlador del módulo de asistencia.
// La vista utiliza este controlador para registrar y consultar asistencias.
import Controller.AsistenciaController;

// Modelo que representa un registro de asistencia.
import Model.Asistencia;

// Modelo que representa al usuario que inició sesión.
import Model.Usuario;

// Librería Swing para crear la interfaz gráfica.
import javax.swing.*;

// Librería AWT para colores, tamaños, layouts, fuentes, etc.
import java.awt.*;

// Permite trabajar con fechas.
import java.time.LocalDate;

// Permite trabajar con horas.
import java.time.LocalTime;

// Permite definir el formato en que mostramos fecha y hora.
import java.time.format.DateTimeFormatter;


/*
 * ============================================================
 * VISTA DEL MÓDULO CONTROL DE ASISTENCIA
 * ============================================================
 *
 * Esta clase representa la interfaz gráfica del módulo
 * de Control de Asistencia de Quimex.
 *
 * Extiende JFrame porque esta clase corresponde a una ventana.
 *
 * FUNCIONES PRINCIPALES:
 *
 * 1. Mostrar la fecha actual.
 * 2. Mostrar la hora de entrada.
 * 3. Mostrar la hora de salida.
 * 4. Mostrar el estado de la asistencia.
 * 5. Registrar una entrada.
 * 6. Registrar una salida.
 * 7. Detectar una entrada atrasada.
 * 8. Detectar una salida anticipada.
 * 9. Mostrar confirmaciones al trabajador.
 * 10. Volver al menú principal.
 * 11. Cerrar sesión.
 *
 * HORARIO DEFINIDO:
 *
 * Entrada: 09:30
 * Salida:  17:30
 *
 * REGLAS:
 *
 * Entrada <= 09:30 = ENTRADA A TIEMPO
 * Entrada >  09:30 = ENTRADA ATRASADA
 *
 * Salida <  17:30 = SALIDA ANTICIPADA
 * Salida >= 17:30 = SALIDA A TIEMPO
 *
 * FLUJO DEL MÓDULO:
 *
 * AsistenciaView
 *       ↓
 * AsistenciaController
 *       ↓
 * AsistenciaDAO
 *       ↓
 * MariaDB
 *
 * ============================================================
 */

public class AsistenciaView extends JFrame {

    /*
     * Controlador utilizado para comunicarnos con la lógica
     * del módulo de asistencia.
     */
    private final AsistenciaController controller;

    /*
     * ID del usuario que actualmente tiene iniciada la sesión.
     *
     * Este ID se utiliza para registrar y consultar
     * solamente la asistencia de ese trabajador.
     */
    private final int idUsuario;

    /*
     * Objeto que contiene la información completa
     * del usuario que inició sesión.
     */
    private final Usuario usuario;


    // =========================================================
    // ETIQUETAS DE LA INTERFAZ
    // =========================================================

    // Muestra la fecha actual.
    private JLabel lblFecha;

    // Muestra la hora registrada como entrada.
    private JLabel lblHoraEntrada;

    // Muestra la hora registrada como salida.
    private JLabel lblHoraSalida;

    /*
     * Muestra el estado de la asistencia.
     *
     * Puede mostrar:
     *
     * SIN REGISTRO
     * EN JORNADA
     * ENTRADA ATRASADA
     * SALIDA ANTICIPADA
     * FINALIZADA
     */
    private JLabel lblEstado;


    // =========================================================
    // BOTONES
    // =========================================================

    // Permite registrar la entrada.
    private JButton btnEntrada;

    // Permite registrar la salida.
    private JButton btnSalida;

    // Permite volver al menú sin cerrar sesión.
    private JButton btnVolver;

    // Permite cerrar sesión y regresar al Login.
    private JButton btnCerrarSesion;


    // =========================================================
    // COLORES UTILIZADOS EN LA INTERFAZ
    // =========================================================

    // Verde principal utilizado por Quimex.
    private final Color VERDE_QUIMEX =
            new Color(59, 126, 70);

    // Azul utilizado principalmente para textos.
    private final Color AZUL_OSCURO =
            new Color(30, 50, 90);

    // Color de fondo de la ventana.
    private final Color FONDO =
            new Color(245, 247, 250);

    // Color utilizado para textos secundarios.
    private final Color GRIS_TEXTO =
            new Color(90, 90, 90);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    /*
     * El constructor recibe el usuario que inició sesión.
     *
     * Esto nos permite saber qué trabajador está utilizando
     * el sistema y registrar su asistencia correctamente.
     */
    public AsistenciaView(Usuario usuario) {

        // Guardamos el usuario recibido.
        this.usuario = usuario;

        // Obtenemos su ID.
        this.idUsuario = usuario.getIdUsuario();

        // Creamos el controlador de asistencia.
        this.controller = new AsistenciaController();

        /*
         * Configuramos las características generales
         * de la ventana.
         */
        configurarVentana();

        /*
         * Creamos todos los componentes gráficos:
         *
         * botones,
         * tarjetas,
         * textos,
         * encabezado, etc.
         */
        crearComponentes();

        /*
         * Consultamos inmediatamente si el trabajador
         * ya tiene una asistencia registrada hoy.
         */
        cargarAsistencia();
    }


    // =========================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =========================================================

    /*
     * Este método configura las características
     * generales del JFrame.
     */
    private void configurarVentana() {

        // Título de la ventana.
        setTitle("Quimex - Control de Asistencia");

        // Tamaño de la ventana.
        setSize(900, 600);

        /*
         * Eliminamos la barra superior estándar
         * de Windows.
         */
        setUndecorated(true);

        /*
         * Hace que la ventana aparezca
         * centrada en la pantalla.
         */
        setLocationRelativeTo(null);

        /*
         * Cuando cerramos esta ventana,
         * solamente se elimina esta instancia.
         */
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        /*
         * Impedimos que el usuario cambie
         * manualmente el tamaño.
         */
        setResizable(false);
    }


    // =========================================================
    // CREACIÓN DE COMPONENTES
    // =========================================================

    /*
     * Este método construye toda la interfaz gráfica.
     */
    private void crearComponentes() {

        /*
         * Panel principal.
         *
         * BorderLayout permite dividirlo en zonas:
         *
         * NORTH
         * CENTER
         * SOUTH
         * EAST
         * WEST
         */
        JPanel panelPrincipal =
                new JPanel(new BorderLayout());

        panelPrincipal.setBackground(FONDO);


        // =====================================================
        // HEADER / ENCABEZADO
        // =====================================================

        /*
         * Creamos el encabezado verde que aparece
         * en la parte superior.
         */
        JPanel panelHeader =
                new JPanel(new BorderLayout());

        panelHeader.setBackground(VERDE_QUIMEX);

        panelHeader.setPreferredSize(
                new Dimension(900, 85)
        );

        /*
         * Agregamos espacio interior al encabezado.
         */
        panelHeader.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        25,
                        10,
                        20
                )
        );


        // =====================================================
        // MARCA QUIMEX
        // =====================================================

        JPanel panelMarca = new JPanel();

        /*
         * false significa que el panel no tendrá
         * un fondo propio y utilizará el del Header.
         */
        panelMarca.setOpaque(false);

        /*
         * BoxLayout Y_AXIS coloca los elementos
         * verticalmente.
         */
        panelMarca.setLayout(
                new BoxLayout(
                        panelMarca,
                        BoxLayout.Y_AXIS
                )
        );


        // Nombre de la empresa.
        JLabel lblLogo =
                new JLabel("Quimex");

        lblLogo.setForeground(Color.WHITE);

        lblLogo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );


        // Subtítulo debajo de Quimex.
        JLabel lblSubtitulo =
                new JLabel(
                        "Sistema de Asistencia"
                );

        lblSubtitulo.setForeground(
                new Color(220, 235, 225)
        );

        lblSubtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        // Agregamos logo y subtítulo.
        panelMarca.add(lblLogo);
        panelMarca.add(lblSubtitulo);


        // =====================================================
        // INFORMACIÓN DEL USUARIO
        // =====================================================

        /*
         * Este panel aparece en la parte derecha
         * del encabezado.
         */
        JPanel panelUsuario =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                10
                        )
                );

        panelUsuario.setOpaque(false);


        /*
         * Panel donde mostraremos correo y rol
         * verticalmente.
         */
        JPanel panelDatosUsuario =
                new JPanel();

        panelDatosUsuario.setOpaque(false);

        panelDatosUsuario.setLayout(
                new BoxLayout(
                        panelDatosUsuario,
                        BoxLayout.Y_AXIS
                )
        );


        // Mostramos el correo del usuario conectado.
        JLabel lblCorreo =
                new JLabel(
                        usuario.getCorreo()
                );

        lblCorreo.setForeground(Color.WHITE);

        lblCorreo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        // =====================================================
        // DETERMINAR ROL DEL USUARIO
        // =====================================================

        String rol;

        /*
         * Si id_rol es 1 consideramos que
         * corresponde al Administrador.
         */
        if (usuario.getId_rol() == 1) {

            rol = "Administrador";

        } else {

            rol = "Empleado";
        }


        // Mostramos el rol debajo del correo.
        JLabel lblRol =
                new JLabel(rol);

        lblRol.setForeground(
                new Color(220, 235, 225)
        );

        lblRol.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );


        // =====================================================
        // BOTÓN VOLVER AL MENÚ
        // =====================================================

        /*
         * Este botón regresa a MenuView
         * sin cerrar la sesión.
         */
        btnVolver =
                new JButton(
                        "Volver al menú"
                );

        btnVolver.setBackground(Color.WHITE);

        btnVolver.setForeground(
                AZUL_OSCURO
        );

        btnVolver.setFocusPainted(false);

        btnVolver.setBorderPainted(false);

        btnVolver.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );


        // =====================================================
        // BOTÓN CERRAR SESIÓN
        // =====================================================

        /*
         * Este botón regresa directamente
         * a la pantalla de Login.
         */
        btnCerrarSesion =
                new JButton(
                        "Cerrar sesión"
                );

        btnCerrarSesion.setBackground(
                Color.WHITE
        );

        btnCerrarSesion.setForeground(
                AZUL_OSCURO
        );

        btnCerrarSesion.setFocusPainted(
                false
        );

        btnCerrarSesion.setBorderPainted(
                false
        );

        btnCerrarSesion.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );


        // =====================================================
        // AGREGAR DATOS DEL USUARIO
        // =====================================================

        panelDatosUsuario.add(lblCorreo);
        panelDatosUsuario.add(lblRol);


        /*
         * Agregamos los datos y botones
         * al panel del usuario.
         */
        panelUsuario.add(
                panelDatosUsuario
        );

        panelUsuario.add(
                btnVolver
        );


        /*
         * Agregamos un pequeño espacio
         * entre los dos botones.
         */
        panelUsuario.add(
                Box.createHorizontalStrut(10)
        );


        panelUsuario.add(
                btnCerrarSesion
        );


        /*
         * Agregamos la marca Quimex
         * al lado izquierdo.
         */
        panelHeader.add(
                panelMarca,
                BorderLayout.WEST
        );


        /*
         * Agregamos datos y botones
         * al lado derecho.
         */
        panelHeader.add(
                panelUsuario,
                BorderLayout.EAST
        );


        // =====================================================
        // CONTENIDO PRINCIPAL
        // =====================================================

        JPanel panelContenido =
                new JPanel();

        panelContenido.setBackground(
                FONDO
        );

        /*
         * Los componentes se mostrarán
         * verticalmente.
         */
        panelContenido.setLayout(
                new BoxLayout(
                        panelContenido,
                        BoxLayout.Y_AXIS
                )
        );


        // Espacios interiores.
        panelContenido.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );


        // =====================================================
        // TÍTULO
        // =====================================================

        JLabel titulo =
                new JLabel(
                        "CONTROL DE ASISTENCIA"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titulo.setForeground(
                AZUL_OSCURO
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // =====================================================
        // DESCRIPCIÓN
        // =====================================================

        JLabel descripcion =
                new JLabel(
                        "Registra tu entrada y salida"
                );

        descripcion.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        descripcion.setForeground(
                GRIS_TEXTO
        );

        descripcion.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // Agregamos título.
        panelContenido.add(titulo);


        // Espacio entre título y descripción.
        panelContenido.add(
                Box.createVerticalStrut(5)
        );


        // Agregamos descripción.
        panelContenido.add(descripcion);


        // Espacio antes de las tarjetas.
        panelContenido.add(
                Box.createVerticalStrut(25)
        );


        // =====================================================
        // TARJETAS DE INFORMACIÓN
        // =====================================================

        /*
         * Creamos cuatro tarjetas:
         *
         * 1. Fecha
         * 2. Hora entrada
         * 3. Hora salida
         * 4. Estado
         *
         * GridLayout(1,4) significa:
         *
         * 1 fila
         * 4 columnas
         */
        JPanel panelTarjetas =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        panelTarjetas.setBackground(
                FONDO
        );

        panelTarjetas.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        140
                )
        );


        /*
         * Creamos las etiquetas donde posteriormente
         * cargaremos los datos.
         */
        lblFecha = new JLabel();

        lblHoraEntrada = new JLabel();

        lblHoraSalida = new JLabel();

        lblEstado = new JLabel();


        // Tarjeta de fecha.
        JPanel tarjetaFecha =
                crearTarjeta(
                        "Fecha",
                        lblFecha
                );


        // Tarjeta de hora de entrada.
        JPanel tarjetaEntrada =
                crearTarjeta(
                        "Hora entrada",
                        lblHoraEntrada
                );


        // Tarjeta de hora de salida.
        JPanel tarjetaSalida =
                crearTarjeta(
                        "Hora salida",
                        lblHoraSalida
                );


        // Tarjeta de estado.
        JPanel tarjetaEstado =
                crearTarjeta(
                        "Estado",
                        lblEstado
                );


        /*
         * Agregamos las cuatro tarjetas.
         */
        panelTarjetas.add(
                tarjetaFecha
        );

        panelTarjetas.add(
                tarjetaEntrada
        );

        panelTarjetas.add(
                tarjetaSalida
        );

        panelTarjetas.add(
                tarjetaEstado
        );


        // Agregamos las tarjetas al contenido.
        panelContenido.add(
                panelTarjetas
        );


        // Espacio antes de los botones.
        panelContenido.add(
                Box.createVerticalStrut(30)
        );


        // =====================================================
        // BOTONES DE ASISTENCIA
        // =====================================================

        /*
         * Utilizamos crearBotonVerde()
         * para que ambos botones tengan
         * el mismo diseño.
         */
        btnEntrada =
                crearBotonVerde(
                        "MARCAR ENTRADA"
                );


        btnSalida =
                crearBotonVerde(
                        "MARCAR SALIDA"
                );


        // Agregamos botón de entrada.
        panelContenido.add(
                btnEntrada
        );


        // Espacio entre botones.
        panelContenido.add(
                Box.createVerticalStrut(10)
        );


        // Agregamos botón de salida.
        panelContenido.add(
                btnSalida
        );


        /*
         * Empuja el pie de página
         * hacia la parte inferior.
         */
        panelContenido.add(
                Box.createVerticalGlue()
        );


        // =====================================================
        // PIE DE PÁGINA
        // =====================================================

        JLabel lblPie =
                new JLabel(
                        "Sistema de Asistencia Quimex"
                );

        lblPie.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        lblPie.setForeground(
                new Color(
                        130,
                        130,
                        130
                )
        );

        lblPie.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        panelContenido.add(
                lblPie
        );


        // =====================================================
        // AGREGAR COMPONENTES A LA VENTANA
        // =====================================================

        /*
         * Header en la parte superior.
         */
        panelPrincipal.add(
                panelHeader,
                BorderLayout.NORTH
        );


        /*
         * Contenido en el centro.
         */
        panelPrincipal.add(
                panelContenido,
                BorderLayout.CENTER
        );


        /*
         * Finalmente agregamos el panel principal
         * al JFrame.
         */
        add(panelPrincipal);


        // =====================================================
        // ACCIONES DE LOS BOTONES
        // =====================================================

        /*
         * Cuando presionamos MARCAR ENTRADA
         * ejecutamos registrarEntrada().
         */
        btnEntrada.addActionListener(
                e -> registrarEntrada()
        );


        /*
         * Cuando presionamos MARCAR SALIDA
         * ejecutamos registrarSalida().
         */
        btnSalida.addActionListener(
                e -> registrarSalida()
        );


        /*
         * VOLVER AL MENÚ
         *
         * Abre MenuView enviando el usuario actual.
         *
         * Esto significa que la sesión continúa abierta.
         */
        btnVolver.addActionListener(e -> {

            new MenuView(usuario)
                    .setVisible(true);

            /*
             * Cerramos la ventana actual
             * de asistencia.
             */
            dispose();
        });


        /*
         * CERRAR SESIÓN
         *
         * Abre LoginView.
         *
         * No enviamos el objeto usuario,
         * porque el trabajador deberá iniciar
         * sesión nuevamente.
         */
        btnCerrarSesion.addActionListener(e -> {

            new LoginView()
                    .setVisible(true);

            // Cerramos AsistenciaView.
            dispose();
        });
    }


    // =========================================================
    // MÉTODO PARA CREAR TARJETAS
    // =========================================================

    /*
     * Este método evita repetir código.
     *
     * Recibe:
     *
     * titulo = nombre de la tarjeta.
     * valor  = JLabel donde mostraremos el dato.
     *
     * Ejemplo:
     *
     * crearTarjeta("Hora entrada", lblHoraEntrada);
     */
    private JPanel crearTarjeta(
            String titulo,
            JLabel valor) {

        // Creamos el panel de la tarjeta.
        JPanel tarjeta =
                new JPanel();

        // Fondo blanco.
        tarjeta.setBackground(
                Color.WHITE
        );


        /*
         * Los elementos de la tarjeta
         * se colocan verticalmente.
         */
        tarjeta.setLayout(
                new BoxLayout(
                        tarjeta,
                        BoxLayout.Y_AXIS
                )
        );


        /*
         * Creamos:
         *
         * borde exterior gris
         *
         * +
         *
         * espacio interior.
         */
        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                10,
                                20,
                                10
                        )
                )
        );


        // Título de la tarjeta.
        JLabel lblTitulo =
                new JLabel(titulo);

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


        /*
         * Configuramos el valor que se mostrará
         * dentro de la tarjeta.
         */
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


        // Agregamos el título.
        tarjeta.add(
                lblTitulo
        );


        // Espacio.
        tarjeta.add(
                Box.createVerticalStrut(15)
        );


        // Agregamos el valor.
        tarjeta.add(
                valor
        );


        // Retornamos la tarjeta terminada.
        return tarjeta;
    }


    // =========================================================
    // MÉTODO PARA CREAR BOTONES VERDES
    // =========================================================

    /*
     * Este método permite crear botones
     * con el mismo diseño.
     *
     * Se utiliza para:
     *
     * MARCAR ENTRADA
     * MARCAR SALIDA
     */
    private JButton crearBotonVerde(
            String texto) {

        // Creamos el botón.
        JButton boton =
                new JButton(texto);


        // Fondo verde Quimex.
        boton.setBackground(
                VERDE_QUIMEX
        );


        // Texto blanco.
        boton.setForeground(
                Color.WHITE
        );


        // Quitamos efecto de enfoque.
        boton.setFocusPainted(
                false
        );


        // Quitamos borde estándar.
        boton.setBorderPainted(
                false
        );


        // Fuente.
        boton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );


        // Tamaño máximo.
        boton.setMaximumSize(
                new Dimension(
                        400,
                        40
                )
        );


        // Centramos el botón.
        boton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        /*
         * Cuando pasamos el mouse por encima
         * aparece la mano.
         */
        boton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        // Retornamos el botón terminado.
        return boton;
    }


    // =========================================================
    // REGISTRAR ENTRADA
    // =========================================================

    /*
     * Este método se ejecuta cuando el trabajador
     * presiona MARCAR ENTRADA.
     *
     * PROCESO:
     *
     * 1. Enviamos el ID al Controller.
     * 2. Controller llama al DAO.
     * 3. DAO registra la entrada en MariaDB.
     * 4. Recibimos true o false.
     * 5. Si fue correcto, evaluamos el horario.
     * 6. Mostramos una confirmación.
     * 7. Actualizamos la interfaz.
     */
    private void registrarEntrada() {

        /*
         * Solicitamos al controlador registrar
         * la entrada del usuario actual.
         */
        boolean resultado =
                controller.registrarEntrada(
                        idUsuario
                );


        /*
         * Si retorna true significa que
         * la entrada se registró correctamente.
         */
        if (resultado) {


            /*
             * Obtenemos la hora actual.
             */
            LocalTime horaActual =
                    LocalTime.now();


            /*
             * Definimos el límite de entrada:
             *
             * 09:30
             */
            LocalTime horaLimiteEntrada =
                    LocalTime.of(
                            9,
                            30
                    );


            // Variable donde guardaremos el estado.
            String estadoEntrada;


            /*
             * isAfter() pregunta:
             *
             * ¿La hora actual es DESPUÉS
             * de las 09:30?
             */
            if (horaActual.isAfter(
                    horaLimiteEntrada)) {


                // Llegó después de las 09:30.
                estadoEntrada =
                        "ENTRADA ATRASADA";


            } else {


                /*
                 * Llegó antes o exactamente
                 * a las 09:30.
                 */
                estadoEntrada =
                        "ENTRADA A TIEMPO";
            }


            /*
             * Mostramos la confirmación.
             *
             * Incluimos:
             *
             * mensaje,
             * hora,
             * estado.
             */
            JOptionPane.showMessageDialog(

                    this,

                    "Entrada registrada correctamente.\n"

                            + "Hora: "

                            + horaActual.format(
                            DateTimeFormatter.ofPattern(
                                    "HH:mm:ss"
                            )
                    )

                            + "\nEstado: "

                            + estadoEntrada,

                    "Registro de entrada",

                    JOptionPane.INFORMATION_MESSAGE
            );


        } else {


            /*
             * Si resultado es false,
             * mostramos una advertencia.
             */
            JOptionPane.showMessageDialog(

                    this,

                    "No se pudo registrar la entrada.",

                    "Aviso",

                    JOptionPane.WARNING_MESSAGE
            );
        }


        /*
         * Volvemos a consultar la asistencia
         * para actualizar automáticamente
         * las tarjetas.
         *
         * Por eso NO necesitamos un botón
         * "Actualizar".
         */
        cargarAsistencia();
    }


    // =========================================================
    // REGISTRAR SALIDA
    // =========================================================

    /*
     * Este método se ejecuta cuando el trabajador
     * presiona MARCAR SALIDA.
     *
     * La hora normal de salida es 17:30.
     */
    private void registrarSalida() {


        /*
         * Solicitamos al controlador registrar
         * la salida.
         */
        boolean resultado =
                controller.registrarSalida(
                        idUsuario
                );


        if (resultado) {


            // Obtenemos la hora actual.
            LocalTime horaActual =
                    LocalTime.now();


            /*
             * Definimos el horario
             * normal de salida.
             */
            LocalTime horaLimiteSalida =
                    LocalTime.of(
                            17,
                            30
                    );


            String estadoSalida;


            /*
             * isBefore() pregunta:
             *
             * ¿La hora de salida es ANTES
             * de las 17:30?
             */
            if (horaActual.isBefore(
                    horaLimiteSalida)) {


                // Salió antes de las 17:30.
                estadoSalida =
                        "SALIDA ANTICIPADA";


            } else {


                /*
                 * Salió a las 17:30
                 * o después.
                 */
                estadoSalida =
                        "SALIDA A TIEMPO";
            }


            /*
             * Mostramos confirmación
             * de salida.
             */
            JOptionPane.showMessageDialog(

                    this,

                    "Salida registrada correctamente.\n"

                            + "Hora: "

                            + horaActual.format(
                            DateTimeFormatter.ofPattern(
                                    "HH:mm:ss"
                            )
                    )

                            + "\nEstado: "

                            + estadoSalida,

                    "Registro de salida",

                    JOptionPane.INFORMATION_MESSAGE
            );


        } else {


            /*
             * Si no se pudo actualizar ningún registro,
             * mostramos la advertencia correspondiente.
             */
            JOptionPane.showMessageDialog(

                    this,

                    "Primero debe registrar una entrada.",

                    "Aviso",

                    JOptionPane.WARNING_MESSAGE
            );
        }


        /*
         * Refrescamos automáticamente
         * la información mostrada.
         */
        cargarAsistencia();
    }


    // =========================================================
    // CARGAR ASISTENCIA
    // =========================================================

    /*
     * Este método consulta la asistencia del usuario
     * correspondiente al día actual.
     *
     * Se ejecuta:
     *
     * - Al abrir AsistenciaView.
     * - Después de marcar entrada.
     * - Después de marcar salida.
     *
     * Esto permite actualizar automáticamente
     * la interfaz sin utilizar un botón Actualizar.
     */
    private void cargarAsistencia() {


        /*
         * Formato que utilizaremos para mostrar
         * las horas.
         *
         * Ejemplo:
         *
         * 09:30:25
         */
        DateTimeFormatter formatoHora =
                DateTimeFormatter.ofPattern(
                        "HH:mm:ss"
                );


        // =====================================================
        // MOSTRAR FECHA ACTUAL
        // =====================================================

        /*
         * Mostramos la fecha utilizando:
         *
         * día/mes/año
         *
         * Ejemplo:
         *
         * 26/09/2026
         */
        lblFecha.setText(

                LocalDate.now().format(

                        DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                        )
                )
        );


        // =====================================================
        // CONSULTAR ASISTENCIA
        // =====================================================

        /*
         * Solicitamos al Controller buscar
         * la asistencia del usuario actual.
         */
        Asistencia asistencia =
                controller.buscarAsistenciaHoy(
                        idUsuario
                );


        // =====================================================
        // SI NO EXISTE ASISTENCIA
        // =====================================================

        /*
         * Si DAO no encuentra un registro,
         * retorna null.
         */
        if (asistencia == null) {


            // Todavía no existe hora de entrada.
            lblHoraEntrada.setText(
                    "--:--"
            );


            // Tampoco existe hora de salida.
            lblHoraSalida.setText(
                    "--:--"
            );


            // Estado inicial.
            lblEstado.setText(
                    "SIN REGISTRO"
            );


            /*
             * Permitimos registrar entrada.
             */
            btnEntrada.setEnabled(
                    true
            );


            /*
             * No permitimos registrar salida
             * porque todavía no existe entrada.
             */
            btnSalida.setEnabled(
                    false
            );


            /*
             * Terminamos el método aquí porque
             * no hay más datos que mostrar.
             */
            return;
        }


        // =====================================================
        // OBTENER HORAS
        // =====================================================

        /*
         * Obtenemos la hora de entrada
         * desde el objeto Asistencia.
         */
        LocalTime entrada =
                asistencia.getHoraEntrada();


        /*
         * Obtenemos la hora de salida.
         *
         * Puede ser null si el trabajador
         * todavía está trabajando.
         */
        LocalTime salida =
                asistencia.getHoraSalida();


        // =====================================================
        // MOSTRAR HORA DE ENTRADA
        // =====================================================

        if (entrada != null) {


            // Mostramos la hora registrada.
            lblHoraEntrada.setText(

                    entrada.format(
                            formatoHora
                    )
            );


        } else {


            lblHoraEntrada.setText(
                    "--:--"
            );
        }


        // =====================================================
        // MOSTRAR HORA DE SALIDA
        // =====================================================

        if (salida != null) {


            lblHoraSalida.setText(

                    salida.format(
                            formatoHora
                    )
            );


        } else {


            /*
             * Si todavía está trabajando,
             * no existe hora de salida.
             */
            lblHoraSalida.setText(
                    "--:--"
            );
        }


        // =====================================================
        // HORARIOS DE LA EMPRESA
        // =====================================================

        /*
         * Hora máxima para considerar
         * una entrada a tiempo.
         */
        LocalTime horaLimiteEntrada =
                LocalTime.of(
                        9,
                        30
                );


        /*
         * Hora normal de salida.
         */
        LocalTime horaLimiteSalida =
                LocalTime.of(
                        17,
                        30
                );


        // =====================================================
        // DETERMINAR ESTADO DE ASISTENCIA
        // =====================================================

        /*
         * CASO 1:
         *
         * Existe entrada
         * pero todavía NO existe salida.
         *
         * Esto significa que el trabajador
         * todavía se encuentra en jornada.
         */
        if (entrada != null
                && salida == null) {


            /*
             * Revisamos si llegó después
             * de las 09:30.
             */
            if (entrada.isAfter(
                    horaLimiteEntrada)) {


                lblEstado.setText(
                        "ENTRADA ATRASADA"
                );


            } else {


                lblEstado.setText(
                        "EN JORNADA"
                );
            }


            /*
             * CASO 2:
             *
             * Existe entrada y también salida.
             *
             * La jornada ya terminó.
             */
        } else if (
                entrada != null
                        && salida != null) {


            /*
             * Si salió antes de las 17:30
             * corresponde a salida anticipada.
             */
            if (salida.isBefore(
                    horaLimiteSalida)) {


                lblEstado.setText(
                        "SALIDA ANTICIPADA"
                );


            } else {


                /*
                 * Si salió a las 17:30
                 * o después, la jornada está
                 * finalizada normalmente.
                 */
                lblEstado.setText(
                        "FINALIZADA"
                );
            }


        } else {


            /*
             * Caso de seguridad por si no existe
             * una entrada válida.
             */
            lblEstado.setText(
                    "SIN REGISTRO"
            );
        }


        // =====================================================
        // CONTROL DE BOTONES
        // =====================================================

        /*
         * Si ya existe una asistencia,
         * deshabilitamos MARCAR ENTRADA.
         *
         * Esto evita intentar registrar
         * una segunda entrada.
         */
        btnEntrada.setEnabled(
                false
        );


        /*
         * MARCAR SALIDA solamente estará
         * habilitado si todavía no existe
         * una hora de salida.
         *
         * salida == null
         *
         * significa que todavía no ha
         * terminado su jornada.
         */
        btnSalida.setEnabled(
                salida == null
        );
    }
}