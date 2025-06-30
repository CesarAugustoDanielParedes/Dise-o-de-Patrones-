package modelo;

import java.sql.Connection;

/**
 * @author Jeremi Alexander
 */
public class TestConexion {

    /*Pruebas de conexion*/
    public static void main(String[] args) {
        
        // Obtenemos la instancia única de nuestra clase de conexión
        conexionJava_SQLServer conexionBD = conexionJava_SQLServer.getInstance();
        
        // Pedimos el objeto Connection
        Connection cxn = conexionBD.getConexion();

        // Verificamos si la conexión fue exitosa
        if (cxn != null) {
            System.out.println("Conexion obtenida correctamente");
            System.out.println("Prueba finalizada con exito");
        } else {
            System.out.println("No se pudo establecer conexión. Revisa la consola para ver los errores detallados.");
        }
    }
}