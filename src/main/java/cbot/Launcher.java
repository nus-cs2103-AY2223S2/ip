package cbot;

import cbot.gui.Main;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Cbot application.
     *
     * @param args Command-Line Arguments.
     * @see cbot.gui.Main#start(Stage)
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
