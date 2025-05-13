package test;

import modelo.AfiliacionDAO;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Curso;
import Control.UsuarioFactory;


public class TestAfiliacion {
    public static void main(String[] args) {
        try {
            AfiliacionDAO dao = new AfiliacionDAO();

            // 1. Crear un docente (nuevo registro)
            System.out.println("Creando docente...");
            Docente docenteBase = new Docente(0, "Dr. Andrés Vilchez", "andres.vilchez@uni.edu.pe", "944332211", 
                                              "Ingeniería Electrónica", "Facultad de Ingeniería");
            int docenteId = dao.ejecutarCRUD("CREATE", docenteBase);

            if (docenteId > 0) {
                System.out.println("Docente creado con ID: " + docenteId);

                // 2. Leer docente
                System.out.println("\nLeyendo docente...");
                //Docente docenteLeer = new Docente();/*ANTES*/
                Docente docenteLeer = (Docente) UsuarioFactory.crearEntidad("docente"); /*Usando patron Factory*/
                docenteLeer.setId(docenteId);
                dao.ejecutarCRUD("READ", docenteLeer);

                // 3. Crear estudiante (nuevo registro)
                System.out.println("\nCreando estudiante...");
                Estudiante estudianteBase = new Estudiante(0, "Lucía Ramos", "lucia.ramos@estudiantes.pe", "951776655", 
                                                           "PUCP2025010", "Cuarto Ciclo");
                int estudianteId = dao.ejecutarCRUD("CREATE", estudianteBase);

                if (estudianteId > 0) {
                    System.out.println("Estudiante creado con ID: " + estudianteId);

                    // 4. Leer estudiante
                    System.out.println("\nLeyendo estudiante...");
                    //Estudiante estudianteLeer = new Estudiante();/*ANTES*/
                    Estudiante estudianteLeer = (Estudiante) UsuarioFactory.crearEntidad("estudiante");/*Usando patron Factory*/
                    estudianteLeer.setId(estudianteId);
                    dao.ejecutarCRUD("READ", estudianteLeer);
                }

                // 5. Crear curso (nuevo registro)
                System.out.println("\nCreando curso...");
                Curso cursoBase = new Curso(0, "ELE303", "Sistemas Digitales", 
                                            "Introducción al diseño de circuitos digitales", 4, docenteId);
                int cursoId = dao.ejecutarCRUD("CREATE", cursoBase);

                if (cursoId > 0) {
                    System.out.println("Curso creado con ID: " + cursoId);

                    // 6. Leer curso
                    System.out.println("\nLeyendo curso...");
                    // Curso cursoLeer = new Curso(); /*ANTES*/
                    Curso cursoLeer = (Curso) UsuarioFactory.crearEntidad("curso"); /*Usando patron Factory*/
                    cursoLeer.setId(cursoId);
                    dao.ejecutarCRUD("READ", cursoLeer);
                }
            }
        } catch (Exception e) {
            System.err.println("Error en TestAfiliacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            modelo.conexionJava_SQLServer.getInstancia().cerrarConexion();
        }
    }
}
