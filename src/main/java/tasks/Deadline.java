package tasks;

import java.time.LocalDateTime;

import parser.Parser;

/**
 * This class is used to represent the Deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline.
     * @param description The description for the Deadline.
     * @param by The deadline to finish the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = Parser.parseDate(by);
    }

    /**
     * Returns the deadline of the task in LocalDateTime.
     * @return The deadline of the task in LocalDateTime.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Sets the deadline of the task to the user input.
     * @param userInput The new deadline.
     */
    public void setBy(LocalDateTime userInput) {
        this.by = userInput;
    }

    /**
     * Returns the string representation of the Deadline.
     * @return The string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.getMonth().toString().substring(0, 3)
                + " " + this.by.getDayOfMonth() + " " + this.by.getYear() + ", " + this.by.toLocalTime() + ")";
    }
}
