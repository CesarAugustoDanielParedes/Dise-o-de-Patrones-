/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _Refererencias;

/**
 *
 * @author Jeremi Alexander
 */
public class profesor extends usuario implements gestionCurso, interaccionEstProf {

    public profesor (String nombre){
    super(nombre);
    }

    @Override
    public void crearCur(String crearCur) {
        System.out.println("Profesor " + nombre + " ha creado el curso de: " + crearCur);

    }

    @Override
    public void agregarMat(String crearCur, String mat) {
        System.out.println("Material " + mat + " agregado al curso: " + crearCur);
    }

    @Override
    public void asignarTar(String crearCur, String tar) {
        System.out.println("Tarea " + tar + " asignada en el curso de " + crearCur);

    }

    @Override
    public void calificarEst(String crearCur, String nomEst, int calif) {
        System.out.println("Profesor " + nombre + " ha calificado a " + nomEst + " en el curso " + crearCur + " con una nota de " + calif);
    }

    @Override
    public void publicarMens(String mens) {
        System.out.println("Profesor " + nombre + " ha publicado un mensaje: " + mens);
    }

    @Override
    public void responderMens(String idMens, String rpta) {
        System.out.println("Profesor " + nombre + " ha respondido al mensaje " + idMens + ": " + rpta);

    }

    @Override
    public void crearPubliForo(String publi) {
        System.out.println("Profesor " + nombre + " ha creado un post en el foro: " + publi);

    }

}
