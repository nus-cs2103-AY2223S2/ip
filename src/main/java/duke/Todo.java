package duke;

/**
 * class for tasks with only a description
 */
public class Todo extends Task {
    private static final String taskType = "[T]";

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
        return taskType + super.toString();
    }

    @Override
    public String toStorageData() {
        String completed = getStatusIcon();
        return taskType + "//" + completed + "//" + description;
    }
}
