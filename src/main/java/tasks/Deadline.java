package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 * It contains the task description as well as the deadline in LocalDateTime format.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Returns a Deadline task with the given task description and deadline.
     *
     * @param taskDescription
     * @param deadline
     */
    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }


    @Override
    public String toStorageFormatString() {
        return "D|" + (isDone ? "1" : "0") +
                "|" + this.taskDescription +
                "|" + this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")" ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline d = (Deadline) o;

        return d.taskDescription.equals(this.taskDescription);
    }

}
