package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description.trim());
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    @Override
    public String toString() {
        String fromStr = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String toStr = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[E]" + super.toString() + "(from: " + fromStr + " to: " + toStr + ")";
    }

    @Override
    public String toBackup() {
        return "D | " + super.toBackup() + " | " + from + " | " + to + "\n";
    }
}