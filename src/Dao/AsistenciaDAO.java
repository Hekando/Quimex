package Dao;

// Clase que permite obtener la conexión con MariaDB
import Conexion.ConexionBD;

// Modelo que representa una asistencia
import Model.Asistencia;

// Clases necesarias para trabajar con SQL
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

// Clases para trabajar con fecha y hora en Java
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/*
 * ============================================================
 * DAO DE ASISTENCIA
 * ============================================================
 *
 * DAO significa Data Access Object.
 *
 * Esta clase es la encargada de comunicarse directamente
 * con la base de datos para realizar las operaciones
 * relacionadas con la asistencia de los trabajadores.
 *
 * Sus funciones principales son:
 *
 * 1. Registrar la hora de entrada.
 * 2. Registrar la hora de salida.
 * 3. Consultar la asistencia del usuario del día actual.
 *
 * La interfaz gráfica NO ejecuta directamente las consultas SQL.
 * El flujo utilizado es:
 *
 * AsistenciaView
 *      ↓
 * AsistenciaController
 *      ↓
 * AsistenciaDAO
 *      ↓
 * MariaDB
 *
 * ============================================================
 */

public class AsistenciaDAO {


    // =========================================================
    // REGISTRAR ENTRADA
    // =========================================================

    /*
     * Este método registra la entrada de un trabajador.
     *
     * Recibe como parámetro el ID del usuario que inició sesión.
     *
     * Antes de registrar la entrada, comprueba que el trabajador
     * no tenga ya una asistencia registrada durante el día actual.
     *
     * Retorna:
     *
     * true  = entrada registrada correctamente.
     * false = no se pudo registrar o ya existía una entrada.
     */

    public boolean registrarEntrada(int idUsuario) {


        /*
         * Primera consulta:
         *
         * Busca si ya existe una asistencia para:
         *
         * - El usuario actual.
         * - La fecha actual.
         *
         * Esto evita que un trabajador registre dos entradas
         * durante el mismo día.
         */

        String verificar = """
                SELECT id_asistencia
                FROM asistencias
                WHERE id_usuario = ? AND fecha = ?
                """;


        /*
         * Segunda consulta:
         *
         * Si todavía no existe asistencia, se crea un nuevo
         * registro almacenando:
         *
         * - ID del usuario.
         * - Fecha.
         * - Hora de entrada.
         */

        String insertar = """
                INSERT INTO asistencias
                (id_usuario, fecha, hora_entrada)
                VALUES (?, ?, ?)
                """;


        /*
         * Obtenemos automáticamente la fecha y hora
         * actuales del computador.
         */

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();


        /*
         * Abrimos una conexión con MariaDB.
         *
         * Se utiliza try-with-resources para que Java
         * cierre automáticamente la conexión cuando
         * termine la operación.
         */

        try (Connection conexion = ConexionBD.getConexion()) {


            // =================================================
            // VERIFICAR SI YA EXISTE UNA ENTRADA
            // =================================================

            try (PreparedStatement ps =
                         conexion.prepareStatement(verificar)) {


                /*
                 * Reemplazamos el primer signo ?
                 * de la consulta por el ID del usuario.
                 */

                ps.setInt(1, idUsuario);


                /*
                 * Reemplazamos el segundo signo ?
                 * por la fecha actual.
                 */

                ps.setDate(
                        2,
                        Date.valueOf(fechaActual)
                );


                /*
                 * Ejecutamos el SELECT.
                 *
                 * ResultSet contiene los registros encontrados
                 * en la base de datos.
                 */

                try (ResultSet rs = ps.executeQuery()) {


                    /*
                     * Si rs.next() es verdadero significa
                     * que encontramos una asistencia del
                     * usuario para el día actual.
                     */

                    if (rs.next()) {

                        System.out.println(
                                "El trabajador ya registró su entrada hoy."
                        );


                        /*
                         * Retornamos false porque no permitimos
                         * registrar una segunda entrada.
                         */

                        return false;
                    }
                }
            }


            // =================================================
            // REGISTRAR LA ENTRADA
            // =================================================

            /*
             * Si llegamos hasta aquí significa que el trabajador
             * todavía no tiene una entrada registrada hoy.
             */

            try (PreparedStatement ps =
                         conexion.prepareStatement(insertar)) {


                // Guardamos el ID del trabajador.
                ps.setInt(
                        1,
                        idUsuario
                );


                // Guardamos la fecha actual.
                ps.setDate(
                        2,
                        Date.valueOf(fechaActual)
                );


                // Guardamos la hora actual como hora de entrada.
                ps.setTime(
                        3,
                        Time.valueOf(horaActual)
                );


                /*
                 * executeUpdate() devuelve la cantidad
                 * de filas afectadas.
                 *
                 * Si es mayor que 0 significa que el INSERT
                 * se realizó correctamente.
                 */

                return ps.executeUpdate() > 0;
            }


        } catch (SQLException e) {


            /*
             * Si ocurre un problema con MariaDB o con
             * la consulta SQL mostramos el error.
             */

            System.out.println(
                    "Error al registrar entrada: "
                            + e.getMessage()
            );


            return false;
        }
    }



