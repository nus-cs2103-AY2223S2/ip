import javafx.application.Application;

import gui.DukeGui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
