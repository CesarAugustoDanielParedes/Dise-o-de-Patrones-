/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import modelo.conexionJava_SQLServer;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Jeremi Alexander
 */
public class testConexion {

    /*Pruebas de conexion*/
    public static void main(String[] args) {
        conexionJava_SQLServer conexionBD = conexionJava_SQLServer.getInstancia();
        Connection cxn = conexionBD.getConexion();

        //Connection cxn = conexionJava_SQLServer.getInstancia().getConexion();
        
        if (cxn != null) {
            System.out.println("Conexion obtenida correctamente!");
        } else {
            System.out.println("No se pudo establecer conexion.");
        }

    }

}
