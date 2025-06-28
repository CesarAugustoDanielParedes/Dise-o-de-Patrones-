/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import modelo.Usuario;
import java.sql.SQLException;

public interface UsuarioService {
    int registrar(Usuario u) throws SQLException;
    Usuario obtener(int id) throws SQLException;
}
