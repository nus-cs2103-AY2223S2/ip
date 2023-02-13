package nemo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nemo.exception.NemoException;

/**
 * Represents Event tasks with from and to date.
 *
 * @author Lian Kok Hai
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event object.
     *
     * @param taskName Name of Event.
     * @param from Date of beginning of event.
     * @param to Date of end of event.
     * @throws NemoException Thrown when format of dates not recognized.
     */
    public Event(String taskName, String from, String to) throws NemoException {
        super(taskName);
        this.type = "E";
        try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            this.from = fromDate;
            this.to = toDate;
        } catch (DateTimeParseException e) {
            throw new NemoException("Format of date was not recognized");
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s | %s | %s", this.type, this.isDone, this.taskName, this.from, this.to);
    }
}
