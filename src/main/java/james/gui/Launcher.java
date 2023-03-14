package james.gui;

import javafx.application.Application;

/**
 * Launches the program to workaround classpath issues.
 */
public class Launcher {
    /**
     * Returns the main method of Launcher program.
     */
    public static void main(String[] args) {
        Application.launch(James.class, args);
    }
}
