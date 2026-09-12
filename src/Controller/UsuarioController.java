package Controller;

import Dao.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public ArrayList<String> listaUsuarios() {
        return usuarioDAO.obtenerListaUsuarios();
    }

    public void insertar(String a, String b, String c, int d, Boolean e){
        if (usuarioDAO.insertar(a, b, c, d, e)) {
            System.out.println("Usuario registrado con éxito");
        }
    }

    public void actualizar(int a, String b, String c, String d, int e, Boolean f){
        if (usuarioDAO.actualizar(a, b, c, d, e, f)) {
            System.out.println("Usuario actualizado con éxito");
        }
    }

    public void actualizar_sin(int a, String b, String c, int e, Boolean f){
        if (usuarioDAO.actualizar_sin(a, b, c, e, f)) {
            System.out.println("Usuario actualizado con éxito manteniendo pass");
        }
    }

    public ArrayList<String> IdxNombre(String a) {
        return usuarioDAO.TodoxNombre(a);
    }

    public void eliminar(int a){
        if (usuarioDAO.eliminar(a)) {
            System.out.println("Usuario eliminado");
        }
    }
}
