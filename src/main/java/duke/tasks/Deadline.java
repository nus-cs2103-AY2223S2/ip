package duke.tasks;

/**
 * Deadline Task class.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructor for Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = super.split(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toText() {
        int done = -1;
        if (this.isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "D" + " | " + done + " | " + this.description + " | " + this.by;
    }
}
