package Vista;

import Model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesViewTest {

    @Test
    void probarCreacionReportes() {

        Usuario usuario = new Usuario();

        usuario.setNombre("Administrador");
        usuario.setCorreo("admin@gmail.com");
        usuario.setId_rol(1);

        ReportesView reportes = new ReportesView(usuario);

        assertNotNull(reportes);
    }
}