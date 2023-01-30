package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.ui.cli.Cli;

/**
 * Main class of the command-line interface version of the app.
 */
public class DukeCli extends Duke {
    private DukeCli() {
        parser = new Parser();
        ui = new Cli(System.out, System.in, this::handleInput, parser::isByeCommand);

        try {
            loadTasks();
        } catch (DukeException e) {
            ui.print(e.getMessage());
            return;
        }

        printGreeting();
        ui.start();
    }

    /**
     * Runs the app.
     */
    public static void run() {
        new DukeCli();
    }
}
