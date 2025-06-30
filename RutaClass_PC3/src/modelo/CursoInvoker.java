/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class CursoInvoker {
    private Command crearCursoCommand;
    private Command asignarTareaCommand;
    private Command calificarEstCommand;

    public CursoInvoker(Command crearCursoCommand, Command asignarTareaCommand, Command calificarEstCommand) {
        this.crearCursoCommand = crearCursoCommand;
        this.asignarTareaCommand = asignarTareaCommand;
        this.calificarEstCommand = calificarEstCommand;
    }

    // Invocar el comando para Crear Curso
    public void pressCrearCursoButton() {
        crearCursoCommand.execute();
    }

    // Invocar el comando para Asignar Tarea
    public void pressAsignarTareaButton() {
        asignarTareaCommand.execute();
    }

    // Invocar el comando para Calificar Estudiante
    public void pressCalificarEstButton() {
        calificarEstCommand.execute();
    }
}