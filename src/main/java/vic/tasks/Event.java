package vic.tasks;


import java.util.Date;

import vic.utilities.Parser;

/**
 * Represents Event task. A <code>Event</code> class corresponds to
 * the event task
 */
public class Event extends ITask {

    private final Date from;
    private final Date to;

    /**
     * Constructor for Deadline
     *
     * @param description for task
     * @param from        date
     * @param to          date
     */
    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Deadline
     *
     * @param description for task
     * @param from        date
     * @param to          date
     * @param isDone      status
     */
    public Event(String description, Date from, Date to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "[E] " + "/from: " + from.getTime()
                + " /to: " + to.getTime() + " /content: " + super.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        ITask task = (ITask) obj;
        return task.getId() == this.getId();
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + Parser.DATE_OUTPUT_FORMAT.format(this.from) + " to: "
                + Parser.DATE_OUTPUT_FORMAT.format(this.to) + ")";
    }
}
