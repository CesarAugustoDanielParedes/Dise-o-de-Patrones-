/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CursoDAO {
    public void crearCurso(Curso curso) {
        String sql = "{call CrearCurso(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = conexionJava_SQLServer.getInstance().getConexion();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, curso.getCodigo());
            cs.setString(2, curso.getNombre());
            cs.setInt(3, curso.getCreditos());
            cs.setInt(4, curso.getIdDocente());
            cs.setString(5, curso.getDescripcion());
            cs.setString(6, curso.getEstado());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Curso creado correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}