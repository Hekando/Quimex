package Model;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private int id_rol;
    private boolean estado;

    public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}

    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public int getId_rol() {return id_rol;}
    public void setId_rol(int id_rol) {this.id_rol = id_rol;}

    public boolean isEstado() {return estado;}
    public void setEstado(boolean estado) {this.estado = estado;}

    @Override
    public String toString() {
        return nombre + " - " + id_rol;
    }
}