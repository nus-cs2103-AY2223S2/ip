package Duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline Task of the Chat bot.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Instantiates a Deadline Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     * @param by The date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Process the current Deadline object to be saved into a text file.
     *
     * @return a String representing the Deadline to be saved.
     */
    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("D | 1 | %s | %s\n", super.getDescription(), this.by);
        }
        return String.format("D | 0 | %s | %s\n", super.getDescription(), this.by);
    }

    /**
     * Process the current Deadline object to be displayed.
     *
     * @return a String representing the Deadline to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
