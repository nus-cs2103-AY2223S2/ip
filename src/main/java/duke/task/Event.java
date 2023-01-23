package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/** A representation of a task with a start date and an end date. */
public class Event extends Task {

    /** Start date of the task. */
    private LocalDate start;
    /** End date of the task. */
    private LocalDate end;

    /**
     * Creates a Event object with a given name, completion status, start date, and
     * end date.
     * 
     * @param name   The name of the object to be created
     * @param isDone The completion status of the object to be created
     * @param start  A string representation of the task's start date
     * @param start  A string representation of the task's end date
     */
    public Event(String name, boolean isDone, String start, String end) throws InvalidDateException {
        super(name, isDone);
        try {
            this.start = Task.parseDate(start);
            this.end = Task.parseDate(end);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getParsedString());
        }
    }

    /**
     * Creates a Event object with a given name, start date, and end date.
     * 
     * @param name  The name of the object to be created
     * @param start A string representation of the task's start date
     * @param start A string representation of the task's end date
     */
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
