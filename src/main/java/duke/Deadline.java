package duke;

import java.time.LocalDateTime;

/**
 * Represents a Task with a due date
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs an instance of Deadline with specified description and due date.
     *
     * @param description Task description
     * @param by Due date of task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs an instance of Deadline with specified task status, description and due date.
     *
     * @param isDone Status of the task (completed or not completed)
     * @param description Task description
     * @param by Due date of task
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns the due date of the task.
     *
     * @return Due date of task
     */
    LocalDateTime getBy() {
        return by;
    }

    /**
     * Updates the due date of the task with specified dua date.
     *
     * @param by New due date of the task
     */
    void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (By: "
                + by.getDayOfMonth() + " " + by.getMonth() + " " + by.getYear() + " "
                + by.getHour() + by.getMinute() // bugalert: may not always be 4-digits
                + ")";
    }
}
