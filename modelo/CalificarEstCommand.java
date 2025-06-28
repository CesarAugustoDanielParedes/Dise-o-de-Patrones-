/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import vista.GC_calificarEst;

public class CalificarEstCommand implements Command {
    private GC_calificarEst calificarView;

    public CalificarEstCommand(GC_calificarEst calificarView) {
        this.calificarView = calificarView;
    }

    @Override
    public void execute() {
        // Mostramos la ventana Calificar Estudiante
        calificarView.setVisible(true);
        calificarView.askToProceed();  // Llamamos al método para confirmar si continuar
    }
}