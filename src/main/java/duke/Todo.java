package duke;

/**
 * Class representing a Todo.
 */
public class Todo extends Task {

    /**
     * Parameterized constructor to create a Todo
     * @param description description of the task to be created
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parameterized constructor to create a Todo
     * @param description description of the task to be created
     * @param isDone if the task has been completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the String representation of a Todo which can be displayed to the user
     * @return the String representation of a Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of a Todo which can be written to a file
     * @return the String to be written to a file
     */
    @Override
    public String toFile() {
        return "T " + super.toFile();
    }
}
