package duke.task;

/**
 * Task which needs to be done.
 */
public class Todo extends Task {

    /**
     * Constructor for the todo class.
     *
     * @param description Description of the todo class.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method that outputs the string of the formatted string of todo task.
     *
     * @return String Output of the formatted Todo event object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
