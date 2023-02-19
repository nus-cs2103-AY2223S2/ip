package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Personal Assistant Chat Bot named Duke that helps the user keep track of various things.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Constructor for class Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        this.isExit = false;
    }

    /**
     * Runs the text-based UI version of the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                ui.show(command.execute(tasks, ui, storage));
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns Duke's response to the user input.
     *
     * @param input the user input.
     * @return the response from Duke.
     */
    public String getResponse(String input) {
        if (this.isExit) {
            return "Duke has been turned off. To restart, close and reopen the application again. Goodbye!";
        }
        try {
            Command command = Parser.parse(input);
            assert command != null : "There was an error in parsing user input into a command.";
            String dukeResponse = command.execute(tasks, ui, storage);
            this.isExit = command.isExit();
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialise the Duke object.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
