/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import vista.GC_CrearCurso;

public class CrearCursoCommand implements Command {
    private GC_CrearCurso cursoView;

    public CrearCursoCommand(GC_CrearCurso cursoView) {
        this.cursoView = cursoView;
    }

    @Override
    public void execute() {
        // Mostramos la ventana Crear Curso
        cursoView.setVisible(true);
        cursoView.askToProceed();  // Llamamos al método para confirmar si continuar a la siguiente ventana
    }
}