package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/**
 * Deadline is a task that inherits Tasks, and it represents a task that
 * has a certain deadline to complete which is indicated by a Date and Time
 */
public class Deadline extends Tasks {

    protected LocalDateTime by;

    /**
     * Returns a Deadline object that inherits the Tasks Class, it is a task
     * with a certain deadline and it is first set to be undone
     * @param description the details of the deadline
     * @param by the date and time the task should be completed
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString() method, it prints out the full description
     * of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
            + this.by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }

    /**
     * Saves the deadline into data/duke.txt
     */
    @Override
    public String log() {
        return "D" + super.log() + " | " + this.by + "\n";
    }
}
