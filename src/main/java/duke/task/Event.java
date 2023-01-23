package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    public Event(String name, boolean isDone, String start, String end) throws InvalidDateException {
        super(name, isDone);
        try {
            this.start = Task.parseDate(start);
            this.end = Task.parseDate(end);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getParsedString());
        }
    }

    public Event(String name, String start, String end) throws InvalidDateException {
        this(name, false, start, end);
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String serialize() {
        String serialized = String.format("%s|%s|%s", super.serialize(), this.start, this.end);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("%s (from: %s to: %s)", super.toString(), Task.formatDate(this.start),
                Task.formatDate(this.end));
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        Event task = (Event) obj;
        return super.equals(task) && this.start.equals(task.start) && this.end.equals(task.end);
    }
}
