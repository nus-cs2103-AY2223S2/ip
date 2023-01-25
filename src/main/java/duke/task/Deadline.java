package duke.task;

import duke.datetime.DateTime;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    protected DateTime by;

    /**
     * Constructs a new deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    /**
     * Returns the command(s) required to recreate the deadline.
     *
     * @param id Identifier of the task, usually assigned by task list.
     * @return the command(s).
     */
    @Override
    public String getRecreateCommand(int id) {
        String result = "deadline " + description + " /by " + by;
        if (isDone) {
            result += "\nmark " + id;
        }
        return result;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
