package duke.Tasks;

import duke.DateTimeFormat;
import duke.Exceptions.DescriptionException;
import java.time.LocalDateTime;

/**
 *
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String strFrom;
    protected String strTo;

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

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.strFrom + "-" + this.strTo;

    }
}
