package duke;

import duke.command.Command;
import duke.exception.CommandException;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Main class for Duke chatbot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Duke chatbot.
     *
     * @param filePath Specifies path to save and retrieve content.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main function
     * @param args input from the command line (ignored)
     */
    public static void main(String[] args) {
        new Duke(".\\tasks.txt").run();
    }

    /**
     * Method that abstracts the running of the program
     */
    public void run() {
        assert this.storage != null && this.tasks != null && this.ui != null;
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = this.ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.storage, this.ui);
                isExit = command.isExit();
            } catch (CommandException commandException) {
                ui.showCommandError(input, commandException);
            } catch (DukeException dukeException) {
                dukeException.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        if (this.tasks == null) {
            try {
                tasks = new TaskList(storage.load());
            } catch (DukeException | IOException exception) {
                exception.printStackTrace();
            }
        }
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage, ui);
        } catch (DukeException exception) {
            return exception.toString();
        }
    }
}
