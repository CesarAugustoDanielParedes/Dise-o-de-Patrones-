/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package _Refererencias;

/**
 *
 * @author Jeremi Alexander
 * - Est = Estudiante
 * - Cur = Curso
 * - Mat = Material
 * - Tar = Tarea
 * - Calif = Claificacion
 */

public interface gestionCurso {
    void crearCur(String crearCur);
    void agregarMat (String crearCur, String mat);
    void asignarTar (String crearCur, String tar);
    void calificarEst (String crearCur, String nomEst, int calif);  
}
