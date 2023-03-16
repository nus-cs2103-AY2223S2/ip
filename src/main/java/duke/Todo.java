package duke;

/**
 * To do class
 */
public class Todo extends Task {

    private static final String TASK_TYPE = "[T]";

    /**
     * Constructor for instantiating a to do object
     * @param description description of the to do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to do object
     * @return String string representation of the to do object, which includes task type, completion status and
     * description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\nPriority: " + getPriority();
    }

    /**
     * Returns the string representation of the to do object to be stored in the data.txt file
     * @return string representation with dividers of to do object to be stored in the data.txt file
     */
    @Override
    public String toStorageData() {
        String completionStatus = this.getStatusIcon();
        return TASK_TYPE + DIVIDER + completionStatus + DIVIDER + getPriority() + DIVIDER + super.description;
    }
}
