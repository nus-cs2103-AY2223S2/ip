package duke.task;

/**
 * Represents a To-Do Task.
 */
public class ToDoTask extends Task {

    private static final String EVENT_SYMBOL = "T";

    /**
     * Creates a To-Do Task.
     * @param description The description of the task.
     */
    public ToDoTask(String description) {
        super(description, EVENT_SYMBOL);
    }

}
