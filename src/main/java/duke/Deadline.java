package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that defines the Deadline type of tasks
 */
public class Deadline extends Task {

    /** The end date of this deadline object stored as a String*/
    protected String byDate;
    /** The end date of this deadline object stored as a LocalDate*/
    protected LocalDate endDate;

    /**
     * Constructor for objects of type Deadline
     *
     * @param userInput specifies the byDate and title of a Deadline object
     */
    public Deadline(boolean isCompleted, String userInput) {
        super(isCompleted, userInput.substring(9, userInput.indexOf("/by ") - 1), 'D');
        this.byDate = userInput.substring(userInput.indexOf("/by ") + 4);
        this.endDate = LocalDate.parse(byDate);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.byDate;
    }
}
