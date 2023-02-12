package duke.task;

/**
 * A Task subclass.
 * **/
public class Deadline extends Task {
    private String type = "[D]";

    /**
     * A constructor for Deadline.
     *
     * @param name name of the task.
     * @param isDone status of the task.
     */
    public Deadline(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * A constructor for Deadline.
     *
     * @param name name of the task.
     */
    public Deadline(String name) {
        super(name);
    }

    /**
     * Returns the current status of the Deadline.
     *
     * @return a string with the current status.
     */
    @Override
    public String status() {
        return type + super.status();
    }

    /**
     * Returns an update upon removal of a Deadline.
     *
     * @return a string upon removal of a Deadline.
     */
    @Override
    public String removeTask() {
        total--;
        return "Noted I've removed this task:\n "
                + this.status() + "\n" + "Now you have "
                + super.total + " tasks in the list";
    }

    /**
     * {@inheritDoc}
     *
     * @return string regarding addition of a Deadline.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n " + this.status() + "\n"
                + "Now you have " + super.total + " tasks in the list";
    }
}
