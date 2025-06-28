/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Alumno;
import modelo.Docente;
import modelo.Usuario;

public class UsuarioFactory {

    /**
     * Crea un Usuario según el tipo:
     *   "ALUMNO"  → Alumno
     *   "DOCENTE" → Docente
     */
    public static Usuario createUsuario(String tipoUsuario) {
        if ("ALUMNO".equalsIgnoreCase(tipoUsuario)) {
            return new Alumno();
        } else if ("DOCENTE".equalsIgnoreCase(tipoUsuario)) {
            return new Docente();
        } else {
            throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }
    }
}