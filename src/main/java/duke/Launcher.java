package duke;

import javafx.application.Application;

/**
 * Entry point for the chatbot to circumvent JavaFX dependency issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
