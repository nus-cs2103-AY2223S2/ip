package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a variation of Tasks that carries a task description and its deadline
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Deadline extends Task {
    private int isDone;
    private final LocalDate by;


    /**
     * Create a Deadline Task
     *
     * @param description decription of a task.
     * @param by deadline of the task
     * @param isDone 0 represents the task is not done and 1 represents the task is done.
     */
    public Deadline(String description, LocalDate by, Integer isDone) {
        super(description, isDone);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public void unMark() {
        isDone = 0;
    }

    @Override
    public void markAsDone() {
        isDone = 1;
    }

    public String getStatusIcon() {
        return (isDone == 1 ? "[X]" : "[ ]");
    }

    @Override
    public String dataFormat() {
        if (isDone == 1) {
            return "D/1/" + description + "/" + by;
        } else {
            return "D/0/" + description + "/" + by;
        }
    }
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + description
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
