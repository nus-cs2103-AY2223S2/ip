package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Deadline is a type of task that additionally tracks the deadline */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates an instance of Deadline with the given task and deadline for completion
     * @param description Description of task to be done
     * @param deadline Deadline of task to be done
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    /**
     * Checks if deadline is overdue with respect to today's date
     */
    public boolean isOverdue() {
        LocalDate today = LocalDate.now();
        boolean isNotDone = !this.isDone;
        boolean isOverdue = today.isAfter(this.deadline);
        return isNotDone && isOverdue;
    }

    /** String representation of a Deadline task */
    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("%s | by: %s", this.description, this.deadline.format(f));
    }
}
