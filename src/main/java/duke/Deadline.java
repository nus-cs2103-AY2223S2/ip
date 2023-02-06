package duke;

/**
 * This is the class for Deadline action
 * @author yanlinglim
 */
public class Deadline extends Task {
    protected String by;

    /**
     * This is the constructor for Deadline
     * @param description description of deadline
     * @param by the date where the dateline is due
     */
    public Deadline(String description, String by) {
        super(description);
        by = by.replaceAll("by","");
        by = by.replaceAll("/","");
        this.by = by;
        this.description = description;
        Task.actions += 1;
    }

    /**
     * Returns a string representation of the object.
     * @return String string representation of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * Returns a string representation of the object to be placed in the list
     * @return String string representation of the object to be placed in the list
     */
    @Override
    public String toSaveString() {
        return String.format(" deadline ||%s || %s || %s", super.toSaveString(), this.description, this.by);
    }
}
