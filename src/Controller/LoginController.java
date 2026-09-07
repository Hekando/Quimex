package Controller;

import Dao.LoginDAO;
import Model.Usuario;

public class LoginController {

    private LoginDAO loginDAO;

    // Constructor que inicializa el acceso a datos de autenticación
    public LoginController() {
        loginDAO = new LoginDAO();
    }

    /**
     * Método que permite autenticar a un usuario en el sistema.
     * Recibe usuario y contrasena, y delega la validación al DAO.
     * Retorna true si las credenciales son correctas, false en caso contrario.
     */
    public Usuario autenticar(String correo, String contrasena) {
        return loginDAO.validarCredenciales(correo, contrasena);
    }
}