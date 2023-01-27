package storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime deadline;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.deadline = by;
        setType(TaskType.DEADLINE);
    }

    @Override
    public String toString() {
        return typeAndStatus() + getTask() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        String strDeadline = formatter.format(this.deadline);
        return typeAndStatus() + getTask() + " | " + strDeadline + "\n";
    }

    private String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        return formatter.format(this.deadline);
    }
}
