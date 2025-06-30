
import modelo.Usuario;
import modelo.UsuarioFactory;
import modelo.UsuarioService;
import modelo.UsuarioServiceProxy;



public class UsuarioController {
   
    private final UsuarioService service = new UsuarioServiceProxy();

    public void registrar(String tipo, String nombres, String apellidos, String dni, String correo) {
        try {
            // 1) Crear modelo con Factory
            Usuario u = UsuarioFactory.createUsuario(tipo);
            u.setNombres(nombres);
            u.setApellidos(apellidos);
            u.setDni(dni);
            u.setCorreoInstitucional(correo);
            // … rellenar campos específicos de Alumno/Docente …

            // 2) Registrar vía Proxy → Facade → Stored Procedures
            int id = service.registrar(u);
            System.out.println("Nuevo usuario con ID=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}