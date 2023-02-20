package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the main class of the Duke chatbot application.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a Duke object and initializes the {@code tasks}
     * attribute with the user's list of tasks.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTaskList());
    }

    /**
     * Returns the response message if input command is executed successfully,
     * and the error message otherwise.
     *
     * @param input The command given by the user.
     * @return A string representation of response message or error message.
     */
    public String getResponse(String input) {
        String formattedInput = input.trim();
        if (formattedInput.equals("bye")) {
            return "bye";
        }

        try {
            return Parser.processCommand(formattedInput, storage, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
