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
     * Runs the command corresponding to the user's input and prints the response message.
     * <p>
     * If an error occurs due to the user's input, prints the error message instead.
     * </p>
     *
     * @param input The user's input.
     * @param tasks The user's task list.
     * @param parser Determines the validity of the input and the command to execute.
     * @param printer Prints to the UI.
     */
    public static void handleInput(String input, TaskList tasks, Parser parser, Consumer<String> printer) {
        assert parser != null;
        assert printer != null;

        String message;
        try {
            message = parser.getCommand(input).run(input, tasks);
        } catch (DukeException e) {
            message = e.getMessage();
        }

        printer.accept(message);
    }

    /**
     * Loads the previously saved task list from storage and returns it.
     * <p>
     * If a previously saved task list does not exist, creates an empty task list and a storage for it. Returns the new
     * task list.
     * </p>
     *
     * @return The task list loaded from storage if it exists. Otherwise, the new task list.
     * @throws DukeException Indicates an error in loading from storage or creating storage.
     */
    public static TaskList loadTasks() throws DukeException {
        return new TaskList(new FileStorage(SAVE_FILE_PATH));
    }

    /**
     * Returns a greeting message.
     *
     * @return A greeting message.
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
