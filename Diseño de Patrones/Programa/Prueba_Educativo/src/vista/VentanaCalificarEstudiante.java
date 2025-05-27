package Vista;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import modelo.Curso;
import modelo.Nota;
import modelo.NotaDAO;

/**
 * @author Jeremi Alexander
 */
public class VentanaCalificarEstudiante extends JFrame {
    private JSpinner spnEstudianteId, spnNota;
    private JButton btnAtras, btnGuardar, btnFinalizar;
    private JLabel lblEstado;
    private Curso curso;

    public VentanaCalificarEstudiante(Curso curso) {
        this.curso = curso;
        setTitle("Calificar Estudiante - Curso: " + (curso != null ? curso.getNombre() : "Sin Curso"));
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Encabezado (Secciones 1, 2, 3) con navegación
        JPanel panelEncabezado = new JPanel(new GridLayout(1, 3, 5, 5));
        panelEncabezado.setBackground(new Color(70, 130, 180));
        JButton btnTab1 = new JButton("1. Registrar Curso");
        JButton btnTab2 = new JButton("2. Asignar Tareas");
        JButton btnTab3 = new JButton("3. Calificar Estudiantes");
        btnTab1.setBackground(new Color(70, 130, 180));
        btnTab2.setBackground(new Color(70, 130, 180));
        btnTab3.setBackground(new Color(70, 130, 180));
        btnTab1.setForeground(Color.WHITE);
        btnTab2.setForeground(Color.WHITE);
        btnTab3.setForeground(Color.WHITE);

        btnTab1.addActionListener(e -> {
            dispose();
            new VentanaRegistroCurso().setVisible(true);
        });
        btnTab2.addActionListener(e -> {
            dispose();
            new VentanaAsignarTareas(curso).setVisible(true);
        });
        btnTab3.addActionListener(e -> {
            dispose();
            new VentanaCalificarEstudiante(curso).setVisible(true);
        });

        panelEncabezado.add(btnTab1);
        panelEncabezado.add(btnTab2);
        panelEncabezado.add(btnTab3);

        // Área de Contenido Principal
        JPanel panelPrincipal = new JPanel(new GridLayout(3, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        spnEstudianteId = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1)); // ID de estudiante
        spnNota = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1)); // Nota entre 0 y 20

        // Ajustar tamaños
        spnEstudianteId.setPreferredSize(new Dimension(80, 25));
        spnNota.setPreferredSize(new Dimension(80, 25));

        panelPrincipal.add(new JLabel("ID del Estudiante:"));
        panelPrincipal.add(spnEstudianteId);
        panelPrincipal.add(new JLabel("Nota (0-20):"));
        panelPrincipal.add(spnNota);
        panelPrincipal.add(new JLabel("Estado:"));
        lblEstado = new JLabel("");
        panelPrincipal.add(lblEstado);

        // Botones Inferiores
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        btnAtras = new JButton("Atrás");
        btnGuardar = new JButton("Guardar");
        btnFinalizar = new JButton("Finalizar");
        btnAtras.setBackground(new Color(255, 99, 71));
        btnGuardar.setBackground(new Color(60, 179, 113));
        btnFinalizar.setBackground(new Color(70, 130, 180));
        btnAtras.setForeground(Color.WHITE);
        btnGuardar.setForeground(Color.WHITE);
        btnFinalizar.setForeground(Color.WHITE);
        panelBotones.add(btnAtras);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnFinalizar);

        // Agregar paneles al marco
        add(panelEncabezado, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Accesos directos
        getRootPane().setDefaultButton(btnGuardar);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                btnAtras.doClick();
            }
        });

        // Listeners
        btnGuardar.addActionListener(e -> {
            try {
                int estudianteId = (int) spnEstudianteId.getValue();
                double nota = (double) (int) spnNota.getValue();
                if (curso == null) {
                    lblEstado.setText("No se ha creado un curso aún");
                    lblEstado.setForeground(Color.RED);
                    return;
                }
                Nota notaObj = new Nota(estudianteId, curso.getId(), nota);
                NotaDAO notaDAO = new NotaDAO();
                if (notaDAO.registrarNota(notaObj)) {
                    lblEstado.setText("¡Nota guardada con éxito!");
                    lblEstado.setForeground(new Color(60, 179, 113));
                    JOptionPane.showMessageDialog(this, "Nota registrada para el curso: " + curso.getNombre());
                } else {
                    lblEstado.setText("Error al registrar la nota");
                    lblEstado.setForeground(Color.RED);
                }
            } catch (Exception ex) {
                lblEstado.setText("Error: " + ex.getMessage());
                lblEstado.setForeground(Color.RED);
            }
        });

        btnAtras.addActionListener(e -> {
            dispose();
            new VentanaAsignarTareas(curso).setVisible(true);
        });

        btnFinalizar.addActionListener(e -> dispose());
    }
}