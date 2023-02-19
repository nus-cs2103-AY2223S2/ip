package jeno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class for Deadline task
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline task
     * @param name Name of deadline task
     * @param deadline Deadline time of task
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Converts deadline task to its task log format to be saved in task log file
     * @return String representing deadline task in task log format
     */
    @Override
    public String toLog() {
        return "D" + super.toLog() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + "\n";
    }

    /**
     * Converts deadline task to string format which is echoed to user
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + ")";
    }
}
