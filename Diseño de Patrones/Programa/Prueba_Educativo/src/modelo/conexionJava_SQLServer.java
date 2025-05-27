/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jeremi Alexander
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionJava_SQLServer {

    private static conexionJava_SQLServer instancia;
    private Connection conexion;

    // Cambia estos datos según tu entorno
    private final 
            String url = "jdbc:sqlserver://localhost:1433;databaseName=EducacionDistancia;encrypt=true;trustServerCertificate=true";
    private final String usuario = "user06"; // Usuario SQL Server
    private final String clave = "root3"; // Contraseña

    private conexionJava_SQLServer() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url, usuario,clave);
            System.out.println("Conexion exitosa a SQL Server");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos SQL Server: " + e.getMessage());
        }
    }

    public static conexionJava_SQLServer getInstancia() {
        if (instancia == null) {
            instancia = new conexionJava_SQLServer();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

   public void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        instancia = null;
    }

    public void commit() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.commit();
            }
        } catch (SQLException e) {
            System.err.println("Error al hacer commit: " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.rollback();
            }
        } catch (SQLException e) {
            System.err.println("Error al hacer rollback: " + e.getMessage());
        }
    }

    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
