// Archivo: model/Usuario.java
package modelo;

public abstract class Usuario implements Cloneable {
    protected int id;
    protected String nombres;
    protected String apellidos;
    protected String dni;
    protected String correoInstitucional;
    protected String tipoUsuario; // "ALUMNO" o "DOCENTE"

    // getters y setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No es posible clonar Usuario", e);
        }
    }

    @Override
    public String toString() {
        return "[" + tipoUsuario + "] " + nombres + " " + apellidos + " (DNI: " + dni + ")";
    }
}