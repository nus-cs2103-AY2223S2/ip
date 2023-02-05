package dukes.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Subclass of Task that handles tasks with "deadline" label.
 */
public class DeadLine extends Task {
    LocalDate deadline;

    /**
     * Constructor of DeadLine class.
     *
     * @param taskName name (main content) of the task.
     * @param deadline deadline of the task.
     */
    public DeadLine(String taskName, LocalDate deadline) {
        super(taskName);
        this.tag = "D";
        this.deadline = deadline;
    }

    /**
     * Constructor of DeadLine class.
     *
     * @param taskName name (main content) of the task.
     * @param isDone specifies if the task has been done or not.
     * @param deadline deadline of the task.
     */
    public DeadLine(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName, isDone);
        this.tag = "D";
        this.deadline = deadline;
    }

    @Override
    public LocalDate getDeadLine() {
        return this.deadline;
    }

    @Override
    public void setDeadLine(LocalDate newDeadline) {
        this.deadline = newDeadline;
    }

    /**
     * Returns a string containing {tag "D" for deadline task} +
     * {if the task is completed} + the content of the task.
     *
     * @return a string showing its a deadline task,
     * if the task is completed, its content and deadline.
     */
    @Override
    public String toString() {
        String dateFormat = this.deadline.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        return "[D]" + super.toString() + " (by: " +
                dateFormat + ")";
    }
}
