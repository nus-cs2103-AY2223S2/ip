package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert false;
        System.out.println("reached");
        Application.launch(Main.class, args);
    }
}
