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
            System.out.print("Usuario registrado con éxito");
        }
    }
    
    
    
}
