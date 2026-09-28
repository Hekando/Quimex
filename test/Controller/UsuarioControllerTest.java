package Controller;

import Dao.UsuarioDAOSimulado;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class UsuarioControllerTest {

    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() throws Exception {
        // 1. Instanciamos el controlador normal
        usuarioController = new UsuarioController();

        // 2. Instanciamos nuestro simulador manual nativo
        UsuarioDAOSimulado daoSimulado = new UsuarioDAOSimulado();

        // 3. Truco de reflexión: Reemplazamos el "usuarioDAO" interno por nuestro simulador
        Field field = UsuarioController.class.getDeclaredField("usuarioDAO");
        field.setAccessible(true);
        field.set(usuarioController, daoSimulado);
    }

    // TEST 1: Comprobar que el controlador retorne correctamente la lista
    @Test
    public void testListaUsuariosExitoso() {
        // Act: Invocamos el método del controlador
        ArrayList<String> resultado = usuarioController.listaUsuarios();

        // Assert: Validamos que los datos devueltos coincidan
        assertNotNull(resultado, "La lista no debería ser nula");
        assertEquals(2, resultado.size(), "Debería retornar 2 usuarios");
        assertEquals("Marcelo", resultado.get(0), "El primer usuario debe ser Marcelo");
        System.out.println("Test1 realizado con exito");
    }

    // TEST 2: Validar que busque correctamente por nombre
    @Test
    public void testIdxNombreDebeRetornarDatosDeUsuario() {
        // Act: Ejecutamos el flujo del controlador
        ArrayList<String> resultado = usuarioController.IdxNombre("Marcelo");

        // Assert: Comprobamos que el ID y el Correo se mantengan intactos
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals("1", resultado.get(0), "El ID devuelto debe ser 1");
        assertEquals("m@gmail.com", resultado.get(2), "El correo electrónico debe ser m@gmail.com");
        System.out.println("Test2 realizado con exito");
    }
}