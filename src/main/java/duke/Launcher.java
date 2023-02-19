package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */


//modify launcher to initiate both GUI and CLI versions of Duke
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--gui")) {
            Application.launch(Main.class, args);
        } else {
            Duke.main(args);
        }
    }
}
