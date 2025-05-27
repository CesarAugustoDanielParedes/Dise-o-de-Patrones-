/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jeremi Alexander
 */
package Control;

import modelo.Docente;
import modelo.Estudiante;
import modelo.Curso;


/*
Esta clase se encarga de crear instancias de Docente, 
Estudiante o Curso desde la capa Control, lo cual reduce 
el acoplamiento entre clases.
 */
public class UsuarioFactory {

    public static Object crearEntidad(String tipo) {
        switch (tipo.toLowerCase()) {
            case "docente":
                return new Docente();
            case "estudiante":
                return new Estudiante();
            case "curso":
                return new Curso();
            default:
                throw new IllegalArgumentException("Tipo de entidad no reconocido: " + tipo);
        }
    }
}
