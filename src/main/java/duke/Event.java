package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for dates!");
        }

    }

    public Event(String description, String from, String to, String status) throws DukeException {
        super(description, status);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong format for dates!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + this.from + "|" + this.to;
    }

}
