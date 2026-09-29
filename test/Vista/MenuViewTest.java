package Vista;

import Model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuViewTest {

    // =========================================================
    // PRUEBA 1: INGRESO DE USUARIO ADMINISTRADOR
    // =========================================================

    @Test
    void probarMenuAdministrador() {

        // Se crea un usuario de prueba
        Usuario usuario = new Usuario();

        // Se ingresan los datos del usuario administrador
        usuario.setNombre("Administrador");
        usuario.setCorreo("admin@gmail.com");
        usuario.setId_rol(1);

        System.out.println("----------------------------------------");
        System.out.println("PRUEBA: INGRESO COMO ADMINISTRADOR");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Rol: Administrador");

        // Se crea el menú utilizando el usuario administrador
        MenuView menu = new MenuView(usuario);

        // Se comprueba que el menú fue creado correctamente
        assertNotNull(menu);

        System.out.println("Resultado: ingreso como administrador correcto");
        System.out.println("----------------------------------------");

        // Se cierra la ventana después de la prueba
        menu.dispose();
    }


    // =========================================================
    // PRUEBA 2: INGRESO DE USUARIO EMPLEADO
    // =========================================================

    @Test
    void probarMenuEmpleado() {

        // Se crea un usuario de prueba
        Usuario usuario = new Usuario();

        // Se ingresan los datos del usuario empleado
        usuario.setNombre("Empleado");
        usuario.setCorreo("empleado@gmail.com");
        usuario.setId_rol(2);

        System.out.println("----------------------------------------");
        System.out.println("PRUEBA: INGRESO COMO EMPLEADO");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Rol: Empleado");

        // Se crea el menú utilizando el usuario empleado
        MenuView menu = new MenuView(usuario);

        // Se comprueba que el menú fue creado correctamente
        assertNotNull(menu);

        System.out.println("Resultado: ingreso como empleado correcto");
        System.out.println("----------------------------------------");

        // Se cierra la ventana después de la prueba
        menu.dispose();
    }
}