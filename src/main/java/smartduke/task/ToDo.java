package smartduke.task;

import smartduke.DukeException;

/**
 * ToDo is a Task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor for "ToDo" type task.
     * @param description The task description.
     * @throws DukeException If there is no description indicated.
     */
    public ToDo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String getSavedFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
