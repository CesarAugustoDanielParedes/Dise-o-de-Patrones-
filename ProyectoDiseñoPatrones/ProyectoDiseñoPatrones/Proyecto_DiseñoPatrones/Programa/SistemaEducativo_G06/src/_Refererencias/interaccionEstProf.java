/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package _Refererencias;

/**
 *
 * @author Jeremi Alexander
 * mens =  mensaje
 * publi = publicacion
 * rpta  = respuesta
 * 
 */
public interface interaccionEstProf {
    void publicarMens(String mens);
    void responderMens(String idMens, String rpta);
    void crearPubliForo(String publi);
}
