package Dao;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean validarCredenciales(String correo, String contrasena) {

        String sql = "SELECT * FROM usuarios " +
                "WHERE correo = ? AND contrasena = ? AND estado = 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al validar las credenciales: " + e.getMessage());
            return false;
        }
    }
}