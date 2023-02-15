package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. The deadline is given by a date and time.
 */
public class Deadline extends Task {

    private LocalDateTime dateBy;

    public Deadline(String title, LocalDateTime dateBy, boolean isDone) {
        super(title, isDone);
        this.dateBy = dateBy;
    }

    /**
     * Returns formatted string representation of the Deadline.
     * This string includes the task completion status, type and name of task, and date and time of deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy, HH:mm");
        return String.format("[D][%s] %s (by: %s)", doneString, this.getTitle(), this.dateBy.format(dateFormat));
    }

    /**
     * Returns formatted string representation of the Deadline, for storage in memory
     * This string includes the task completion status, type and name of task, and date and time of deadline.
     *
     * @return String representation of the Deadline, for storage in memory.
     */
    public String convertToMemoryString() {
        String doneString = this.getDone() ? "1" : "0";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm a");
        return "D, " + doneString + ", " + this.getTitle() + ", " + this.dateBy.format(dateFormat);
    }

}
