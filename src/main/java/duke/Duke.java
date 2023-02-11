package duke;

import duke.gui.Ui;
import javafx.application.Application;

import duke.command.Command;
import duke.command.ListCommand;
import duke.gui.GUi;

/**
 * The main class for the Duke program.
 */
public class Duke {

    /**
     * Main method of Duke. Runs the program with default settings.
     * @param args Default main command-line args.
     */
    public static void main(String[] args) {
        Application.launch(GUi.class);
    }
}
