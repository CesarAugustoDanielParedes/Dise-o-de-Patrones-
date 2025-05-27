/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jeremi Alexander
 */
package modelo;

public class Estudiante extends Usuario {

    private String matricula;
    private String grado;

    public Estudiante() {
        super();
        this.tipo = "ESTUDIANTE";
    }

    public Estudiante(int id, String nombre, String email, String telefono, String matricula, String grado) {
        super(id, nombre, email, telefono, "ESTUDIANTE");
        this.matricula = matricula;
        this.grado = grado;
    }

    @Override
    public Estudiante clone() throws CloneNotSupportedException {
        return (Estudiante) super.clone();
    }

    @Override
    public String getTipoEntidad() {
        return "ESTUDIANTE";
    }

    // Getters y Setters específicos
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    
    /*Agregado por el patron Factory*/
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Soy un estudiante: " + nombre);
    }
}
