package duke.command;

import duke.task.Task;

import java.util.List;

/**
 * Represents a response resulting from a command.
 */
public class CommandResponse {
    private final String message;
    private final List<Task> tasks;

    /**
     * Create a CommandResponse object.
     *
     * @param message Message of the response.
     * @param tasks Updated task list.
     */
    CommandResponse(String message, List<Task> tasks) {
        this.message = message;
        this.tasks = tasks;
    }

    public String getMessage() {
        return message;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
