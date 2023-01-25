package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a Task with a due date.
 */

public class Deadline extends Task {
    protected String by;
    LocalDateTime dateTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for deadline.
     * @param description Description of Deadline.
     * @param by Due date of deadline.
     * @param isDone Completion status of Deadline.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        try {
            this.dateTime = LocalDateTime.parse(by.trim(),formatter);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter a date in e.g yyyy-mm-dd 23:59 format!");
        }
    }

    /**
     * A method that converts the Deadline into its String representation.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }
    /**
     * Converts the Deadline into the String format required to be saved in the Storage.
     * @return String formatted data of Deadline.
     */
    public String saveFormat(){
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

