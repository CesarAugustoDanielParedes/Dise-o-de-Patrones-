/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _Refererencias;

/**
 *
 * @author Jeremi Alexander
 */
public class estudiante extends usuario implements interaccionEstProf, seguimProgre {

    public estudiante(String nombre) {
        super(nombre);
    }

    @Override
    public void publicarMens(String mens) {
        System.out.println("Estudiante " + nombre + " ha publicado un mensaje: " + mens);
    }

    @Override
    public void responderMens(String idMens, String rpta) {
        System.out.println("Estudiante " + nombre + " ha respondido al mensaje " + idMens + ": " + rpta);
    }

    @Override
    public void crearPubliForo(String publi) {
        System.out.println("Estudiante " + nombre + " ha creado un post en el foro: " + publi);
    }

    @Override
    public void seguirProgr(String nombreEst) {
        System.out.println("Estudiante " + nombre + " esta  siguiendo su progreso academico.");
    }

    @Override
    public void generarRepor(String nombreEst) {
        System.out.println("Generando reporte de progreso para el estudiante: " + nombre);
    }

}
