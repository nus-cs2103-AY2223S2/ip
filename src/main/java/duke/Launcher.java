package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Starts up Duke GUI.
     * @param args arguments to main
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

