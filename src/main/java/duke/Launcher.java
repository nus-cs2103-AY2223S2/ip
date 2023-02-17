package duke;

import javafx.application.Application;

/**
 * Entry point of the application.
 */
public class Launcher {

    /**
     * Starts the application. The command line arguments will determine which version of the
     * application will be started: CLI or GUI.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("cli")) {
            DukeCli cli = new DukeCli();
            cli.start();
        } else {
            Application.launch(DukeGui.class, args);
        }
    }
}
