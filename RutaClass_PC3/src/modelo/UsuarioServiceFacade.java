/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import modelo.Usuario;
import modelo.Alumno;
import modelo.Docente;
import modelo.conexionJava_SQLServer;

import java.sql.*;

public class UsuarioServiceFacade {
    private final Connection conn;

    public UsuarioServiceFacade() {
        this.conn = conexionJava_SQLServer.getInstance().getConexion();
    }

    /** Registra Usuario + Alumno/Docente y devuelve el nuevo ID */
    public int registrarUsuarioCompleto(Usuario u) throws SQLException {
        // 1) sp_RegistrarUsuario
        CallableStatement cs = conn.prepareCall("{call sp_RegistrarUsuario(?,?,?,?,?,?,?,?)}");
        cs.setString(1, u.getNombres());
        cs.setString(2, u.getApellidos());
        cs.setString(3, u.getDni());
        cs.setString(4, u.getCorreoInstitucional());
        cs.setDate(5, new java.sql.Date(new java.util.Date().getTime())); // ejemplo
        cs.setString(6, null);
        cs.setString(7, u.getTipoUsuario());
        cs.registerOutParameter(8, Types.INTEGER);
        cs.execute();
        int idGenerado = cs.getInt(8);
        cs.close();

        // 2) según tipo, registrar en Alumno o Docente
        if (u instanceof Alumno) {
            Alumno a = (Alumno) u;
            CallableStatement ca = conn.prepareCall("{call sp_RegistrarAlumno(?,?,?,?,?,?)}");
            ca.setInt(1, idGenerado);
            ca.setString(2, a.getCarreraProfesional());
            ca.setInt(3, a.getCicloActual());
            ca.setString(4, a.getEstadoAlumno());
            ca.setDate(5, new java.sql.Date(a.getFechaIngreso().getTime()));
            ca.setDouble(6, a.getPromedioPonderado());
            ca.execute();
            ca.close();
        } else if (u instanceof Docente) {
            Docente d = (Docente) u;
            CallableStatement cd = conn.prepareCall("{call sp_RegistrarDocente(?,?,?,?,?,?)}");
            cd.setInt(1, idGenerado);
            cd.setString(2, d.getEspecialidad());
            cd.setString(3, d.getGradoAcademico());
            cd.setString(4, d.getTipoContrato());
            cd.setDate(5, new java.sql.Date(d.getFechaIngreso().getTime()));
            cd.setString(6, d.getFacultad());
            cd.execute();
            cd.close();
        }

        return idGenerado;
    }

    /** Obtiene Usuario completo por ID */
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        CallableStatement cs = conn.prepareCall("{call sp_ObtenerUsuarioPorId(?)}");
        cs.setInt(1, id);
        ResultSet rs = cs.executeQuery();
        Usuario u = null;
        if (rs.next()) {
            String tipo = rs.getString("TipoUsuario");
            if ("ALUMNO".equalsIgnoreCase(tipo)) {
                u = new Alumno();
                ((Alumno) u).setCarreraProfesional(rs.getString("CarreraProfesional"));
                ((Alumno) u).setCicloActual(rs.getInt("CicloActual"));
                ((Alumno) u).setEstadoAlumno(rs.getString("EstadoAlumno"));
                ((Alumno) u).setPromedioPonderado(rs.getDouble("PromedioPonderado"));
            } else {
                u = new Docente();
                ((Docente) u).setEspecialidad(rs.getString("Especialidad"));
                ((Docente) u).setGradoAcademico(rs.getString("GradoAcademico"));
                ((Docente) u).setTipoContrato(rs.getString("TipoContrato"));
                ((Docente) u).setFacultad(rs.getString("Facultad"));
            }
            u.setId(rs.getInt("ID_Usuario"));
            u.setNombres(rs.getString("Nombres"));
            u.setApellidos(rs.getString("Apellidos"));
            u.setDni(rs.getString("DNI"));
            u.setCorreoInstitucional(rs.getString("CorreoInstitucional"));
        }
        rs.close();
        cs.close();
        return u;
    }

    // Métodos updateUsuarioCompleto, eliminarUsuario, obtenerTodosUsuarios...
}