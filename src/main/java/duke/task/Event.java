package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String task, String from, String to) throws DateTimeParseException {
        super(task);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, parser);
        this.to = LocalDateTime.parse(to, parser);
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    public String toFileString() {
        return String.format("E | %s | %s | %s",
                super.toFileString(),
                from.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")));
    }

}
