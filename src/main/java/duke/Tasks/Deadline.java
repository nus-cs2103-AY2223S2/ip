package duke.Tasks;

import duke.Exceptions.DescriptionException;
import duke.DateTimeFormat;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected String strBy;

    public Deadline(String description, String by) throws DescriptionException {
        super(description);
        this.strBy = by;
        this.by = getLocalDateTime(this.strBy);
        if (this.by != null) {
            this.strBy = this.by.format(DateTimeFormat.defaultOutput.formatter);
        }
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.strBy;
    }
}
