package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String name, boolean isDone, String deadline) throws InvalidDateException {
        super(name, isDone);
        try {
            this.deadline = Task.parseDate(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getParsedString());
        }
    }

    public Deadline(String name, String deadline) throws InvalidDateException {
        this(name, false, deadline);
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String serialize() {
        String serialized = String.format("%s|%s", super.serialize(), this.deadline);
        return serialized;
    }

    @Override
    public String toString() {
        String s = String.format("%s (by: %s)", super.toString(), Task.formatDate(this.deadline));
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        Deadline task = (Deadline) obj;
        return super.equals(task) && this.deadline.equals(task.deadline);
    }
}
