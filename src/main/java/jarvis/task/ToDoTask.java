package jarvis.task;

import jarvis.exception.command.MissingParameterException;

/**
 * Task class representing a todo task.
 */
public class ToDoTask extends Task {
    public static final String ID = "T";

    /**
     * Constructor of a todo task, marked as undone.
     *
     * @param description Description of the task.
     * @throws MissingParameterException If description is null or blank.
     */
    public ToDoTask(String description) throws MissingParameterException {
        super(description);
    }

    /**
     * Constructor of a todo task.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is to be marked as done.
     * @throws MissingParameterException If description is null or blank.
     */
    public ToDoTask(String description, boolean isDone) throws MissingParameterException {
        super(description, isDone);
    }

    @Override
    public boolean satisfies(TaskFilter filter) {
        if (!super.satisfies(filter)) {
            return false;
        }
        return filter.hasNoDates();
    }

    @Override
    public String serialize() {
        String[] data = {ID, String.valueOf(this.isDone()), this.getDescription()};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", ID, super.toString());
    }
}
