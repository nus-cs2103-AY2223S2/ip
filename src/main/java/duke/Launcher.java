package duke;

import gui.Main;
import javafx.application.Application;

/**
 * The Launcher class is responsible for starting the JavaFX application
 * by calling the launch method of the Application class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
