package duke.task;

/**
 * A Task subclass.
 */
public class Event extends Task {
    private String type = "[E]";

    /**
     * A constructor for Event.
     *
     * @param name name of the task.
     * @param isDone status of the task.
     */
    public Event(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * A constructor for Event.
     *
     * @param name name of the task.
     * @param name name of the task
     */
    public Event(String name) {
        super(name);
    }

    /**
     * Returns the current status of the Event.
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
     * Returns an update upon removal.
     *
     * @return a string upon removal of an Event.
     */
    @Override
    public String removeTask() {
        total--;
        return "Noted I've removed this task:\n " + this.status()
                + "\n" + "Now you have " + super.total + " tasks in the list";
    }

    /**
     * {@inheritDoc}
     *
     * @return string regarding addition of an Event.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task:\n " + this.status() + "\n"
                + "Now you have " + super.total + " tasks in the list";
    }
}
