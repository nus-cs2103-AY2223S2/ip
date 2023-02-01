package duke.Tasks;

import duke.DateTimeFormat;
import duke.Exceptions.DescriptionException;
import java.time.LocalDateTime;

/**
 *
 */
public class Event extends Task {

    protected LocalDateTime by;
    protected LocalDateTime to;
    protected String strBy;
    protected String strTo;

    public Event(String description, String by, String to) throws DescriptionException {
        super(description);
        this.strBy = by;
        this.strTo = to;
        this.to = getLocalDateTime(this.strTo);
        this.by = getLocalDateTime(this.strBy);
        if (this.to != null) {
            this.strTo = this.to.format(DateTimeFormat.defaultOutput.formatter);
        }
        if (this.by != null) {
            this.strBy = this.by.format(DateTimeFormat.defaultOutput.formatter);
        }
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.strBy + "-" + this.strTo;

    }
}
