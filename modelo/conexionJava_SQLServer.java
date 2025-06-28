
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionJava_SQLServer {

    // --- DATOS PARA LA CONEXIÓN ---
    // ❗ IMPORTANTE: Modifica estos valores con los de tu base de datos ❗
    private static final String HOST = "localhost"; // o la IP de tu servidor
    private static final String PORT = "1433";      // Puerto por defecto de SQL Server
    private static final String DB_NAME = "RutaClass"; // El nombre de tu base de datos
    private static final String USER = "user06"; // Tu usuario de SQL Server (ej. "sa")
    private static final String PASSWORD = "root3"; // Tu contraseña

    // Objeto Connection que se compartirá
    private Connection conexion;

    // Instancia única de la clase (para el Patrón Singleton)
    private static conexionJava_SQLServer instancia;

    // El constructor es privado para que no se puedan crear objetos desde fuera
    private conexionJava_SQLServer() {
        this.crearConexion();
    }
    
    /**
     * Implementación del patrón Singleton.
     * Devuelve la única instancia de la clase. Si no existe, la crea.
     * Es 'synchronized' para ser seguro en entornos con múltiples hilos.
     * @return Instancia única de la conexión.
     */
    public static synchronized conexionJava_SQLServer getInstance() {
        if (instancia == null) {
            instancia = new conexionJava_SQLServer();
        }
        return instancia;
    }

    /**
     * Método privado que intenta crear la conexión a la base de datos.
     */
    public void crearConexion() {
        try {
            // Construir la URL de conexión completa
            String url = "jdbc:sqlserver://" + HOST + ":" + PORT + ";"
                    + "databaseName=" + DB_NAME + ";"
                    + "encrypt=true;trustServerCertificate=true;";

            // Establecer la conexión usando el DriverManager
            this.conexion = DriverManager.getConnection(url, USER, PASSWORD);

        } catch (SQLException e) {
            // Si algo sale mal, imprime el error detallado en la consola.
            // Esto es fundamental para saber qué falló (login, firewall, etc.).
            System.err.println("ERROR AL CONECTAR CON LA BASE DE DATOS:");
            e.printStackTrace();
            this.conexion = null; // Se deja la conexión como nula si hubo un error
        }
    }

    /**
     * Devuelve el objeto Connection.
     * @return El objeto Connection o null si la conexión falló.
     */
    public Connection getConexion() {
        return this.conexion;
    }
}