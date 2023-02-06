package duke;

/**
 * class for tasks with only a description
 */
public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";

    /**
     * constructor for a new Todo instance
     * 
     * @param description description of task
     * @throws MissingDescriptionException missing description
     */
    public Todo(String description) throws MissingDescriptionException {
        super(description);
    }

    /**
     * returns string representation of todo
     * 
     * @return string with type, completion status, and name
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.toString();
    }

    @Override
    public String toStorageData() {
        String completionStatus = getStatusIcon();
        return TASK_TYPE + "//" + completionStatus + "//" + description;
    }
}
