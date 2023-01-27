package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date/time!");
        }
    }

    @Override
    public String toSaveString() {
        return "E$$$" + super.toSaveString() + "$$$"
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "$$$"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
