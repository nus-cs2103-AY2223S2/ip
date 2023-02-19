package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores tasks that have a duration.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event.
     * @param task The task description.
     * @param from The start time of the event.
     * @param to   The end time of the event.
     * @throws DateTimeParseException If the start time or end time is not in the
     *                                correct format.
     */
    public Event(String task, String from, String to) throws DateTimeParseException {
        super(task);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, parser);
        this.to = LocalDateTime.parse(to, parser);
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s",
                super.toFileString(),
                from.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            return super.equals(obj)
                    && from.equals(((Event) obj).from)
                    && to.equals(((Event) obj).to);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Event) {
            if (from.equals(((Event) o).from)) {
                if (to.equals(((Event) o).to)) {
                    return super.compareTo(o);
                }
                if (to.isBefore(((Event) o).to)) {
                    return -1;
                }
                return 1;
            }
            if (from.isBefore(((Event) o).from)) {
                return -1;
            }
            return 1;
        }
        return super.compareTo(o);
    }

}
