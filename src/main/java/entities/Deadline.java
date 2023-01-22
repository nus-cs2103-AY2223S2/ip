package entities;

import enums.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        this.taskType = TaskType.DEADLINE;
    }

    public SerializableTask serialize() {
        String flags = by.toString();
        return new SerializableTask(TaskType.DEADLINE, isDone, description, flags);
    }

    @Override
    public boolean activeOn(LocalDate date) {
        return by.equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.getDayOfWeek().toString().toLowerCase() + ", " + by.format(formatter) + ")";
    }
}
