package com.shizuka.ui.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.shizuka.data.model.Paciente;
import com.shizuka.data.repository.PacienteRepository;
import com.shizuka.data.repository.Repository;
import com.shizuka.ui.modal.InputPacienteForms;
import com.shizuka.ui.model.ModelPaciente;
import com.shizuka.ui.system.Form;
import com.shizuka.ui.utils.SystemForm;
import com.shizuka.ui.utils.table.CheckBoxTableHeaderRenderer;
import com.shizuka.ui.utils.table.TableHeaderAlignment;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

@SystemForm(name = "Pacientes", description = "Módulo de pacientes para operaciones CRUD", tags = {"lista"})
public class FormPacientes extends Form {

    private JTable table;
    private DefaultTableModel tableModel;
    private Repository<Paciente> repository;

    @Override
    protected void init() {
        repository = new PacienteRepository();
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow][]"));
        add(createInfo(
                "Gestión de Pacientes",
                "Este módulo permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar pacientes.",
                1));
        add(createTab(), "gapx 7 7");
    }

    private JPanel createInfo(String title, String description, int level) {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel(title);
        JTextPane text = new JTextPane();
        text.setText(description);
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +" + (4 - level));
        panel.add(lbTitle);
        panel.add(text, "width 500");
        return panel;
    }

    private Component createTab() {
        JTabbedPane tabb = new JTabbedPane();
        tabb.putClientProperty(FlatClientProperties.STYLE, "" +
                "tabType:card");
        tabb.addTab("Lista Pacientes", createBorder(createCustomTable()));
        return tabb;
    }

    private Component createBorder(Component component) {
        JPanel panel = new JPanel(new MigLayout("fill,insets 7 0 7 0", "[fill]", "[fill]"));
        panel.add(component);
        return panel;
    }

    private Component createCustomTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        String[] columnNames = {"SELECT", "#", "Nombres", "Apellidos", "DNI", "Fecha Nacimiento", "Contacto", "N° Citas", "Ultima Cita", "id"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0)
                    return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(7).setMaxWidth(100);
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(9));

        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                return SwingConstants.LEADING;
            }
        });

        // style
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
                "height:30;" +
                "hoverBackground:null;" +
                "pressedBackground:null;" +
                "separatorColor:$TableHeader.background;");
        table.putClientProperty(FlatClientProperties.STYLE, "" +
                "rowHeight:70;" +
                "showHorizontalLines:true;" +
                "intercellSpacing:0,1;" +
                "cellFocusColor:$TableHeader.hoverBackground;" +
                "selectionBackground:$TableHeader.hoverBackground;" +
                "selectionInactiveBackground:$TableHeader.hoverBackground;" +
                "selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "trackInsets:3,3,3,3;" +
                "thumbInsets:3,3,3,3;" +
                "background:$Table.background;");

        // create title
        JLabel title = new JLabel("Detalle Pacientes");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderAction());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane);

        updateData();

        return panel;
    }

    private void updateData() {
        tableModel.setRowCount(0);

        for (var paciente : repository.getAll()) {
            tableModel.addRow(ModelPaciente.toTableRowCustom(paciente, tableModel.getRowCount() + 1));
        }

        tableModel.fireTableDataChanged();
    }


    private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("app/icons/search.svg", 0.4f));

        JButton btnCreate = new JButton("Crear");
        JButton btnEdit = new JButton("Editar");
        JButton btnDelete = new JButton("Eliminar");

        btnCreate.addActionListener(this::onCreate);
        btnEdit.addActionListener(this::onEdit);
        btnDelete.addActionListener(this::onDelete);

        panel.add(txtSearch);
        panel.add(btnCreate);
        panel.add(btnEdit);
        panel.add(btnDelete);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");

        return panel;
    }

    private void onCreate(ActionEvent e) {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);

        SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Registrar", 0), new SimpleModalBorder.Option("Cancelar", 1)};

        InputPacienteForms inputForms = new InputPacienteForms();

        ModalDialog.showModal(this, new SimpleModalBorder(
                inputForms, "Registrar Paciente", options,
                (controller, action) -> {
                    if(action == 0) {
                        try {
                            repository.insert(inputForms.getPaciente());
                            updateData();
                            Toast.show(this, Toast.Type.SUCCESS, "Se ha registrado correctamente");

                        } catch (IllegalArgumentException ex) {
                            Toast.show(this, Toast.Type.ERROR, "No se ha podido registrar el paciente");
                        }
                    }
                }), option);
    }

    private void onEdit(ActionEvent e) {
        // Lógica para editar un paciente seleccionado
        try{
            int selectedRow = table.getSelectedRow();
            int id = (int) tableModel.getValueAt(selectedRow, 9);

            Paciente paciente = repository.findById(id).get();

            Option option = ModalDialog.createOption();
            option.getLayoutOption().setSize(-1, 1f)
                    .setLocation(Location.TRAILING, Location.TOP)
                    .setAnimateDistance(0.7f, 0);

            SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Actualizar", 0), new SimpleModalBorder.Option("Cancelar", 1)};

            InputPacienteForms inputForms = new InputPacienteForms(paciente);

            ModalDialog.showModal(this, new SimpleModalBorder(
                    inputForms, "Actualizar Paciente", options,
                    (controller, action) -> {
                        if(action == 0) {
                            try {
                                repository.update(id, inputForms.getPaciente());
                                updateData();
                                Toast.show(this, Toast.Type.SUCCESS, "Se ha actualizado correctamente");

                            } catch (IllegalArgumentException ex) {
                                Toast.show(this, Toast.Type.ERROR, "No se ha actualizar el paciente");
                            }
                        }
                    }), option);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.show(this, Toast.Type.ERROR, "No se ha podido actualizar el paciente");
        }
    }

    private void onDelete(ActionEvent e) {
        // Lógica para eliminar un paciente seleccionado
        try{
            int selectedRow = table.getSelectedRow();
            int id = (int) tableModel.getValueAt(selectedRow, 9);

            JTextArea txt = new JTextArea();
            txt.setEditable(false);
            txt.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:5,35,5,35");
            txt.setText("¡Esta seguro de eliminar el paciente?");

            SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Eliminar", 0), new SimpleModalBorder.Option("Cancelar", 1)};

            ModalDialog.showModal(this, new SimpleModalBorder(txt, "Confirmar", options,
                    (controller, action) -> {
                        if(action == 0) {
                            repository.delete(id);
                            updateData();
                            Toast.show(this, Toast.Type.SUCCESS, "Paciente eliminado exitosamente");
                        }
                    }));
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.show(this, Toast.Type.ERROR, "No se ha podido eliminar el paciente");
        }
    }
}
