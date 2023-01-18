/**
 * Deadline is a type of Task.
 * @author EL
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a Task with the provided Task name.
     * By default, a Tasks is not completed.
     *
     * @param task_name
     */
    public Deadline(String task_name, String by) {
        super(task_name);
        this.by = by;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
