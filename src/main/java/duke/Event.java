package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    //this makes sure that from and to do not have to be dates.
    public void isDate() {
        boolean c = true;
        boolean d = true;
        LocalDate d1, d2;
        try {
            d1 = LocalDate.parse((String) from);
            this.from = d1;
            from = ((LocalDate) from).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            c = false;
        }
        try {
            d2 = LocalDate.parse((String) to);
            this.to = d2;
            to = ((LocalDate) to).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            d = false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
