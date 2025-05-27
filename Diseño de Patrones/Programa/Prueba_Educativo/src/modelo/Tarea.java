/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Tarea {
    private String nombre;
    private String fechaLimite;
    private int cursoId;

    public Tarea(String nombre, String fechaLimite, int cursoId) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.cursoId = cursoId;
    }

    public String getNombre() { return nombre; }
    public String getFechaLimite() { return fechaLimite; }
    public int getCursoId() { return cursoId; }
}
