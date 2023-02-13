package duke;

/**
 * class for tasks with only a description
 */
public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";
    private String note;

    /**
     * constructor for a new Todo instance
     * 
     * @param description description of task
     * @throws MissingDescriptionException missing description
     */
    public Todo(String description, String note) throws MissingDescriptionException {
        super(description);
        if (note.equals("")) {
            this.note = "There are no notes!";
        } else {
            this.note = note;
        }
    }

    /**
     * returns string representation of todo
     * 
     * @return string with type, completion status, and name
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.toString() + "\nNote: " + note;
    }

    @Override
    public String toStorageData() {
        String completionStatus = getStatusIcon();
        return TASK_TYPE + DIVIDER + completionStatus + DIVIDER + description + DIVIDER + note;
    }
}
