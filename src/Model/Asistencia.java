package Model;

// LocalDate permite trabajar con fechas.
// Ejemplo: 26/09/2026.
import java.time.LocalDate;

// LocalTime permite trabajar con horas.
// Ejemplo: 09:30:00.
import java.time.LocalTime;


/*
 * ============================================================
 * MODELO: ASISTENCIA
 * ============================================================
 *
 * Esta clase representa una asistencia de un trabajador.
 *
 * Su función principal es almacenar temporalmente en Java
 * los datos que corresponden a un registro de la tabla
 * "asistencias" de la base de datos.
 *
 * Un registro contiene:
 *
 * - ID de la asistencia.
 * - ID del usuario.
 * - Fecha.
 * - Hora de entrada.
 * - Hora de salida.
 *
 * Esta clase NO realiza consultas SQL.
 * Tampoco se conecta directamente con MariaDB.
 *
 * Solamente representa y transporta los datos de una asistencia.
 *
 * Ejemplo:
 *
 * idAsistencia = 15
 * idUsuario    = 3
 * fecha        = 26/09/2026
 * horaEntrada  = 09:25:00
 * horaSalida   = 17:35:00
 *
 * ============================================================
 */

public class Asistencia {


    // ========================================================
    // ATRIBUTOS
    // ========================================================

    /*
     * Identificador único del registro de asistencia.
     *
     * Corresponde al campo "id_asistencia"
     * de la tabla asistencias en MariaDB.
     */
    private int idAsistencia;


    /*
     * Identificador del trabajador al que pertenece
     * esta asistencia.
     *
     * Permite relacionar la asistencia con un usuario.
     *
     * Corresponde al campo "id_usuario"
     * de la base de datos.
     */
    private int idUsuario;


    /*
     * Fecha en la que el trabajador registra
     * su asistencia.
     *
     * Utilizamos LocalDate porque solamente
     * necesitamos almacenar una fecha.
     *
     * Ejemplo:
     * 2026-09-26
     */
    private LocalDate fecha;


    /*
     * Hora en la que el trabajador
     * registra su entrada.
     *
     * Utilizamos LocalTime porque solamente
     * necesitamos trabajar con horas.
     *
     * Ejemplo:
     * 09:25:00
     */
    private LocalTime horaEntrada;


    /*
     * Hora en la que el trabajador
     * registra su salida.
     *
     * Mientras el trabajador todavía se encuentre
     * trabajando, este valor puede ser null.
     *
     * Ejemplo:
     * 17:35:00
     */
    private LocalTime horaSalida;



    // ========================================================
    // CONSTRUCTOR VACÍO
    // ========================================================

    /*
     * Constructor sin parámetros.
     *
     * Permite crear primero un objeto Asistencia vacío
     * y posteriormente asignarle sus datos utilizando
     * los métodos set.
     *
     * Ejemplo:
     *
     * Asistencia asistencia = new Asistencia();
     *
     * asistencia.setIdUsuario(3);
     * asistencia.setFecha(LocalDate.now());
     *
     * Este constructor es utilizado, por ejemplo,
     * cuando AsistenciaDAO obtiene información
     * desde la base de datos.
     */
    public Asistencia() {
    }



    // ========================================================
    // CONSTRUCTOR CON PARÁMETROS
    // ========================================================

    /*
     * Este constructor permite crear una asistencia
     * entregando todos sus datos inmediatamente.
     *
     * Recibe:
     *
     * idAsistencia = identificador de la asistencia.
     * idUsuario    = identificador del trabajador.
     * fecha        = fecha de la asistencia.
     * horaEntrada  = hora de entrada.
     * horaSalida   = hora de salida.
     */
    public Asistencia(
            int idAsistencia,
            int idUsuario,
            LocalDate fecha,
            LocalTime horaEntrada,
            LocalTime horaSalida) {

        /*
         * "this" hace referencia al atributo
         * perteneciente al objeto actual.
         *
         * Por ejemplo:
         *
         * this.idUsuario
         *
         * es el atributo de la clase.
         *
         * Mientras que:
         *
         * idUsuario
         *
         * es el parámetro recibido por el constructor.
         */

        this.idAsistencia = idAsistencia;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }



    // ========================================================
    // GET Y SET DE ID ASISTENCIA
    // ========================================================

    /*
     * GET:
     *
     * Permite obtener el ID de la asistencia.
     */
    public int getIdAsistencia() {
        return idAsistencia;
    }


    /*
     * SET:
     *
     * Permite asignar o modificar
     * el ID de la asistencia.
     */
    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }



    // ========================================================
    // GET Y SET DE ID USUARIO
    // ========================================================

    /*
     * Retorna el ID del trabajador
     * asociado a esta asistencia.
     */
    public int getIdUsuario() {
        return idUsuario;
    }


    /*
     * Permite asignar el ID del trabajador.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }



    // ========================================================
    // GET Y SET DE FECHA
    // ========================================================

    /*
     * Retorna la fecha correspondiente
     * a esta asistencia.
     */
    public LocalDate getFecha() {
        return fecha;
    }


    /*
     * Permite asignar la fecha
     * de la asistencia.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }



    // ========================================================
    // GET Y SET DE HORA DE ENTRADA
    // ========================================================

    /*
     * Retorna la hora en la que
     * el trabajador registró su entrada.
     */
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }


    /*
     * Permite asignar la hora
     * de entrada del trabajador.
     */
    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }



    // ========================================================
    // GET Y SET DE HORA DE SALIDA
    // ========================================================

    /*
     * Retorna la hora de salida.
     *
     * Si todavía no se registró una salida,
     * este valor puede ser null.
     */
    public LocalTime getHoraSalida() {
        return horaSalida;
    }


    /*
     * Permite asignar la hora
     * de salida del trabajador.
     */
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}