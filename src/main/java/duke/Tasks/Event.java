package duke.Tasks;

import duke.DateTimeFormat;
import duke.Exceptions.DescriptionException;
import java.time.LocalDateTime;

/**
 * Represents an event task
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String strFrom;
    protected String strTo;

    /**
     * Constructor for an event object based on the attributes that it has
     * @param description string representing the description
     * @param from when the event starts datetime in string
     * @param to when the event ends datetime in string
     * @throws DescriptionException If description is empty
     */
    public Event(String description, String from, String to) throws DescriptionException {
        super(description);
        this.strFrom = from;
        this.strTo = to;
        this.to = getLocalDateTime(this.strTo);
        this.from = getLocalDateTime(this.strFrom);
        if (this.to != null) {
            this.strTo = this.to.format(DateTimeFormat.defaultOutput.formatter);
        }
        if (this.from != null) {
            this.strFrom = this.from.format(DateTimeFormat.defaultOutput.formatter);
        }
    }

    /**
     * String representation of the object
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.strFrom + "-" + this.strTo;

    }
}
