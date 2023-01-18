package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.TaskType;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String ...flags) {
        super(description);
        this.from = LocalDate.parse(flags[0]);
        this.to = LocalDate.parse(flags[1]);
    }

    public SerializableTask serialize() {
        String flags = from + " to " + to;
        return new SerializableTask(TaskType.EVENT, isDone, description, flags);
    }

    @Override
    public boolean activeOn(LocalDate date) {
        return date.isAfter(from) && date.isBefore(to) || date.isEqual(from) || date.equals(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " +  to.format(formatter) + ")";
    }
}