    // =========================================================
    // REGISTRAR SALIDA
    // =========================================================

    /*
     * Este método registra la salida del trabajador.
     *
     * A diferencia de la entrada, aquí NO hacemos un INSERT.
     *
     * La asistencia ya fue creada cuando el trabajador
     * marcó su entrada.
     *
     * Por eso utilizamos UPDATE para agregar la hora
     * de salida al mismo registro.
     */

    public boolean registrarSalida(int idUsuario) {


        /*
         * Actualizamos hora_salida solamente cuando:
         *
         * - Corresponde al usuario.
         * - Corresponde al día actual.
         * - Todavía no existe una hora de salida.
         *
         * "hora_salida IS NULL" evita registrar dos salidas.
         */

        String sql = """
                UPDATE asistencias
                SET hora_salida = ?
                WHERE id_usuario = ?
                AND fecha = ?
                AND hora_salida IS NULL
                """;


        // Obtenemos fecha y hora actuales.
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();


        /*
         * Abrimos la conexión y preparamos la consulta.
         */

        try (Connection conexion = ConexionBD.getConexion();

             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {


            // Primer ? = hora de salida.
            ps.setTime(
                    1,
                    Time.valueOf(horaActual)
            );


            // Segundo ? = ID del trabajador.
            ps.setInt(
                    2,
                    idUsuario
            );


            // Tercer ? = fecha actual.
            ps.setDate(
                    3,
                    Date.valueOf(fechaActual)
            );


            /*
             * Ejecutamos el UPDATE.
             *
             * Si se modificó una fila retornamos true.
             *
             * Si no se modificó ninguna fila retorna false.
             */

            return ps.executeUpdate() > 0;


        } catch (SQLException e) {


            // Mostramos el error si ocurre un problema.

            System.out.println(
                    "Error al registrar salida: "
                            + e.getMessage()
            );


            return false;
        }
    }



    // =========================================================
    // BUSCAR ASISTENCIA DEL DÍA ACTUAL
    // =========================================================

    /*
     * Este método busca la asistencia del usuario
     * correspondiente al día actual.
     *
     * Es utilizado por la interfaz para mostrar:
     *
     * - Fecha.
     * - Hora de entrada.
     * - Hora de salida.
     * - Estado de la jornada.
     */

    public Asistencia buscarAsistenciaHoy(int idUsuario) {


        /*
         * Buscamos la asistencia utilizando:
         *
         * - ID del usuario.
         * - Fecha actual.
         */

        String sql = """
                SELECT *
                FROM asistencias
                WHERE id_usuario = ?
                AND fecha = ?
                """;


        /*
         * Abrimos conexión con MariaDB.
         */

        try (Connection conexion = ConexionBD.getConexion();

             PreparedStatement ps =
                     conexion.prepareStatement(sql)) {


            // Primer ? = ID del usuario.
            ps.setInt(
                    1,
                    idUsuario
            );


            // Segundo ? = fecha actual.
            ps.setDate(
                    2,
                    Date.valueOf(LocalDate.now())
            );


            /*
             * Ejecutamos el SELECT.
             */

            try (ResultSet rs = ps.executeQuery()) {


                /*
                 * Si encontramos un registro,
                 * creamos un objeto Asistencia.
                 */

                if (rs.next()) {


                    Asistencia asistencia =
                            new Asistencia();


                    // Obtenemos el ID de la asistencia.
                    asistencia.setIdAsistencia(
                            rs.getInt(
                                    "id_asistencia"
                            )
                    );


                    // Obtenemos el ID del trabajador.
                    asistencia.setIdUsuario(
                            rs.getInt(
                                    "id_usuario"
                            )
                    );


                    // Obtenemos la fecha.
                    asistencia.setFecha(
                            rs.getDate(
                                    "fecha"
                            ).toLocalDate()
                    );


                    /*
                     * Recuperamos la hora de entrada.
                     */

                    Time entrada =
                            rs.getTime(
                                    "hora_entrada"
                            );


                    /*
                     * Comprobamos que la hora no sea NULL
                     * antes de convertirla a LocalTime.
                     */

                    if (entrada != null) {

                        asistencia.setHoraEntrada(
                                entrada.toLocalTime()
                        );
                    }


                    /*
                     * Recuperamos la hora de salida.
                     */

                    Time salida =
                            rs.getTime(
                                    "hora_salida"
                            );


                    /*
                     * Si existe una salida, la convertimos
                     * de java.sql.Time a LocalTime.
                     */

                    if (salida != null) {

                        asistencia.setHoraSalida(
                                salida.toLocalTime()
                        );
                    }


                    /*
                     * Retornamos el objeto con toda
                     * la información encontrada.
                     */

                    return asistencia;
                }
            }


        } catch (SQLException e) {


            /*
             * Mostramos el error si falla la consulta.
             */

            System.out.println(
                    "Error al consultar asistencia: "
                            + e.getMessage()
            );
        }


        /*
         * Si no existe asistencia del usuario
         * para el día actual retornamos null.
         */

        return null;
    }

    // =========================
    // REPORTE DE ATRASOS
    // =========================

    public ArrayList<Object[]> obtenerAtrasos(String fecha) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT u.id_usuario,
                       u.nombre,
                       a.fecha,
                       a.hora_entrada
                FROM asistencias a
                INNER JOIN usuarios u
                    ON a.id_usuario = u.id_usuario
                WHERE a.fecha = ?
                  AND a.hora_entrada > '09:30:00'
                ORDER BY a.hora_entrada
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getDate("fecha"),
                            rs.getTime("hora_entrada")
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al obtener reporte de atrasos: "
                            + e.getMessage()
            );
        }

        return lista;
    }


    // =========================
    // REPORTE DE SALIDAS ANTICIPADAS
    // =========================

    public ArrayList<Object[]> obtenerSalidasAnticipadas(String fecha) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT u.id_usuario,
                       u.nombre,
                       a.fecha,
                       a.hora_salida
                FROM asistencias a
                INNER JOIN usuarios u
                    ON a.id_usuario = u.id_usuario
                WHERE a.fecha = ?
                  AND a.hora_salida IS NOT NULL
                  AND a.hora_salida < '17:30:00'
                ORDER BY a.hora_salida
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getDate("fecha"),
                            rs.getTime("hora_salida")
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al obtener reporte de salidas anticipadas: "
                            + e.getMessage()
            );
        }

        return lista;
    }


    // =========================
    // REPORTE DE INASISTENCIAS
    // =========================

    public ArrayList<Object[]> obtenerInasistencias(String fecha) {

        ArrayList<Object[]> lista = new ArrayList<>();

        String sql = """
                SELECT u.id_usuario,
                       u.nombre
                FROM usuarios u
                LEFT JOIN asistencias a
                    ON u.id_usuario = a.id_usuario
                    AND a.fecha = ?
                WHERE a.id_asistencia IS NULL
                  AND u.estado = TRUE
                ORDER BY u.nombre
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Object[] fila = {
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            fecha
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al obtener reporte de inasistencias: "
                            + e.getMessage()
            );
        }

        return lista;
    }


}