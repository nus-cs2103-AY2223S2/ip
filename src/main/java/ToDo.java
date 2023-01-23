/**
 * The to-do class extending from the Task class
 */
public class ToDo extends Task {
    protected String type;

    /**
     * Default constructor
     *
     * @param description: a string describing the to-do task
     */
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Overriding
     *
     * @return the string representation of the to-do task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.type, super.toString());
    }
}
