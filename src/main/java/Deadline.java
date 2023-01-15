/**
 * Deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for "Deadline" type task.
     * @param description The task description.
     * @param by The deadline of the task.
     * @throws DukeException If there is no description or deadline indicated.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.length() == 0) {
            throw new DukeException("☹ OOPS!!! You need to indicate a deadline for this task...");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
