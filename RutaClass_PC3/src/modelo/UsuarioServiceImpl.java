/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Usuario;
import java.sql.SQLException;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioServiceFacade facade = new UsuarioServiceFacade();

    @Override
    public int registrar(Usuario u) throws SQLException {
        return facade.registrarUsuarioCompleto(u);
    }

    @Override
    public Usuario obtener(int id) throws SQLException {
        return facade.obtenerUsuarioPorId(id);
    }
}