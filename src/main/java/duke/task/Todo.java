package duke.task;

import duke.util.DukeException;

/**
 * Represents a Todo that is a Task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Translates the task into data format.
     * @return A String that represents the task in data format.
     */
    public String toData() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.getDescription());
    }

    /**
     * Checks if Todo details are valid.
     * @param todoDetails The details of Todo task that are entered by user.
     * @throws DukeException If no details are entered.
     */
    public static void checkTodoDetails(String todoDetails) throws DukeException {
        if (todoDetails.isBlank()) {
            throw new DukeException("You did not enter the required details for your task!");
        }
    }
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
