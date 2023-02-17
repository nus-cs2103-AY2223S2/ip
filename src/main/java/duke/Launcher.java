package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * This is the main method that creates the Application object and launch the program.
     *
     * @param args Input given by users via GUI.
     */
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getCause());
        }
    }
}
