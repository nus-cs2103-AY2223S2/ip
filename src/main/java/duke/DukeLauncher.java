package duke;

import duke.gui.Gui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
