package Dao;

import Conexion.ConexionBD;
import Model.Usuario;
//import java.awt.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<String> obtenerListaUsuarios() {
        ArrayList<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM usuarios";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String u = rs.getString("nombre");
                nombres.add(u);
            }
            return nombres;
        } catch (SQLException e) {
            System.out.println("error obtener lista " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(String nombre, String email, String contraseña, int rol, Boolean estado) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, id_rol, estado) VALUES (?, ?, SHA2(?,256), ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, contraseña);
            ps.setInt(4, rol);
            ps.setBoolean(5, estado);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(int id, String nombre, String email, String contrasena, int idRol, Boolean estado) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, contrasena = SHA2(?,256), id_rol = ?, estado = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, contrasena);
            ps.setInt(4, idRol);
            ps.setBoolean(5, estado);
            ps.setInt(6, id);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar_sin(int id, String nombre, String email, int idRol, Boolean estado) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, id_rol = ?, estado = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setInt(3, idRol);
            ps.setBoolean(4, estado);
            ps.setInt(5, id);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<String> TodoxNombre(String n) {
        ArrayList<String> user = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, n);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String a;
                    a = String.valueOf(rs.getInt("id_usuario"));
                    user.add(a);
                    a = rs.getString("nombre");
                    user.add(a);
                    a = rs.getString("correo");
                    user.add(a);
                    a = rs.getString("contrasena");
                    user.add(a);
                    a = rs.getString("id_rol");
                    user.add(a);
                    a = rs.getString("estado");
                    user.add(a);
                }
            }
            return user;
        } catch (SQLException e) {
            System.out.println("error obtener lista " + e.getMessage());
            return null;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
