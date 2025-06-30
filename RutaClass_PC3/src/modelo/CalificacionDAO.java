/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

// Clase DAO para Calificación

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CalificacionDAO {
    public void calificarEstudiante(Calificacion c) {
        String sql = "{call CalificarEstudiante(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = conexionJava_SQLServer.getInstance().getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, c.getIdCurso());
            cs.setInt(2, c.getIdEstudiante());
            cs.setString(3, c.getIdTarea());
            cs.setString(4, c.getFechaEntrega()); // corregido
            cs.setDouble(5, c.getNota());
            cs.setString(6, c.getObservacion());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Calificación registrada correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}