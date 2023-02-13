package duke;

/**
 * To do class
 */
public class Todo extends Task {

    /**
     * Constructor for instantiating a to do object
     * @param description description of the to do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to do object
     * @return String string representation of the to do object, which includes task type, completion status and
     * description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
