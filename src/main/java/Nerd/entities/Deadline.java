package Nerd.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline Task of the Chat bot.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Instantiates a Deadline Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     * @param deadline    The date of the Deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline.trim());
    }

    public LocalDate getBy() {
        return this.deadline;
    }

    /**
     * Process the current Deadline object to be saved into a text file.
     *
     * @return a String representing the Deadline to be saved.
     */
    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("D | 1 | %s | %s\n", super.getDescription(), this.deadline);
        }
        return String.format("D | 0 | %s | %s\n", super.getDescription(), this.deadline);
    }

    /**
     * Process the current Deadline object to be displayed.
     *
     * @return A String representing the Deadline to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
