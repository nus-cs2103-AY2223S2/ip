package task;

/**
 * Class to support Deadline tasks.
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() +
                " (by: " + by + ")";
    }
}
