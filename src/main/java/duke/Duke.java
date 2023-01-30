package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.io.FileStorage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Path;

/**
 * Represents the app.
 */
public abstract class Duke {
    private static final Path SAVE_FILE_PATH = Path.of("./save-data/task-list.csv");

    /** Handles the UI of the app. */
    protected Ui ui;
    /** Parses the user's inputs. */
    protected Parser parser;

    private TaskList tasks;

    /**
     * Validates the user's inputs, executes the appropriate command, and returns the response message.
     *
     * @param input The user's input.
     * @return The response message.
     */
    protected String handleInput(String input) {
        String message;
        try {
            message = parser.getCommand(input).run(input, tasks);
        } catch (DukeException e) {
            message = e.getMessage();
        }

        return message;
    }

    /**
     * Loads the previously saved tasks list from storage if it exists. Otherwise, create a new file for storing the
     * task list.
     *
     * @throws DukeException Indicates an error in loading from storage or creating storage.
     */
    protected void loadTasks() throws DukeException {
        tasks = new TaskList(new FileStorage(SAVE_FILE_PATH));
    }

    /**
     * Prints a greeting message to the UI.
     */
    protected void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("IT IS I!\n%s\nManager of tasks! Tracker of progress!\nHeedless user, what is "
                + "it that you seek?", logo);
        ui.print(greeting);
    }
}
