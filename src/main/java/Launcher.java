import javafx.application.Application;

import gui.DukeGui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method runs the GUI interface.
     *
     * @param args any arguments to the program, empty by default.
     */
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
