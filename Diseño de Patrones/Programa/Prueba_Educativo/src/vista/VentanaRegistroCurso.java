package Vista;

import Control.FachadaCurso;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import modelo.AfiliacionDAO;
import modelo.Curso;
import modelo.conexionJava_SQLServer;

/**
 * @author Jeremi Alexander
 */
public class VentanaRegistroCurso extends JFrame {
    private JTextField txtCodigo, txtNombre;
    private JTextArea txtDescripcion;
    private JSpinner spnCreditos, spnDocenteId;
    private JButton btnAtras, btnGuardar, btnSiguiente;
    private JLabel lblEstado;

    public VentanaRegistroCurso() {
        setTitle("Registrar Curso");
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
            new VentanaAsignarTareas(null).setVisible(true);
        });
        btnTab3.addActionListener(e -> {
            dispose();
            new VentanaCalificarEstudiante(null).setVisible(true);
        });

        panelEncabezado.add(btnTab1);
        panelEncabezado.add(btnTab2);
        panelEncabezado.add(btnTab3);

        // Área de Contenido Principal
        JPanel panelPrincipal = new JPanel(new GridLayout(6, 2, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtDescripcion = new JTextArea(3, 10);
        spnCreditos = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1)); // Créditos entre 1 y 10
        spnDocenteId = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1)); // ID de docente

        // Ajustar tamaños
        txtCodigo.setPreferredSize(new Dimension(120, 25));
        txtNombre.setPreferredSize(new Dimension(120, 25));
        txtDescripcion.setPreferredSize(new Dimension(120, 60));
        spnCreditos.setPreferredSize(new Dimension(80, 25));
        spnDocenteId.setPreferredSize(new Dimension(80, 25));

        panelPrincipal.add(new JLabel("Código del Curso:"));
        panelPrincipal.add(txtCodigo);
        panelPrincipal.add(new JLabel("Nombre del Curso:"));
        panelPrincipal.add(txtNombre);
        panelPrincipal.add(new JLabel("Descripción:"));
        panelPrincipal.add(new JScrollPane(txtDescripcion));
        panelPrincipal.add(new JLabel("Créditos:"));
        panelPrincipal.add(spnCreditos);
        panelPrincipal.add(new JLabel("ID del Docente:"));
        panelPrincipal.add(spnDocenteId);
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
            if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtDescripcion.getText().isEmpty()) {
                lblEstado.setText("Por favor, complete todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }
            try {
                int creditos = (int) spnCreditos.getValue();
                int docenteId = (int) spnDocenteId.getValue();
                AfiliacionDAO dao = new AfiliacionDAO();
                if (!dao.existeDocente(conexionJava_SQLServer.getInstancia().getConexion(), docenteId)) {
                    lblEstado.setText("El docente con ID " + docenteId + " no existe");
                    lblEstado.setForeground(Color.RED);
                    return;
                }
                Curso curso = new Curso(0, txtCodigo.getText(), txtNombre.getText(),
                        txtDescripcion.getText(), creditos, docenteId);
                boolean creado = FachadaCurso.getInstancia().crearCurso(curso);
                if (creado) {
                    lblEstado.setText("¡Curso creado con éxito!");
                    lblEstado.setForeground(new Color(60, 179, 113));
                    JOptionPane.showMessageDialog(this, "Curso creado correctamente");
                } else {
                    throw new Exception("No se pudo crear el curso");
                }
            } catch (Exception ex) {
                lblEstado.setText("Error: " + ex.getMessage());
                lblEstado.setForeground(Color.RED);
            }
        });

        btnSiguiente.addActionListener(e -> {
            if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtDescripcion.getText().isEmpty()) {
                lblEstado.setText("Por favor, complete todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }
            try {
                int creditos = (int) spnCreditos.getValue();
                int docenteId = (int) spnDocenteId.getValue();
                AfiliacionDAO dao = new AfiliacionDAO();
                if (!dao.existeDocente(conexionJava_SQLServer.getInstancia().getConexion(), docenteId)) {
                    lblEstado.setText("El docente con ID " + docenteId + " no existe");
                    lblEstado.setForeground(Color.RED);
                    return;
                }
                Curso curso = new Curso(0, txtCodigo.getText(), txtNombre.getText(),
                        txtDescripcion.getText(), creditos, docenteId);
                boolean creado = FachadaCurso.getInstancia().crearCurso(curso);
                if (creado) {
                    dispose();
                    new VentanaAsignarTareas(curso).setVisible(true);
                } else {
                    lblEstado.setText("Error al crear el curso");
                    lblEstado.setForeground(Color.RED);
                }
            } catch (Exception ex) {
                lblEstado.setText("Error: " + ex.getMessage());
                lblEstado.setForeground(Color.RED);
            }
        });

        btnAtras.addActionListener(e -> dispose());
    }
}