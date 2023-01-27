package tasks;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) throws DukeException {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String toSaveFormat() {
        return String.format("D,%s,%s", this.name, this.getDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by " + this.getDeadline() + ")";
    }
}
