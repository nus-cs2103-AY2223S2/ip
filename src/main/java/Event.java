/**
 * Represents a event task.
 *
 * @author wz2k
 */
public class Event extends Task {
    /**
     * End of the event.
     */
    private String to;

    /**
     * Start of the event.
     */
    private String from;

    /**
     * Constructor for Event class.
     *
     * @param desc description of the event task.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * This method returns the task type, checkbox, description and timeline.
     *
     * @return event task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + this.from + "to: " + this.to + ")";
    }
}
