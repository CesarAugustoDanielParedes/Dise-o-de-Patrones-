/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Usuario;
import java.sql.SQLException;

public class UsuarioServiceProxy implements UsuarioService {
    private final UsuarioServiceImpl realService = new UsuarioServiceImpl();

    @Override
    public int registrar(Usuario u) throws SQLException {
        // Ejemplo: sólo docentes pueden registrarse a través de este proxy
        if (!"DOCENTE".equalsIgnoreCase(u.getTipoUsuario())) {
            throw new SecurityException("Sólo DOCENTES pueden registrarse aquí.");
        }
        System.out.println("Proxy: Autorización correcta para " + u);
        return realService.registrar(u);
    }

    @Override
    public Usuario obtener(int id) throws SQLException {
        System.out.println("Proxy: Acceso de lectura para ID=" + id);
        return realService.obtener(id);
    }
}