package Vista;

// Importamos la clase Usuario para poder crear un usuario
import Model.Usuario;

// Importamos la anotación @Test de JUnit
import org.junit.jupiter.api.Test;

// Importamos las funciones para realizar comprobaciones en las pruebas
import static org.junit.jupiter.api.Assertions.*;

public class ReportesViewTest {

    // Indicamos que este método corresponde a una prueba de JUnit
    @Test
    void probarCreacionReportes() {

        // Creamos un nuevo objeto de tipo Usuario
        Usuario usuario = new Usuario();

        // Asignamos el nombre del usuario
        usuario.setNombre("Administrador");

        // Asignamos el correo del usuario
        usuario.setCorreo("admin@gmail.com");

        // Asignamos el rol del usuario.
        // En este caso, el ID 1 corresponde al administrador.
        usuario.setId_rol(1);

        // Creamos una nueva ventana de ReportesView
        // y le pasamos el usuario creado anteriormente.
        ReportesView reportes = new ReportesView(usuario);

        // Comprobamos que la ventana ReportesView
        // se haya creado correctamente y que no sea null.
        assertNotNull(reportes);
    }
}