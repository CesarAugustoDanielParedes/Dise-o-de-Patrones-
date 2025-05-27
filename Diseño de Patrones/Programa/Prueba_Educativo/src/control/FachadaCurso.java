/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import modelo.AfiliacionDAO;
import modelo.Curso;

/**
 *
 * @author Jeremi Alexander
 */
public class FachadaCurso {
    private static FachadaCurso instancia;
    private AfiliacionDAO dao;

    private FachadaCurso() {
        dao = new AfiliacionDAO();
    }

    public static FachadaCurso getInstancia() {
        if (instancia == null) {
            instancia = new FachadaCurso();
        }
        return instancia;
    }

    public boolean crearCurso(Curso curso) {
        try {
            int idGenerado = dao.ejecutarCRUD("CREATE", curso);
            curso.setId(idGenerado);
            return idGenerado > 0;
        } catch (Exception e) {
            System.err.println("Error en fachada: " + e.getMessage());
            return false;
        }
    }
}
