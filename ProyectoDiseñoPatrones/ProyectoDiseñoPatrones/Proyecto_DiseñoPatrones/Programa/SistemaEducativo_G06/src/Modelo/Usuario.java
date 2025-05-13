/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Jeremi Alexander
 */
package modelo;

public abstract class Usuario implements Entidad {

    protected int id;
    protected String nombre;
    protected String email;
    protected String telefono;
    protected String tipo; // ESTUDIANTE o DOCENTE

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String telefono, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    @Override
    public Usuario clone() throws CloneNotSupportedException {
        return (Usuario) super.clone();
    }

    @Override
    public String getTipoEntidad() {
        return "USUARIO";
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    /*Agregado por el Patron Factory*/
    public abstract void mostrarInformacion();


}
