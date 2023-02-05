package james;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method of the James program.
     */
    public static void main(String[] args) {
        Application.launch(James.class, args);
    }
}
