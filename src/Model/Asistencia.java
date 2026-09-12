package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Asistencia {

    private int idAsistencia;
    private int idUsuario;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, int idUsuario, LocalDate fecha,
                      LocalTime horaEntrada, LocalTime horaSalida) {

        this.idAsistencia = idAsistencia;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}