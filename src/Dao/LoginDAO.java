package Dao;

import Conexion.ConexionBD;
import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public Usuario validarCredenciales(String correo, String contrasena) {

        String sql = "SELECT * FROM usuarios " +
                "WHERE correo = ? AND contrasena = SHA2(?,256) AND estado = TRUE";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {


                Usuario usuario = new Usuario();


                usuario.setIdUsuario(
                        rs.getInt("id_usuario")
                );


                usuario.setNombre(
                        rs.getString("nombre")
                );


                usuario.setCorreo(
                        rs.getString("correo")
                );


                usuario.setId_rol(
                        rs.getInt("id_rol")
                );


                usuario.setEstado(
                        rs.getBoolean("estado")
                );


                return usuario;

            }


            return null;

        } catch (SQLException e) {
            System.out.println("Error al validar las credenciales: " + e.getMessage());
            return null;
        }
    }
}