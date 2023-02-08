package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime dueDate;


    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = interpretLocalDateTime(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "by: "
                + this.dueDate.format(formatter);
    }
}
