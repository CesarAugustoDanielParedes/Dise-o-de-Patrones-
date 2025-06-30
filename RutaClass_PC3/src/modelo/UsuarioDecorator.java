/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Usuario;

public abstract class UsuarioDecorator extends Usuario {
    protected Usuario decoratedUsuario;

    public UsuarioDecorator(Usuario usuario) {
        this.decoratedUsuario = usuario;
    }

    @Override
    public abstract Usuario clone();

    @Override
    public String toString() {
        return decoratedUsuario.toString();
    }
}