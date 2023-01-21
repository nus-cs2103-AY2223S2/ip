/**
 * Represents a Deadline task. A Deadline task has a description,
 * a 'by' timeframe, and can be marked as done.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean bool, String by) {
        super(description, bool);
        this.by = by;
    }

    /**
     * Marks a Deadline task as done.
     * @return Deadline task marked as done.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(description, true, by);
    }

    /**
     * Unmarks a Deadline task from being done.
     * @return Deadline task unmarked from being done.
     */
    @Override
    public Deadline unmarkAsDone() {
        return new Deadline(description, by);
    }

    /**
     * Returns data for storage purposes.
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() { return "D / " + getStatusNum() + " / " + getDesc() + " / " + by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
