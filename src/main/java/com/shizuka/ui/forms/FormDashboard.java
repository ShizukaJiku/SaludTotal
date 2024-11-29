package com.shizuka.ui.forms;

import com.shizuka.ui.system.Form;
import com.shizuka.ui.utils.SystemForm;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

@SystemForm(name = "Dashboard", description = "dashboard form display some details")
public class FormDashboard extends Form {

    @Override
    protected void init() {
        setLayout(new MigLayout("al center center"));
        JLabel text = new JLabel("Dashboard");
        add(text);
    }
}
