package duke;

/**
 * Encapsulates a Todo as a specific type of Task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo object.
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task in the list.
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task that is saved in a file.
     * @return The string representation of the Todo task that is stored in a text file.
     */
    @Override
    public String sendOutputToFile() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
