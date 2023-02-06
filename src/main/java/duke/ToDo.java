package duke;

import duke.Task;

/**
 * This is a class for Todo task
 */
public class ToDo extends Task {

    protected String by;

    /**
     * This is a constructor for todo
     * @param description description of todo
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
        Task.actions += 1;
    }

    /**
     * String representation of todo
     * @return String representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    /**
     * String representation of todo in a saved file
     * @return String representation of todo in a saved file
     */
    @Override
    public String toSaveString() {
        return String.format("todo || %s || %s", super.toSaveString(), this.description);
    }
}

