package com.shizuka;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.shizuka.ui.menu.MyDrawerBuilder;
import com.shizuka.ui.system.FormManager;
import com.shizuka.ui.utils.DemoPreferences;
import raven.modal.Drawer;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static final String APP_VERSION = "1.0.0";
    private static final String APP_TITLE = "Salud Total";

    public App() {
        init();
    }

    private void init() {
        setIconImages(FlatSVGUtils.createWindowIconImages("/app/icons/app.svg"));
        setTitle(APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Drawer.installDrawer(this, new MyDrawerBuilder());
        FormManager.install(this);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        DemoPreferences.init();
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("app.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        DemoPreferences.setupLaf();
        EventQueue.invokeLater(() -> new App().setVisible(true));
    }
}