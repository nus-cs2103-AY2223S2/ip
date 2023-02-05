package duke.task;

import duke.datetime.DateTime;

/**
 * A task with a starting and ending date and time.
 */
public class Event extends Task {

    /** Starting date and time of event */
    protected DateTime from;

    /** Ending date and time of event */
    protected DateTime to;

    /**
     * Constructs a new event.
     *
     * @param description Description of the event.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    /**
     * Returns the command(s) required to recreate the event.
     *
     * @param id Identifier of the task, usually assigned by task list.
     * @return the command(s).
     */
    @Override
    public String getRecreateCommand(int id) {
        String result = "event " + description + " /from "
                + from.getRaw() + " /to " + to.getRaw();
        if (isDone) {
            result += "\nmark " + id;
        }
        return result;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns whether this event have the same description, start and end datetime as another event.
     *
     * @param other Other object.
     * @return true if other object is an event and has the same description, start and end datetime,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(super.equals(other) && (other instanceof Event))) {
            return false;
        }
        Event event = (Event) other;
        return from.equals(event.from) && to.equals(event.to);
    }
}
