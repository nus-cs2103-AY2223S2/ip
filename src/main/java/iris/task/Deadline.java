package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.exception.DateTimeException;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, String by) throws DateTimeException {
        super(name);
        try {
            this.deadline = LocalDateTime.parse(by.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String storageFormat() {
        return String.join("|", "D", super.storageFormat(),
                deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a")) + ")";
    }
}
