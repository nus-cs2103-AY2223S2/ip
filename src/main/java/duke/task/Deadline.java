package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, parser);
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + deadline.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }
}
