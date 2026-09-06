package Vista;
import Conexion.ConexionBD;
import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        // Verifica que la conexión a la base de datos funcione correctamente
        ConexionBD.testConexion();

        // Se crea la ventana de login
        Login login = new Login();

        // Se hace visible la ventana
        login.setVisible(true);
        }
    }