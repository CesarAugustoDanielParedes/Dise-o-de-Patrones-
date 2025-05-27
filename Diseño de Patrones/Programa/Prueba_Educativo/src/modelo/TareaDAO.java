/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

public class TareaDAO {
    public boolean registrarTarea(Tarea tarea) {
        try (Connection con = conexionJava_SQLServer.getInstancia().getConexion()) {
            String sql = "INSERT INTO Tarea (nombre, fecha_limite, curso_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tarea.getNombre());
            stmt.setDate(2, Date.valueOf(tarea.getFechaLimite()));
            stmt.setInt(3, tarea.getCursoId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error registrando tarea: " + e.getMessage());
            return false;
        }
    }
}
