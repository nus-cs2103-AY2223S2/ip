/**
 * Subclass of Task class used by Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Deadline extends Task {

    /**
     * A string representing the deadline of the Deadline instance.
     */
    protected String by;

    /**
     * Constructor for a Deadline instance.
     *
     * @param description String describing this Deadline.
     *
     * @param by deadline of this Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return The string representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getFileFormatString() {
        //to be split using "|"
        return "D" + "@" + this.isDone + "@" + this.description + "@" + this.by;
    }
}
