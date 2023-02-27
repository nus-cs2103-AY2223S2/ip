package duke;

import duke.controllers.MainWindow;

/**
 * Ui collates methods to deal with display of text messages on the GUI.
 */
public class Ui {
    private MainWindow app;

    /**
     * Constructor for Ui to instantiate the GUI (app).
     */
    public Ui(MainWindow app) {
        this.app = app;
    }

    /**
     * Displays the user text input on the GUI.
     * @param input The user text input.
     */
    public void showUserInput(String input) {
        this.app.addUserDialog(input);
    }

    /**
     * Displays a greeting message on the GUI by Duke.
     */
    public void showWelcome() {
        String greeting = "Yo how can I help you?";
        this.app.addDukeDialog(greeting);
    }

    /**
     * Displays a success message on the GUI when a successful task is completed by Duke.
     * @param succMsg The success message.
     */
    public void showSuccess(String succMsg) {
        this.app.addDukeDialog(succMsg);
    }

    /**
     * Displays an error message on the GUI when there is a failure encountered by Duke.
     * @param errMsg The error message.
     */
    public void showError(String errMsg) {
        this.app.addDukeDialog(errMsg);
    }
}
