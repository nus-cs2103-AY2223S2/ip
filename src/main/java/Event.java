/**
 * Event is a Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String from, to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        if (from.length() == 0 || to.length() == 0) {
            throw new DukeException("☹ OOPS!!! You need to indicate a start and end date/time for this task...");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
