/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jeremi Alexander
 */

package modelo;

import java.sql.*;

public class NotaDAO {
    public boolean registrarNota(Nota nota) {
        try (Connection con = conexionJava_SQLServer.getInstancia().getConexion()) {
            String sql = "INSERT INTO Nota (estudiante_id, curso_id, nota) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, nota.getEstudianteId());
            stmt.setInt(2, nota.getCursoId());
            stmt.setBigDecimal(3, nota.getNota());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error registrando nota: " + e.getMessage());
            return false;
        }
    }
}
