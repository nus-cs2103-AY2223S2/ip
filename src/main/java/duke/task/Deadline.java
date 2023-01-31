package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d LLL uuuu, hh:mm a");

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() +
                " (by: " + this.dueDate.format(formatter) + ")";
    }

    @Override
    public String getTaskState() {
        return "D | " + super.getTaskString() +
                " | " + this.dueDate;
    }
}
