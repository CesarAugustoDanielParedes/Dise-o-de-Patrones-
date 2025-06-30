/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioComposite implements UsuarioComponent {
    private final List<UsuarioComponent> children = new ArrayList<>();

    public void add(UsuarioComponent comp) {
        children.add(comp);
    }

    public void remove(UsuarioComponent comp) {
        children.remove(comp);
    }

    @Override
    public void mostrarDetalles() {
        for (UsuarioComponent comp : children) {
            comp.mostrarDetalles();
        }
    }
}