package storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + status() + getTask() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String saveFormat() {
        return "[D]" + status() + getTask() + " | " + getDeadline() + "\n";
    }

    private String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        String strDeadline = formatter.format(this.deadline);
        return " (by: " + strDeadline + ")";
    }
}
