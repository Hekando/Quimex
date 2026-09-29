package Controller;

import Dao.AsistenciaDAO;
import Model.Asistencia;

import java.time.LocalTime;

public class AsistenciaController {

    private final AsistenciaDAO asistenciaDAO;

    public AsistenciaController() {
        asistenciaDAO = new AsistenciaDAO();
    }

    // Registrar entrada
    public boolean registrarEntrada(int idUsuario) {
        return asistenciaDAO.registrarEntrada(idUsuario);
    }

    // Registrar salida
    public boolean registrarSalida(int idUsuario) {
        return asistenciaDAO.registrarSalida(idUsuario);
    }

    // Consultar asistencia de hoy
    public Asistencia buscarAsistenciaHoy(int idUsuario) {
        return asistenciaDAO.buscarAsistenciaHoy(idUsuario);
    }

    // Determinar si la entrada fue a tiempo o atrasada
    public String determinarEstadoEntrada(LocalTime horaEntrada) {

        LocalTime limiteEntrada = LocalTime.of(9, 30);

        if (horaEntrada.isAfter(limiteEntrada)) {
            return "ENTRADA ATRASADA";
        }

        return "ENTRADA A TIEMPO";
    }

    // Determinar si la salida fue normal o anticipada
    public String determinarEstadoSalida(LocalTime horaSalida) {

        LocalTime limiteSalida = LocalTime.of(17, 30);

        if (horaSalida.isBefore(limiteSalida)) {
            return "SALIDA ANTICIPADA";
        }

        return "SALIDA A TIEMPO";
    }
}