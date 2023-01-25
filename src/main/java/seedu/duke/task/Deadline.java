/**
 * Project name: Duke
 * @author Tan Jun Da
 * Student Number: A0234893U
 */

package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to a
 * description and deadline by two strings.
 * e.g., <code>"read book","2019-12-12"</code>
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for the class Deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public Deadline(String description, String by){
        super(description.trim());
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Getter for the variable by of this object instance.
     *
     * @return The string of the variable by.
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Override the toString method to return the Deadline task.
     *
     * @return The String of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " )";
    }
}
