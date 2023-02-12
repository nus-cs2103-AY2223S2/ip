package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println(1);
        Application.launch(Main.class, args);
    }
}