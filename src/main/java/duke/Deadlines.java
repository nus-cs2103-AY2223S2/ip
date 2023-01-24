package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{
    protected String ddl;
    protected LocalDateTime by;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Deadlines(String description, String by) throws DukeInvalidArgumentException {
        super(description);
        try {
            this.ddl = by;
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("The format of date-time is invalid.");
        }
    }

    public String getBy() {
        return ddl;
    }

    public void getDeadline() {
        System.out.println("The deadline is on" + by.format(format));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(format) + ")";
    }
}
