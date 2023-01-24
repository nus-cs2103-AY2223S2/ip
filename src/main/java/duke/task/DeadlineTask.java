package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class representing a Deadline task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class DeadlineTask extends DukeTask{
    private final LocalDateTime deadline;

    /**
     * Given a task and a deadline, constructs a Deadline task.
     *
     * @param task The task to be completed.
     * @param deadline The task's deadline.
     */
    public DeadlineTask(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.deadline.format(df));
    }

    @Override
    public boolean isOnDate(LocalDate dtParsed) {
        return dtParsed.equals(this.deadline.toLocalDate());
    }

    @Override
    public String toDBSchema() {
        return String.format(
                "%s|%s|%s",
                "D",
                super.toDBSchema(),
                this.deadline.format(DateTimeFormatter.ofPattern("d/M/yy HHmm"))
        );
    }
}
