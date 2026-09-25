package Dao;
import java.util.ArrayList;
import Dao.UsuarioDAO;

public class UsuarioDAOSimulado extends UsuarioDAO{

    @Override
    public ArrayList<String> obtenerListaUsuarios() {
        ArrayList<String> listaFalsa = new ArrayList<>();
        listaFalsa.add("Marcelo");
        listaFalsa.add("Juan");
        return listaFalsa;
    }

    @Override
    public ArrayList<String> TodoxNombre(String n) {
        ArrayList<String> datosUsuarioFalso = new ArrayList<>();
        if ("Marcelo".equals(n)) {
            datosUsuarioFalso.add("1");           // id_usuario
            datosUsuarioFalso.add("Marcelo");     // nombre
            datosUsuarioFalso.add("m@gmail.com"); // correo
        }
        return datosUsuarioFalso;
    }
}
