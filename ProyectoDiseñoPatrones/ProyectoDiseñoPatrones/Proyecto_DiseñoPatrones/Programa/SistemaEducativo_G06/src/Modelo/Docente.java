/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Docente extends Usuario {

    private String especialidad;
    private String departamento;

    public Docente() {
        super();
        this.tipo = "DOCENTE";
    }

    public Docente(int id, String nombre, String email, String telefono, String especialidad, String departamento) {
        super(id, nombre, email, telefono, "DOCENTE");
        this.especialidad = especialidad;
        this.departamento = departamento;
    }

    @Override
    public Docente clone() throws CloneNotSupportedException {
        return (Docente) super.clone();
    }

    @Override
    public String getTipoEntidad() {
        return "DOCENTE";
    }

    // Getters y Setters específicos
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Soy un docente: " + nombre);
    }
}
