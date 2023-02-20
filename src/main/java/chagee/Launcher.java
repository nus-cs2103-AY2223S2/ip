package chagee;

import javafx.application.Application;

import chagee.ui.Gui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
