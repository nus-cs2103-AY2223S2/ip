package rick.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class DeadlineTask extends RickTask {
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task, when given a task and a deadline.
     *
     * @param task The task to be completed.
     * @param deadline The task's deadline.
     */
    public DeadlineTask(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Generates and returns a human friendly interpretation of this task.
     *
     * @return The task interpretation.
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.deadline.format(df));
    }

    /**
     * Returns a boolean indicating if this task falls on the given date.
     *
     * @param dtParsed The given date.
     * @return The indicative boolean.
     */
    @Override
    public boolean isOnDate(LocalDate dtParsed) {
        return dtParsed.equals(this.deadline.toLocalDate());
    }

    /**
     * Formats this task into a format for storage in the Storage class, and
     * returns the schema format.
     *
     * @return The formatted task.
     */
    @Override
    public String toDbSchema() {
        return String.format(
                "%s|%s|%s",
                "D",
                super.toDbSchema(),
                this.deadline.format(DateTimeFormatter.ofPattern("d/M/yy HHmm"))
        );
    }
}
