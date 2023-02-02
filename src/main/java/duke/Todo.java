package duke;

/**
 * Class that can store a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo object.
     *
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the task that is parsable by the Duke Storage parser.
     *
     * @return A String object that is parsable by the Duke Storage parser.
     */
    @Override
    public String getFileRepresentation() {
        return "todo|" + super.getFileRepresentation();
    }

    /**
     * Returns a String representation of the task to be printed on the screen.
     *
     * @return String representation for a Deadline object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
