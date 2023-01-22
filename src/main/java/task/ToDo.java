package task;

import duke.DukeException;

/**
 * ToDo is a Task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor for "ToDo" type task.
     * @param description The task description.
     * @throws DukeException If there is no description indicated.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the formatted string representing this task to be saved to the local file.
     * @return The formatted string.
     */
    @Override
    public String getSavedFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Gets the string representation of this task to be displayed on the Ui.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
