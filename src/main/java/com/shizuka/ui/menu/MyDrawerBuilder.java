package com.shizuka.ui.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.shizuka.App;
import com.shizuka.ui.forms.*;
import com.shizuka.ui.system.AllForms;
import com.shizuka.ui.system.Form;
import com.shizuka.ui.system.FormManager;
import raven.extras.AvatarIcon;
import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.data.Item;
import raven.modal.drawer.data.MenuItem;
import raven.modal.drawer.menu.MenuOption;
import raven.modal.drawer.menu.MenuStyle;
import raven.modal.drawer.renderer.DrawerStraightDotLineStyle;
import raven.modal.drawer.simple.SimpleDrawerBuilder;
import raven.modal.drawer.simple.footer.LightDarkButtonFooter;
import raven.modal.drawer.simple.footer.SimpleFooterData;
import raven.modal.drawer.simple.header.SimpleHeaderData;
import raven.modal.option.Option;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private final int SHADOW_SIZE = 12;

    public MyDrawerBuilder() {
        super(createSimpleMenuOption());
        LightDarkButtonFooter lightDarkButtonFooter = (LightDarkButtonFooter) footer;
        lightDarkButtonFooter.addModeChangeListener(isDarkMode -> {
            // event for light dark mode changed
        });
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/app/drawer/image/profile.jpg"), 50, 50, 3.5f);
        icon.setType(AvatarIcon.Type.MASK_SQUIRCLE);
        icon.setBorder(2, 2);

        changeAvatarIconBorderColor(icon);

        UIManager.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("lookAndFeel")) {
                changeAvatarIconBorderColor(icon);
            }
        });

        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Shizuka Jiku")
                .setDescription("shizuka.jiku@gmail.com");
    }

    private void changeAvatarIconBorderColor(AvatarIcon icon) {
        icon.setBorderColor(new AvatarIcon.BorderColor(UIManager.getColor("Component.accentColor"), 0.7f));
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("App Salud Total")
                .setDescription("Version " + App.APP_VERSION);
    }

    public static MenuOption createSimpleMenuOption() {

        MenuOption simpleMenuOption = new MenuOption();

        MenuItem[] items = new MenuItem[] {
                // Categoría Principal: Dashboard
                new Item.Label("INICIO"),
                new Item("Dashboard", "dashboard.svg", FormDashboard.class),

                // Gestión de Pacientes y Doctores
                new Item.Label("GESTIÓN"),
                new Item("Pacientes", "patients.svg", FormPacientes.class),
                new Item("Doctores", "doctors.svg", FormDoctores.class),

                // Gestión de Citas y Historias Médicas
                new Item.Label("CONSULTAS"),
                new Item("Citas", "appointments.svg", FormCitas.class),
                new Item("Historias Médicas", "medical_history.svg", FormHistoriaMedica.class),
                new Item("Tratamientos", "treatments.svg"),

                // Farmacia
                new Item.Label("FARMACIA"),
                new Item("Inventario", "inventory.svg", FormInventario.class),

                // Otros
                new Item.Label("OTROS"),
                new Item("Configuración", "setting.svg", FormSetting.class),
                new Item("Acerca De", "about.svg"),
                new Item("Cerrar Sesión", "logout.svg")
        };


        simpleMenuOption.setMenuStyle(new MenuStyle() {

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
            }
        });

        simpleMenuOption.getMenuStyle().setDrawerLineStyleRenderer(new DrawerStraightDotLineStyle());
        simpleMenuOption.setMenuItemAutoSelectionMode(MenuOption.MenuItemAutoSelectionMode.SELECT_SUB_MENU_LEVEL);
        simpleMenuOption.addMenuEvent((action, index) -> {
            System.out.println("Drawer menu selected " + Arrays.toString(index));
            Class<?> itemClass = action.getItem().getItemClass();
            int i = index[0];

            if (i == 8) {
                action.consume();
                FormManager.showAbout();
                return;
            } else if (i == 9) {
                action.consume();
                FormManager.logout();
                return;
            }
            if (itemClass == null || !Form.class.isAssignableFrom(itemClass)) {
                action.consume();
                return;
            }
            Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
            FormManager.showForm(AllForms.getForm(formClass));
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("app/drawer/icon")
                .setIconScale(0.45f);

        return simpleMenuOption;
    }

    @Override
    public int getDrawerWidth() {
        return 270 + SHADOW_SIZE;
    }

    @Override
    public int getDrawerCompactWidth() {
        return 80 + SHADOW_SIZE;
    }

    @Override
    public int getOpenDrawerAt() {
        return 1000;
    }

    @Override
    public Option getOption() {
        Option option = super.getOption();
        option.setOpacity(0.3f);
        option.getBorderOption()
                .setShadowSize(new Insets(0, 0, 0, SHADOW_SIZE));
        return option;
    }

    @Override
    public boolean openDrawerAtScale() {
        return false;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
    }

    private static String getDrawerBackgroundStyle() {
        return "" +
                "[light]background:tint($Panel.background,20%);" +
                "[dark]background:tint($Panel.background,5%);";
    }
}
