package duke.task;

/**
 * A Task subclass.
 */
public class ToDo extends Task {
    private String type = "[T]";

    /**
     * A constructor for ToDo.
     *
     * @param name name of the task.
     * @param isDone status of the task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * A constructor for ToDo.
     *
     * @param name name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     *
     * prepends type of ToDo.
     */
    @Override
    public String status() {
        return type + super.status();
    }

    /**
     * Returns the updates on removal.
     *
     * @return string regarding removal of a ToDo.
     */
    public String removeTask() {
        total--;
        return "Noted I've removed this task:\n "
                + this.status() + "\n" + "Now you have "
                + super.total + " tasks in the list";
    }

    /**
     * {@inheritDoc}
     *
     * @return string regarding addition of a ToDo.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n " + this.status() + "\n"
                + "Now you have " + super.total + " tasks in the list";
    }
}
