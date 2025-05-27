package Vista;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import modelo.Curso;
import modelo.Tarea;
import modelo.TareaDAO;

/**
 * @author Jeremi Alexander
 */
public class VentanaAsignarTareas extends JFrame {
    private JTextField txtTarea, txtFecha;
    private JButton btnAtras, btnGuardar, btnSiguiente;
    private JLabel lblEstado;
    private Curso curso;

    public VentanaAsignarTareas(Curso curso) {
        this.curso = curso;
        setTitle("Asignar Tareas - Curso: " + (curso != null ? curso.getNombre() : "Sin Curso"));
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

        txtTarea = new JTextField();
        txtFecha = new JTextField();
        txtFecha.setToolTipText("Formato: AAAA-MM-DD");

        // Ajustar tamaños
        txtTarea.setPreferredSize(new Dimension(50, 10));
        txtFecha.setPreferredSize(new Dimension(50, 10));

        panelPrincipal.add(new JLabel("Nombre de la Tarea:"));
        panelPrincipal.add(txtTarea);
        panelPrincipal.add(new JLabel("Fecha Límite (AAAA-MM-DD):"));
        panelPrincipal.add(txtFecha);
        panelPrincipal.add(new JLabel("Estado:"));
        lblEstado = new JLabel("");
        panelPrincipal.add(lblEstado);

        // Botones Inferiores
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        btnAtras = new JButton("Atrás");
        btnGuardar = new JButton("Guardar");
        btnSiguiente = new JButton("Siguiente");
        btnAtras.setBackground(new Color(255, 99, 71));
        btnGuardar.setBackground(new Color(60, 179, 113));
        btnSiguiente.setBackground(new Color(70, 130, 180));
        btnAtras.setForeground(Color.WHITE);
        btnGuardar.setForeground(Color.WHITE);
        btnSiguiente.setForeground(Color.WHITE);
        panelBotones.add(btnAtras);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnSiguiente);

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
            if (txtTarea.getText().isEmpty() || txtFecha.getText().isEmpty()) {
                lblEstado.setText("Por favor, complete todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }
            if (!txtFecha.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                lblEstado.setText("Formato de fecha inválido");
                lblEstado.setForeground(Color.RED);
                return;
            }
            if (curso == null) {
                lblEstado.setText("No se ha creado un curso aún");
                lblEstado.setForeground(Color.RED);
                return;
            }
            Tarea tarea = new Tarea(txtTarea.getText(), txtFecha.getText(), curso.getId());
            TareaDAO tareaDAO = new TareaDAO();
            if (tareaDAO.registrarTarea(tarea)) {
                lblEstado.setText("¡Tarea asignada con éxito!");
                lblEstado.setForeground(new Color(60, 179, 113));
                JOptionPane.showMessageDialog(this, "Tarea asignada");
            } else {
                lblEstado.setText("Error al asignar la tarea");
                lblEstado.setForeground(Color.RED);
            }
        });

        btnSiguiente.addActionListener(e -> {
            if (txtTarea.getText().isEmpty() || txtFecha.getText().isEmpty()) {
                lblEstado.setText("Por favor, complete todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }
            if (!txtFecha.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                lblEstado.setText("Formato de fecha inválido");
                lblEstado.setForeground(Color.RED);
                return;
            }
            if (curso == null) {
                lblEstado.setText("No se ha creado un curso aún");
                lblEstado.setForeground(Color.RED);
                return;
            }
            Tarea tarea = new Tarea(txtTarea.getText(), txtFecha.getText(), curso.getId());
            TareaDAO tareaDAO = new TareaDAO();
            if (tareaDAO.registrarTarea(tarea)) {
                dispose();
                new VentanaCalificarEstudiante(curso).setVisible(true);
            } else {
                lblEstado.setText("Error al asignar la tarea");
                lblEstado.setForeground(Color.RED);
            }
        });

        btnAtras.addActionListener(e -> {
            dispose();
            new VentanaRegistroCurso().setVisible(true);
        });
    }
}