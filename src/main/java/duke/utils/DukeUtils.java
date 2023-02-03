package duke.utils;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.io.FileStorage;
import duke.task.TaskList;

import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Contains utility methods for the app.
 */
public abstract class DukeUtils {
    private static final Path SAVE_FILE_PATH = Path.of("./save-data/task-list.csv");

    /**
     * Validates the user's inputs, executes the appropriate command, and prints the response message.
     *
     * @param input The user's input.
     * @param printer For printing to the UI.
     * @param parser For determining the validity of the input and the command to execute.
     * @param tasks The user's task list.
     */
    public static void handleInput(String input, Consumer<String> printer, Parser parser, TaskList tasks) {
        assert printer != null;
        assert parser != null;

        String message;
        try {
            message = parser.getCommand(input).run(input, tasks);
        } catch (DukeException e) {
            message = e.getMessage();
        }

        printer.accept(message);
    }

    /**
     * Loads the previously saved tasks list from storage if it exists and returns it. Otherwise, creates a new file for
     * storing the task list and returns an empty task list.
     *
     * @return The task list loaded from storage if it exists. Otherwise, returns an empty task list.
     * @throws DukeException Indicates an error in loading from storage or creating storage.
     */
    public static TaskList loadTasks() throws DukeException {
        return new TaskList(new FileStorage(SAVE_FILE_PATH));
    }

    /**
     * Returns a greeting message.
     */
    public static String getGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return String.format("IT IS I!\n%s\nManager of tasks! Tracker of progress!\nHeedless user, what is it that you "
                + "seek?", logo);

    }
}
