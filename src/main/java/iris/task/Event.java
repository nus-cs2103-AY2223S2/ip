package iris.task;

import iris.exception.DateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String name, String from, String to) throws DateTimeException {
        super(name);
        try {
            this.from = LocalDateTime.parse(from.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.to = LocalDateTime.parse(to.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String storageFormat() {
        return String.join("|", "E", super.storageFormat(),
                from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"))
                + ", to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}
