package duke.task;

public class ToDo extends Task {

    /**
     * Constructor for Todo task
     *
     * @param description Description for Todo task.
     */
    public ToDo(String description) {
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
}
