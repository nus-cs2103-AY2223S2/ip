package duke.tasks;


import duke.utilities.Parser;

import java.util.Date;
/**
 * Represents Event task. A <code>Event</code> class corresponds to
 * the event task
 */
public class Event extends ITask {

    private final Date from;
    private final Date to;

    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Event(String description, Date from, Date to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "[E] " + "/from: " + from.getTime() + " /to: " + to.getTime() + " /content: " + super.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.DATE_OUTPUT_FORMAT.format(this.from) + " to: "
                + Parser.DATE_OUTPUT_FORMAT.format(this.to) + ")";
    }
}
