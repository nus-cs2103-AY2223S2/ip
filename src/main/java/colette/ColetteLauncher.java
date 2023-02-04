package colette;

import colette.gui.Gui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class ColetteLauncher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
