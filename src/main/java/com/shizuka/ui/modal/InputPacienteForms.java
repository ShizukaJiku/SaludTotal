package com.shizuka.ui.modal;

import com.formdev.flatlaf.FlatClientProperties;
import com.shizuka.data.model.Paciente;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeParseException;

public class InputPacienteForms extends JPanel {

    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDni;
    private DatePicker txtFechaNacimiento;
    private JTextField txtContacto;

    public InputPacienteForms() {
        init();
    }

    public InputPacienteForms(Paciente paciente) {
        init();
        txtNombres.setText(paciente.getNombres());
        txtApellidos.setText(paciente.getApellidos());
        txtDni.setText(paciente.getDni());
        txtFechaNacimiento.setSelectedDate(paciente.getFechaNacimiento());
        txtContacto.setText(paciente.getContacto());
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 30 5 30,width 400", "[fill]", ""));

        // Crear campos de entrada
        txtNombres = new JTextField();
        txtApellidos = new JTextField();
        txtDni = new JTextField();
        txtFechaNacimiento = new DatePicker();
        txtContacto = new JTextField();

        JFormattedTextField dateEditor = new JFormattedTextField();
        txtFechaNacimiento.setEditor(dateEditor);

        // Agregar placeholders para los campos
        txtNombres.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nombres");
        txtApellidos.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Apellidos");
        txtDni.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "DNI");
        txtFechaNacimiento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Fecha de nacimiento (yyyy-MM-dd)");
        txtContacto.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Contacto (teléfono)");

        // Crear el título
        createTitle("Información del Paciente");

        // Agregar campos al panel
        add(new JLabel("Nombres"), "gapy 5 0");
        add(txtNombres);

        add(new JLabel("Apellidos"), "gapy 5 0");
        add(txtApellidos);

        add(new JLabel("DNI"), "gapy 5 0");
        add(txtDni);

        add(new JLabel("Fecha de Nacimiento"), "gapy 5 0");
        add(dateEditor);

        add(new JLabel("Contacto"), "gapy 5 0");
        add(txtContacto);

        addSubmitKeyListener();
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }

    private void addSubmitKeyListener() {
        KeyAdapter submitListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    ModalBorderAction modalBorderAction = ModalBorderAction.getModalBorderAction(InputPacienteForms.this);
                    if (modalBorderAction != null) {
                        modalBorderAction.doAction(SimpleModalBorder.YES_OPTION);
                    }
                }
            }
        };

        txtNombres.addKeyListener(submitListener);
        txtApellidos.addKeyListener(submitListener);
        txtDni.addKeyListener(submitListener);
        txtFechaNacimiento.addKeyListener(submitListener);
        txtContacto.addKeyListener(submitListener);
    }

    public Paciente getPaciente() {
        try {
            return Paciente.builder()
                    .nombres(txtNombres.getText().trim())
                    .apellidos(txtApellidos.getText().trim())
                    .dni(txtDni.getText().trim())
                    .fechaNacimiento(txtFechaNacimiento.getSelectedDate())
                    .contacto(txtContacto.getText().trim())
                    .build();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha de nacimiento no tiene el formato correcto (yyyy-MM-dd).");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear el paciente: " + e.getMessage());
        }
    }
}
