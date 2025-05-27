/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jeremi Alexander
 */

package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AfiliacionDAO {
    public int ejecutarCRUD(String operacion, Entidad entidad) {
        int resultado = -1;
        String tipoEntidad = entidad.getTipoEntidad();

        Connection con = null;
        CallableStatement stmt = null;
        
        try {
            con = conexionJava_SQLServer.getInstancia().getConexion();
            
            // Primero manejar usuario si es necesario
            if ((entidad instanceof Estudiante || entidad instanceof Docente) && 
                operacion.equals("CREATE")) {
                // Primero crear el usuario
                Usuario usuario = (Usuario) entidad;
                CallableStatement stmtUsuario = con.prepareCall(
                    "{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                
                configurarParametrosUsuario(stmtUsuario, "CREATE", "USUARIO", usuario);
                stmtUsuario.registerOutParameter(16, Types.INTEGER);
                stmtUsuario.execute();
                
                int usuarioId = stmtUsuario.getInt(16);
                stmtUsuario.close();
                
                if (usuarioId <= 0) {
                    throw new SQLException("No se pudo crear el usuario padre");
                }
                
                // Asignar el ID al objeto original
                usuario.setId(usuarioId);
                
                // Ahora crear la entidad específica
                stmt = con.prepareCall(
                    "{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                
                if (entidad instanceof Estudiante) {
                    Estudiante est = (Estudiante) entidad;
                    configurarParametrosEstudiante(stmt, "CREATE", est);
                } else if (entidad instanceof Docente) {
                    Docente doc = (Docente) entidad;
                    configurarParametrosDocente(stmt, "CREATE", doc);
                }
            } 
            else if (entidad instanceof Curso && operacion.equals("CREATE")) {
                Curso curso = (Curso) entidad;
                // Verificar que el docente existe
                if (!existeDocente(con, curso.getDocenteId())) {
                    throw new SQLException("El docente con ID " + curso.getDocenteId() + " no existe");
                }
                
                stmt = con.prepareCall(
                    "{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                configurarParametrosCurso(stmt, "CREATE", curso);
            }
            else {
                // Operaciones normales (UPDATE, DELETE, READ)
                stmt = con.prepareCall(
                    "{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                
                if (entidad instanceof Usuario) {
                    configurarParametrosUsuario(stmt, operacion, 
                        entidad instanceof Estudiante ? "ESTUDIANTE" : 
                        entidad instanceof Docente ? "DOCENTE" : "USUARIO", 
                        (Usuario) entidad);
                } else if (entidad instanceof Curso) {
                    configurarParametrosCurso(stmt, operacion, (Curso) entidad);
                }
            }
            
            if (stmt != null) {
                stmt.registerOutParameter(16, Types.INTEGER);
                stmt.execute();
                
                if (operacion.equals("READ")) {
                    ResultSet rs = stmt.getResultSet();
                    if (rs != null) {
                        while (rs.next()) {
                            System.out.println("ID: " + rs.getInt("id") + 
                                           " | Nombre: " + rs.getString("nombre") +
                                           " | Email: " + rs.getString("email"));
                        }
                    }
                }
                
                resultado = stmt.getInt(16);
            }
            
            conexionJava_SQLServer.getInstancia().commit();
        } catch (SQLException e) {
            conexionJava_SQLServer.getInstancia().rollback();
            System.err.println("Error al ejecutar procedimiento almacenado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar CallableStatement: " + e.getMessage());
            }
        }
        
        return resultado;
    }

    private void configurarParametrosUsuario(CallableStatement stmt, String operacion, 
                                           String tipoEntidad, Usuario usuario) throws SQLException {
        stmt.setString(1, operacion);
        stmt.setString(2, tipoEntidad);
        stmt.setObject(3, operacion.equals("CREATE") ? null : usuario.getId());
        stmt.setString(4, usuario.getNombre());
        stmt.setString(5, usuario.getEmail());
        stmt.setString(6, usuario.getTelefono());
        stmt.setString(7, usuario.getTipo());
        stmt.setNull(8, Types.VARCHAR); // matricula
        stmt.setNull(9, Types.VARCHAR); // grado
        stmt.setNull(10, Types.VARCHAR); // especialidad
        stmt.setNull(11, Types.VARCHAR); // departamento
        stmt.setNull(12, Types.VARCHAR); // codigo_curso
        stmt.setNull(13, Types.VARCHAR); // descripcion_curso
        stmt.setNull(14, Types.INTEGER); // creditos_curso
        stmt.setNull(15, Types.INTEGER); // docente_id_curso
    }

    private void configurarParametrosEstudiante(CallableStatement stmt, String operacion, 
                                             Estudiante estudiante) throws SQLException {
        stmt.setString(1, operacion);
        stmt.setString(2, "ESTUDIANTE");
        stmt.setInt(3, estudiante.getId()); // Usamos el ID del usuario ya creado
        stmt.setNull(4, Types.VARCHAR); // nombre
        stmt.setNull(5, Types.VARCHAR); // email
        stmt.setNull(6, Types.VARCHAR); // telefono
        stmt.setNull(7, Types.VARCHAR); // tipo_usuario
        stmt.setString(8, estudiante.getMatricula());
        stmt.setString(9, estudiante.getGrado());
        stmt.setNull(10, Types.VARCHAR); // especialidad
        stmt.setNull(11, Types.VARCHAR); // departamento
        stmt.setNull(12, Types.VARCHAR); // codigo_curso
        stmt.setNull(13, Types.VARCHAR); // descripcion_curso
        stmt.setNull(14, Types.INTEGER); // creditos_curso
        stmt.setNull(15, Types.INTEGER); // docente_id_curso
    }

    private void configurarParametrosDocente(CallableStatement stmt, String operacion, 
                                          Docente docente) throws SQLException {
        stmt.setString(1, operacion);
        stmt.setString(2, "DOCENTE");
        stmt.setInt(3, docente.getId()); // Usamos el ID del usuario ya creado
        stmt.setNull(4, Types.VARCHAR); // nombre
        stmt.setNull(5, Types.VARCHAR); // email
        stmt.setNull(6, Types.VARCHAR); // telefono
        stmt.setNull(7, Types.VARCHAR); // tipo_usuario
        stmt.setNull(8, Types.VARCHAR); // matricula
        stmt.setNull(9, Types.VARCHAR); // grado
        stmt.setString(10, docente.getEspecialidad());
        stmt.setString(11, docente.getDepartamento());
        stmt.setNull(12, Types.VARCHAR); // codigo_curso
        stmt.setNull(13, Types.VARCHAR); // descripcion_curso
        stmt.setNull(14, Types.INTEGER); // creditos_curso
        stmt.setNull(15, Types.INTEGER); // docente_id_curso
    }

    private void configurarParametrosCurso(CallableStatement stmt, String operacion, 
                                        Curso curso) throws SQLException {
        stmt.setString(1, operacion);
        stmt.setString(2, "CURSO");
        stmt.setObject(3, operacion.equals("CREATE") ? null : curso.getId());
        stmt.setString(4, curso.getNombre());
        stmt.setNull(5, Types.VARCHAR); // email
        stmt.setNull(6, Types.VARCHAR); // telefono
        stmt.setNull(7, Types.VARCHAR); // tipo_usuario
        stmt.setNull(8, Types.VARCHAR); // matricula
        stmt.setNull(9, Types.VARCHAR); // grado
        stmt.setNull(10, Types.VARCHAR); // especialidad
        stmt.setNull(11, Types.VARCHAR); // departamento
        stmt.setString(12, curso.getCodigo());
        stmt.setString(13, curso.getDescripcion());
        stmt.setInt(14, curso.getCreditos());
        stmt.setInt(15, curso.getDocenteId());
    }

    public boolean existeDocente(Connection con, int docenteId) throws SQLException {
        if (docenteId <= 0) return false;
        
        try (CallableStatement stmt = con.prepareCall(
            "{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            
            stmt.setString(1, "READ");
            stmt.setString(2, "DOCENTE");
            stmt.setInt(3, docenteId);
            stmt.setNull(4, Types.VARCHAR);
            stmt.setNull(5, Types.VARCHAR);
            stmt.setNull(6, Types.VARCHAR);
            stmt.setNull(7, Types.VARCHAR);
            stmt.setNull(8, Types.VARCHAR);
            stmt.setNull(9, Types.VARCHAR);
            stmt.setNull(10, Types.VARCHAR);
            stmt.setNull(11, Types.VARCHAR);
            stmt.setNull(12, Types.VARCHAR);
            stmt.setNull(13, Types.VARCHAR);
            stmt.setNull(14, Types.INTEGER);
            stmt.setNull(15, Types.INTEGER);
            stmt.registerOutParameter(16, Types.INTEGER);
            
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            return rs != null && rs.next(); // Si hay resultados, el docente existe
        }
    }

    public List<Docente> listarDocentes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    public List<Curso> obtenerListaCursos() {
    List<Curso> cursos = new ArrayList<>();
    Connection con = null;
    CallableStatement stmt = null;

    try {
        con = conexionJava_SQLServer.getInstancia().getConexion();

        stmt = con.prepareCall("{call sp_crud_afiliacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setString(1, "READ");
        stmt.setString(2, "CURSO");
        stmt.setNull(3, Types.INTEGER);
        for (int i = 4; i <= 15; i++) {
            stmt.setNull(i, Types.VARCHAR);
        }
        stmt.registerOutParameter(16, Types.INTEGER);

        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while (rs != null && rs.next()) {
            Curso curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setCodigo(rs.getString("codigo"));
            curso.setNombre(rs.getString("nombre"));
            curso.setDescripcion(rs.getString("descripcion"));
            curso.setCreditos(rs.getInt("creditos"));
            curso.setDocenteId(rs.getInt("docente_id"));
            cursos.add(curso);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener cursos: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar stmt: " + e.getMessage());
        }
    }

    return cursos;
}

}