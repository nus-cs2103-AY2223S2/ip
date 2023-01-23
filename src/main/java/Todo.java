/**
 * A type of Task with no timeframe.
 */
public class Todo extends Task {

    /**
     * Constructor for the todo class.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method of the Task class.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides the printData method of the Task class.
     *
     * @return String representation of the todo task in data form.
     */
    @Override
    public String printData() {
        return "T" + "/" + (isDone ? "1" : "0") + "/" + description;
    }
}
