/**
 * Deadline is a type of Task.
 * Deadlines have a 'by' field to indicate the period which the task must be completed by.
 * @author EL
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructor that takes in the name of the Task alongside some timing details.
     * By default, a Tasks is not completed.
     *
     * @param task_name The name of this Task
     * @param by A string representation of the end date/time of this Task
     */
    public Deadline(String task_name, String by) {
        super(task_name);
        this.by = by;
    }

    /**
     * Returns String representation of Deadline.
     * @return The name of this task and the deadline details.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
