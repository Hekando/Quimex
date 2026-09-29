package Controller;

import Dao.AsistenciaDAO;
import java.util.ArrayList;

public class ReporteController {

    private AsistenciaDAO asistenciaDAO;

    public ReporteController() {
        asistenciaDAO = new AsistenciaDAO();
    }

    public ArrayList<Object[]> reporteAtrasos(String fecha) {
        return asistenciaDAO.obtenerAtrasos(fecha);
    }

    public ArrayList<Object[]> reporteSalidasAnticipadas(String fecha) {
        return asistenciaDAO.obtenerSalidasAnticipadas(fecha);
    }

    public ArrayList<Object[]> reporteInasistencias(String fecha) {
        return asistenciaDAO.obtenerInasistencias(fecha);
    }
}