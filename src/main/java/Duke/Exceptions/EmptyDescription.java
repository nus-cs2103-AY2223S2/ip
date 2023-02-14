package Duke.Exceptions;

import Duke.task.Task;

/**
 * Exceptions when user inputs empty description for a task.
 */

public class EmptyDescription extends DukeException {
    private final String emptyDescription;

    /**
   * Constructor for Exception when description of
     * the command is empty.
   */
    public EmptyDescription(Task task) {
        this.emptyDescription = new StringBuilder()
            .append("OOPS!!! The description of a ")
            .append(task.toString())
            .append(" cannot be empty.").toString();
    }

    @Override
    public String toString() {
        return emptyDescription;
    }
}
