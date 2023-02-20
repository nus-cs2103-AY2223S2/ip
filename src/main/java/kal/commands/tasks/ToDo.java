package kal.commands.tasks;

/**
 * This class handles To-Dos and their associated operations.
 */
public class ToDo extends Task {
    private static final String IDENTIFIER = "T";

    /**
     * Constructs a ToDo object.
     *
     * @param description The description of the ToDo object.
     */
    public ToDo(String description) {
        super(description, false);
    }

    /**
     * Constructs a ToDo object.
     *
     * @param description The description of the ToDo object.
     * @param isDone The completion status of the ToDo Object.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates a letter representing the type of task.
     *
     * @return a letter representing the type of this task.
     */
    public String getTaskClass() {
        return ToDo.IDENTIFIER;
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskClass(), this.getStatusIcon(), this.description);
    }
}
