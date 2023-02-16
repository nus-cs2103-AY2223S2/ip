package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * starts launcher to workaround classpath issues.
     */
    public static void main(String[] args) {
        System.out.println("hehe");
        Application.launch(Main.class, args);
    }
}