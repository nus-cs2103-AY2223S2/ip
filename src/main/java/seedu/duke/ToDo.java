package seedu.duke;

/**
 * Class for tasks that are without dates.
 * it extends the Task class.
 * @param description description of the task.
 * @param type task type.
 */
public class ToDo extends Task{
    protected String type = "[ T ]";

    /**
     * Constructs an Event task object and initializes the needed parameters.
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string describing the task.
     *
     * @return description of task.
     */
    @Override
    public String toString() {
        return type + super.toString();
    }
}
