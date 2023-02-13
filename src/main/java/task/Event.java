package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Event task
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM YY");

    private final LocalDate start;
    private final LocalDate end;

    /**
     * @param desc  Task description
     * @param done  Task completed info
     * @param start Task start date
     * @param end   Task end date
     */
    private Event(String desc, boolean done, LocalDate start, LocalDate end) {
        super(desc, done);
        this.start = start;
        this.end = end;
    }

    public Event(String desc, LocalDate start, LocalDate end) {
        this(desc, false, start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start.format(DATE_FORMAT),
                this.end.format(DATE_FORMAT));
    }

    @Override
    public boolean hasDate(LocalDate date) {
        return this.start.isEqual(date) || this.end.isEqual(date);
    }

    @Override
    public Event markDone() {
        return new Event(this.desc, true, this.start, this.end);
    }

    @Override
    public Event markNotDone() {
        return new Event(this.desc, false, this.start, this.end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<LocalDate> getDate() {
        return Optional.of(this.start);
    }
}
