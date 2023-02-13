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
