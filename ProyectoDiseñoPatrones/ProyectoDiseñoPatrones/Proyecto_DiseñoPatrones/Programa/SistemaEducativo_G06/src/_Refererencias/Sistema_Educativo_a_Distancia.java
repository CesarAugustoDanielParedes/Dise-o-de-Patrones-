/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package _Refererencias;

import java.util.Scanner;

/**
 *
 * @author Jeremi Alexander
 */
public class Sistema_Educativo_a_Distancia {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese el tipo de usuario:");
        System.out.println("1- Profesor");
        System.out.println("2- Estudiante");
        System.out.println("Elige una opcion: ");
        int tipoUsuario = leer.nextInt();
        leer.nextLine();
        if (tipoUsuario == 1) {
            System.out.println("Ingrese el nombre del profesor: ");
            String nombreProfesor = leer.nextLine();
            profesor profe = new profesor(nombreProfesor);
            menuProfesor(profe, leer);
        } else if (tipoUsuario == 2) {
            System.out.println("Ingrese el nombre del estudiante: ");
            String nombreEstudiante = leer.nextLine();
            estudiante estu = new estudiante(nombreEstudiante);
            menuEstudiante(estu, leer);
        } else {
            System.out.println("Opción no válida...");
        }
    }
    //CREANDO CLASE MENUPROFESOR PARA QUE EL PROFESOR ELIGA SUS PROPIAS OPCIONES
    public static void menuProfesor(profesor profe, Scanner leer) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("--- MENU PROFESOR ---");
            System.out.println("1. Crear curso");
            System.out.println("2. Agregar material");
            System.out.println("3. Asignar tarea");
            System.out.println("4. Calificar estudiante");
            System.out.println("5. Publicar mensaje");
            System.out.println("6. Responder mensaje");
            System.out.println("7. Crear post en el foro");
            System.out.println("0. Salir");
            System.out.println("Opcion: ");
            int opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    leer.nextLine();
                    System.out.print("Nombre del curso: ");
                    profe.crearCur(leer.nextLine());
                    break;
                case 2:
                    leer.nextLine();
                    System.out.print("Curso: ");
                    String curso = leer.nextLine();
                    System.out.print("Material: ");
                    profe.agregarMat(curso, leer.nextLine());
                    break;
                case 3:
                    leer.nextLine();
                    System.out.print("Curso: ");
                    curso = leer.nextLine();
                    System.out.print("Tarea: ");
                    profe.asignarTar(curso, leer.nextLine());
                    break;
                case 4:
                    leer.nextLine();
                    System.out.print("Curso: ");
                    curso = leer.nextLine();
                    System.out.print("Estudiante: ");
                    String est = leer.nextLine();
                    System.out.print("Calificacion: ");
                    int nota = leer.nextInt();
                    profe.calificarEst(curso, est, nota);
                    break;
                case 5:
                    leer.nextLine();
                    System.out.print("Mensaje: ");
                    profe.publicarMens(leer.nextLine());
                    break;
                case 6:
                    leer.nextLine();
                    System.out.print("ID mensaje: ");
                    String id = leer.nextLine();
                    System.out.print("Respuesta: ");
                    profe.responderMens(id, leer.nextLine());
                    break;
                case 7:
                    leer.nextLine();
                    System.out.print("Post del foro: ");
                    profe.crearPubliForo(leer.nextLine());
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    leer.nextLine();
                    System.out.println("Opción inválida.");
            }
        }
    }
        //CREANDO CLASE MENUESTUDIANTE PARA QUE EL ESTUDIANTE ELIGA SUS OPCIONES
    public static void menuEstudiante(estudiante estu, Scanner leer) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("--- MENU ESTUDIANTE ---");
            System.out.println("1. Publicar mensaje");
            System.out.println("2. Responder mensaje");
            System.out.println("3. Crear post en el foro");
            System.out.println("4. Seguir progreso");
            System.out.println("5. Generar reporte");
            System.out.println("0. Salir");
            System.out.println("Opcion: ");
            int opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    leer.nextLine();
                    System.out.print("Mensaje: ");
                    estu.publicarMens(leer.nextLine());
                    break;
                case 2:
                    leer.nextLine();
                    System.out.print("ID mensaje: ");
                    String id = leer.nextLine();
                    System.out.print("Respuesta: ");
                    estu.responderMens(id, leer.nextLine());
                    break;
                case 3:
                    leer.nextLine();
                    System.out.print("Post del foro: ");
                    estu.crearPubliForo(leer.nextLine());
                    break;
                case 4:
                    leer.nextLine();
                    estu.seguirProgr(estu.getNombre());
                    break;
                case 5:
                    leer.nextLine();
                    estu.generarRepor(estu.getNombre());
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    leer.nextLine();
                    System.out.println("Opción inválida.");
            }
        }
    }
}
