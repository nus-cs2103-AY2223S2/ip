package duke.command;

import java.util.List;

/**
 * Represents a response resulting from a command.
 */
public class CommandResponse {
    private final String message;
    private final List<String> tasks;

    /**
     * Create a CommandResponse object.
     *
     * @param message Message of the response.
     * @param tasks Updated task list.
     */
    CommandResponse(String message, List<String> tasks) {
        this.message = message;
        this.tasks = tasks;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getTasks() {
        return tasks;
    }
}
