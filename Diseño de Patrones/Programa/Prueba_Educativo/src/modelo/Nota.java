/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;

public class Nota {
    private int estudianteId;
    private int cursoId;
    private BigDecimal nota;

    public Nota(int estudianteId, int cursoId, double nota) {
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.nota = BigDecimal.valueOf(nota);
    }

    public int getEstudianteId() { return estudianteId; }
    public int getCursoId() { return cursoId; }
    public BigDecimal getNota() { return nota; }
}

