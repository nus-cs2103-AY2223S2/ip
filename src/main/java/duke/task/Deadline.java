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
