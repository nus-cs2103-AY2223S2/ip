package panav.task;

/**
 * Class to represent a Deadline task. It has attributes for its deadline time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to initialise attributes.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
