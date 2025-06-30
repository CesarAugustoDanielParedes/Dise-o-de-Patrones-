/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class UsuarioType {
  
  private String usuarioTipo; // Tipo de usuario (ALUMNO o DOCENTE)
    private List<UsuarioObserver> observers = new ArrayList<>(); // Lista de observadores

    // Método para agregar observadores
    public void addObserver(UsuarioObserver observer) {
        observers.add(observer);
    }

    // Método para eliminar observadores
    public void removeObserver(UsuarioObserver observer) {
        observers.remove(observer);
    }

    // Método para cambiar el tipo de usuario y notificar a los observadores
    public void setUsuarioTipo(String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
        notifyObservers(); // Notifica a todos los observadores
    }

     public String getUsuarioTipo() {
        return usuarioTipo;
    }
     
    // Notifica a todos los observadores sobre el cambio de tipo de usuario
    private void notifyObservers() {
        for (UsuarioObserver observer : observers) {
            observer.update(usuarioTipo); // Llama al método update() en los observadores
        }
    }
    
}
