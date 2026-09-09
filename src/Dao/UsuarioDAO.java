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
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, id_rol, estado) VALUES (?, ?, ?, ?, ?)";
        
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

    
    
    
    
    
    
    
    
    
    // 2. READ: Leer y listar todos los registros
    /*public List<String[]> listar() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                String[] usuario = new String[3];
                usuario[0] = String.valueOf(rs.getInt("id"));
                usuario[1] = rs.getString("nombre");
                usuario[2] = rs.getString("email");
                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }*/

    // 3. UPDATE: Modificar un registro existente
    /*public boolean actualizar(int id, String nombre, String email) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }*/

    // 4. DELETE: Eliminar un registro
    /*public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }*/
    
    

}
