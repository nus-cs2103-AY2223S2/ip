package duke.task;

/**
 * Deadline
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String title, String by) {
        this(title, false, by);
    }

    public Deadline(String title, boolean isDone, String by) {
        super(title, isDone);
        this.by = by;
    }

    public String toCsv() {
        return "D," + super.toCsv() + "," + by + ",";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
