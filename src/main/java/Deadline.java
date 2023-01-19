/**
 * The Deadline of tasks.
 * Inherits from the superclass Task.
 */
public class Deadline extends Task {
    private final String by;

    public Deadline(String nameOfTask, String by) {
        super(nameOfTask);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
