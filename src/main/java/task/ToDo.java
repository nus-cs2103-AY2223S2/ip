package task;

/**
 * Encapsulates a Task without deadline or duration.
 */
public class ToDo extends Task {

    /**
     * Constructor.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Another constructor.
     *
     * @param description Description of the task.
     * @param marked Status to show if the task is done.
     */
    public ToDo(String description, boolean marked) {
        super(description, marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
