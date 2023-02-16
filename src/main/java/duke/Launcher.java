package duke;

import duke.client.Main;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public static void exit() {
        Platform.exit();
    }
}