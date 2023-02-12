package duke.task;

import duke.DukeException;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task
     *
     * @param description Description for Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task in the save file.
     * @return Representation of the task in the save file.
     */
    @Override
    public String toSavedString() {
        return "T" + "|" + (super.isDone ? "1" : "0")
                + "|" + super.description;
    }

    @Override
    public void setComponent(TaskComponent component, Object detail) throws DukeException {
        switch (component) {
        case DESCRIPTION:
            this.description = (String) detail;
            break;
        default:
            throw new DukeException("TODO task does not have this component: " + component);
        }
    }
}
