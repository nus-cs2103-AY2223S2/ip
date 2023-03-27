package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores tasks that have a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * @param task     The task description.
     * @param deadline The deadline of the task.
     * @throws DateTimeParseException If the deadline is not in the correct format.
     */
    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, parser);
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + deadline.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            return super.equals(obj) && deadline.equals(((Deadline) obj).deadline);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            if (deadline.equals(((Deadline) o).deadline)) {
                return super.compareTo(o);
            }
            if (deadline.isBefore(((Deadline) o).deadline)) {
                return -1;
            }
            return 1;
        }
        return super.compareTo(o);
    }
}
