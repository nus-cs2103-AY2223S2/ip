package duke.task;

/**
 * {@code ToDo} class that encapsulates a todo task 
 */
public class ToDo extends Task {
    /**
     * Constructor method for {@code ToDo} task 
     * @param description description of todo task 
     */
    public ToDo(String description) {
        super(description);
        super.isDone = false;
    }

    /**
     * Provides string representation of {@code Todo} object 
     * @return String representation of todo task 
     */
    @Override
    public String toString() {
        String toDoString = "T" + " | " + super.toString();
        return toDoString;
    }
}
