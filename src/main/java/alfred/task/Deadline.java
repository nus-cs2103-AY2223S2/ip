package alfred.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task by the user that has a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object that represents a unique task given by the user.
     * @param description {@inheritDoc}
     * @param deadline Provides the deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return deadline.toLocalDate().isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String str = String.format("D | %d | %s | %s",
                isDone ? 1 : 0, this.description, this.deadline.format(formatter));
        return str + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return String.format("[D][%s] %s(by: %s)",
                this.isDone ? "X" : " ", this.description,
                this.deadline.format(formatter));
    }
}
