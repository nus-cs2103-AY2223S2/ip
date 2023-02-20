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
     * Returns the status and name of the Task.
     *
     * @return a string to state the Task as done.
     */
    @Override
    public String setDone() {
        super.setDone();
        return ("NOM NOM NOM! I've marked this task as done:\n" + this.status());
    }

    /**
     * Returns the status and name of the uncompleted Task.
     *
     * @return a string to state Task is not done.
     */
    @Override
    public String setNotDone() {
        super.setNotDone();
        return ("NOM NOM NOM! I've marked this task as not done yet:\n" + this.status());
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
