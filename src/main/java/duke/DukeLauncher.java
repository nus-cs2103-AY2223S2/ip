package duke;

import duke.gui.DukeGui;
import javafx.application.Application;

/**
 * This class acts as a launcher/starting point for the Duke application.
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
