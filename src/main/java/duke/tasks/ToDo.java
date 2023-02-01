package duke.tasks;


/**
 * ToDo is a task without a specified date/time.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param command Description of the ToDo.
     */
    public ToDo(String command) {
        super(command);
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of ToDo to be stored.
     *
     * @return The string storage representation of the ToDo.
     */
    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[T] | %d | %s",
                done,
                task);
    }
}
