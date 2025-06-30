/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

// Clase Modelo para Calificación
public class Calificacion {
    private int idCurso;
    private int idEstudiante;
    private String idTarea;
    private String fechaEntrega;
    private double nota;
    private String observacion;

    public Calificacion(int idCurso, int idEstudiante, String idTarea, String fechaEntrega, double nota, String observacion) {
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.idTarea = idTarea;
        this.fechaEntrega = fechaEntrega;
        this.nota = nota;
        this.observacion = observacion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
}