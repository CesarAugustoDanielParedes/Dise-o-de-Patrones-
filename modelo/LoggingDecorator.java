/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class LoggingDecorator extends UsuarioDecorator {

    public LoggingDecorator(Usuario usuario) {
        super(usuario);
    }

    @Override
    public Usuario clone() {
        // Delegamos clonación al original y añadimos logging
        System.out.println("Clonando usuario: " + decoratedUsuario);
        return decoratedUsuario.clone();
    }

    @Override
    public String toString() {
        String info = decoratedUsuario.toString();
        System.out.println("Logging INFO: " + info);
        return info;
    }
}