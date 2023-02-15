package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected Object from;
    protected Object to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public void isDate() {
        LocalDate d1 = null;
        LocalDate d2 = null;
        boolean a = true;
        boolean b = true;
        try {
            d1 = LocalDate.parse((String) from);
            this.from = d1;
            from = ((LocalDate) from).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            //do nothing, this.from is not a date.
            a = false;
        }
        try {
            d2 = LocalDate.parse((String) to);
            this.to = d2;
            to = ((LocalDate) to).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            //do nothing, this.to is not a date.
            b = false;
        }
        if (a && b) {
            assert !d2.isBefore(d1) : "event cannot end before it starts";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
