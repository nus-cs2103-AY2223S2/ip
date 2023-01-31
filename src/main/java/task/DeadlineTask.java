package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for a Task with a deadline.
 */
public class DeadlineTask extends Task{
    private final LocalDate date;

    /**
     * Constructor for a deadline Task.
     *
     * @param name Title/name of task.
     * @param date Date which task should be done by.
     */
    public DeadlineTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for a deadline Task when loaded in from hard drive.
     *
     * @param name Title/name of task.
     * @param date Date which task should be done by.
     * @param isDone True if task has been marked as done, false otherwise.
     */
    public DeadlineTask(String name, LocalDate date, Boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
