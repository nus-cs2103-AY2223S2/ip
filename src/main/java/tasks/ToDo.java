package tasks;

/**
 * This class is used to represent a To Do task.
 */
public class ToDo extends Task {

    /**
     * Constructor for the To Do.
     *
     * @param description The description for To Do.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of To Do.
     * @return The description of To Do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
