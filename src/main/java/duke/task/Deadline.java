package duke.task;

/**
 * Represents a deadline object with a deadline timing.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a deadline object.
     *
     * @param description title of the deadline.
     * @param by deadline timing.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public Deadline() {
        super();
    }

    /**
     * Represents the string written into todo_list.txt.
     *
     * @return A string written into the todo_list.txt.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
