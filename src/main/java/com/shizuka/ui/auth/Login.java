package com.shizuka.ui.auth;

import com.formdev.flatlaf.FlatClientProperties;
import com.shizuka.ui.system.Form;
import com.shizuka.ui.system.FormManager;
import net.miginfocom.swing.MigLayout;
import raven.modal.Toast;
import raven.modal.option.Location;
import raven.modal.toast.option.ToastLocation;
import raven.modal.toast.option.ToastOption;
import raven.modal.toast.option.ToastStyle;

import javax.swing.*;

public class Login extends Form {

    @Override
    protected void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chRememberMe = new JCheckBox("Recuerdame");
        cmdLogin = new JButton("Iniciar Sesion");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:shade($Panel.background,5%);" +
                "[dark]background:tint($Panel.background,5%);");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:shade($Panel.background,10%);" +
                "[dark]background:tint($Panel.background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su usuario");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su contraseña");

        JLabel lbTitle = new JLabel("Bienvenido a Salud Total!");
        JLabel description = new JLabel("Inicie sesion para acceder a su cuenta");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:$Label.disabledForeground;");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Usuario"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtPassword);
        panel.add(chRememberMe, "gapy 8");
        panel.add(cmdLogin, "gapy 10");
        add(panel);

        // event
        cmdLogin.addActionListener((e) -> {
            String userName = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if(userName.isEmpty() || password.isEmpty()) {
                showToast("Por favor ingrese sus credenciales", Toast.Type.WARNING, getSelectedOption());
                return;
            }

            if(login(userName, password)) {
                showToast("Bienvenido " + userName, Toast.Type.SUCCESS, getSelectedOption());
                FormManager.login();
            } else {
                showToast("Error, usuario o contraseña incorrectos",Toast.Type.ERROR, getSelectedOption());
            }
        });
    }

    private Boolean login(String username, String password) {
        return "user".equals(username) && "user".equals(password);
    }

    private void showToast(String text, Toast.Type type, ToastOption option) {
        Toast.closeAll();
        Toast.show(this, type, text, option);
    }

    private ToastOption getSelectedOption() {
        ToastOption option = Toast.createOption();
        Location h = Location.CENTER;
        Location v = Location.TOP;
        ToastStyle.BackgroundType backgroundType = ToastStyle.BackgroundType.GRADIENT;
        ToastStyle.BorderType borderType = ToastStyle.BorderType.LEADING_LINE;
        option.setAnimationEnabled(true)
                .setPauseDelayOnHover(true)
                .setAutoClose(true)
                .setCloseOnClick(true);
        option.setLayoutOption(ToastLocation.from(h, v).getLayout());
        option.getStyle().setBackgroundType(backgroundType)
                .setBorderType(borderType)
                .setShowLabel(false)
                .setIconSeparateLine(true)
                .setShowCloseButton(true)
                .setPromiseLabel("Saving...");
        return option;
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chRememberMe;
    private JButton cmdLogin;
}
