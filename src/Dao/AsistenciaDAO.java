package Dao;

import Conexion.ConexionBD;
import Model.Asistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaDAO {

    // Registrar entrada
    public boolean registrarEntrada(int idUsuario) {

        String verificar = """
                SELECT id_asistencia
                FROM asistencias
                WHERE id_usuario = ? AND fecha = ?
                """;

        String insertar = """
                INSERT INTO asistencias
                (id_usuario, fecha, hora_entrada)
                VALUES (?, ?, ?)
                """;

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        try (Connection conexion = ConexionBD.getConexion()) {

            // Verificar si ya existe asistencia de hoy
            try (PreparedStatement ps = conexion.prepareStatement(verificar)) {

                ps.setInt(1, idUsuario);
                ps.setDate(2, Date.valueOf(fechaActual));

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        System.out.println(
                                "El trabajador ya registró su entrada hoy."
                        );
                        return false;
                    }
                }
            }

            // Registrar entrada
            try (PreparedStatement ps = conexion.prepareStatement(insertar)) {

                ps.setInt(1, idUsuario);
                ps.setDate(2, Date.valueOf(fechaActual));
                ps.setTime(3, Time.valueOf(horaActual));

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar entrada: " + e.getMessage()
            );

            return false;
        }
    }

    // Registrar salida
    public boolean registrarSalida(int idUsuario) {

        String sql = """
                UPDATE asistencias
                SET hora_salida = ?
                WHERE id_usuario = ?
                AND fecha = ?
                AND hora_salida IS NULL
                """;

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setTime(1, Time.valueOf(horaActual));
            ps.setInt(2, idUsuario);
            ps.setDate(3, Date.valueOf(fechaActual));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar salida: " + e.getMessage()
            );

            return false;
        }
    }

    // Buscar asistencia del usuario en el día actual
    public Asistencia buscarAsistenciaHoy(int idUsuario) {

        String sql = """
                SELECT *
                FROM asistencias
                WHERE id_usuario = ?
                AND fecha = ?
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setDate(2, Date.valueOf(LocalDate.now()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Asistencia asistencia = new Asistencia();

                    asistencia.setIdAsistencia(
                            rs.getInt("id_asistencia")
                    );

                    asistencia.setIdUsuario(
                            rs.getInt("id_usuario")
                    );

                    asistencia.setFecha(
                            rs.getDate("fecha").toLocalDate()
                    );

                    Time entrada = rs.getTime("hora_entrada");

                    if (entrada != null) {
                        asistencia.setHoraEntrada(
                                entrada.toLocalTime()
                        );
                    }

                    Time salida = rs.getTime("hora_salida");

                    if (salida != null) {
                        asistencia.setHoraSalida(
                                salida.toLocalTime()
                        );
                    }

                    return asistencia;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al consultar asistencia: " + e.getMessage()
            );
        }

        return null;
    }
}