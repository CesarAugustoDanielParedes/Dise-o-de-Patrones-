/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Usuario;

public class UsuarioLeaf implements UsuarioComponent {
    private final Usuario usuario;

    public UsuarioLeaf(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println(usuario);
    }
}