package duke;

import duke.gui.DukeGui;
import javafx.application.Application;

/**
 * Launcher for the Duke GUI and subsequently the Duke program.
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
