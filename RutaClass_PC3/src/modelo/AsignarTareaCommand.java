/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import vista.GC_AsignarTarea;

public class AsignarTareaCommand implements Command {
    private GC_AsignarTarea tareaView;

    public AsignarTareaCommand(GC_AsignarTarea tareaView) {
        this.tareaView = tareaView;
    }

    @Override
    public void execute() {
        // Mostramos la ventana Asignar Tarea
        tareaView.setVisible(true);
        tareaView.askToProceed();  // Llamamos al método para confirmar si continuar
    }
}