package duke.dukeExceptions;

public class TaskNotFoundException extends DukeException {
    /**
     * Exception used to indicate user input was invalid and/or task was not found in TaskList
     */

    public TaskNotFoundException() {
        super("Task not found...");
    }
}
