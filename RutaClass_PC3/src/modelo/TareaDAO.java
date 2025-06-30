/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

// Clase DAO para Tarea

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TareaDAO {
    public void asignarTarea(Tarea tarea) {
        String sql = "{call AsignarTarea(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = conexionJava_SQLServer.getInstance().getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, tarea.getIdTarea());
            cs.setString(2, tarea.getTitulo());
            cs.setString(3, tarea.getDescripcion());
            cs.setString(4, tarea.getFechaEntrega());
            cs.setString(5, tarea.getTipoArchivo());
            cs.setInt(6, tarea.getIdCurso());
            cs.setInt(7, tarea.getIdDocente());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Tarea asignada correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}