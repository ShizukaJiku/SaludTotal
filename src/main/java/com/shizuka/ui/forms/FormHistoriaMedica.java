package com.shizuka.ui.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.shizuka.data.model.HistoriaMedica;
import com.shizuka.data.repository.HistoriaMedicaRepository;
import com.shizuka.data.repository.Repository;
import com.shizuka.ui.model.ModelHistoriaMedica;
import com.shizuka.ui.system.Form;
import com.shizuka.ui.utils.SystemForm;
import com.shizuka.ui.utils.table.CheckBoxTableHeaderRenderer;
import com.shizuka.ui.utils.table.TableHeaderAlignment;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

@SystemForm(name = "Historia Médica", description = "Módulo de historias médicas para operaciones CRUD", tags = {"lista"})
public class FormHistoriaMedica extends Form {

    private JTable table;
    private DefaultTableModel tableModel;

    @Override
    protected void init() {
        setLayout(new MigLayout("fillx,wrap", "[fill]", "[][fill,grow][]"));
        add(createInfo(
                "Gestión de Historias Médicas",
                "Este módulo permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar historias médicas de los pacientes.",
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
        tabb.addTab("Lista Historias Médicas", createBorder(createCustomTable()));
        return tabb;
    }

    private Component createBorder(Component component) {
        JPanel panel = new JPanel(new MigLayout("fill,insets 7 0 7 0", "[fill]", "[fill]"));
        panel.add(component);
        return panel;
    }

    private Component createCustomTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        String[] columnNames = {"SELECT", "#", "Paciente", "Medicamento", "Diagnóstico", "Fecha"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Solo la columna de selección es editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);

        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) return SwingConstants.CENTER;
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

        JLabel title = new JLabel("Detalle Historias Médicas");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2");
        panel.add(title, "gapx 20");

        panel.add(createHeaderAction());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane);

        Repository<HistoriaMedica> respository = new HistoriaMedicaRepository();

        // Agregar datos de ejemplo
        for (HistoriaMedica historia : respository.getAll()) {
            tableModel.addRow(ModelHistoriaMedica.toTableRowCustom(historia, table.getRowCount() + 1));
        }

        return panel;
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
        JOptionPane.showMessageDialog(this, "Funcionalidad de Crear aún no implementada.", "Crear Historia Médica", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onEdit(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            JOptionPane.showMessageDialog(this, "Funcionalidad de Editar aún no implementada.", "Editar Historia Médica", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una historia médica para editar.", "Editar Historia Médica", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Historia Médica eliminada con éxito.", "Eliminar Historia Médica", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una historia médica para eliminar.", "Eliminar Historia Médica", JOptionPane.WARNING_MESSAGE);
        }
    }
}
