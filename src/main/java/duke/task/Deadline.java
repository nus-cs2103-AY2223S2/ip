package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates a new deadline task.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * A public constructor to initialize Deadline instance.
     *
     * @param task Task name.
     * @param deadline Task deadline.
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the description of Deadline.
     *
     * @return Deadline description.
     */
    @Override
    public String getDescription() {
        return "deadline " + super.getTaskName() + "/by " + this.deadline;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
