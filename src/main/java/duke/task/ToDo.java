package duke.task;

/**
 * {@code ToDo} class that encapsulates a todo task NOSONAR
 */
public class ToDo extends Task {
    /**
     * Constructor method for {@code ToDo} task NOSONAR
     * @param description description of todo task NOSONAR
     */
    public ToDo(String description) {
        super(description);
        super.isDone = false;
    }

    /**
     * Provides string representation of {@code Todo} object NOSONAR
     * @return String representation of todo task NOSONAR
     */
    @Override
    public String toString(){
        return "T" + " | " + super.toString();
    }
}
