package duke.task;

/**
 * This class creates an event with a from and to date.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor of the event class.
     *
     * @param description Event description.
     * @param from Date or time of the event.
     * @param to Date or time of the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Method that outputs in formatted event string.
     * @return String Output of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " " + "to: " + to + ")";
    }

}
