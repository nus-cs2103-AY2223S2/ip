package duke;

public class ToDo extends Task {
    /**
     * constructs a toDo task object.
     * @param description description of this task.
     * @throws EmptyDescriptionException if description of task is missing.
     */
    public ToDo(String description) throws EmptyDescriptionException {
        super(description);
    }
    @Override
    public String getFileDescription() {
    return "T | " + super.getStatusIcon() + " | " + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
