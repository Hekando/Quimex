package Controller;

import Dao.AsistenciaDAO;
import Model.Asistencia;

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
}