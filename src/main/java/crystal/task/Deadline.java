package crystal.task;

import crystal.CrystalException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task.
 *
 */

public class Deadline extends Task {

    public LocalDateTime by;


    /**
     * Constructor for Deadline class.
     *
     * @param description Task description
     * @param by Task date and time
     * @throws CrystalException When user input is in the incorrect format
     */
    public Deadline(String description, String by) throws CrystalException {
        super(description);

        try {
            LocalDateTime d1 = LocalDateTime.parse(by.trim());
            this.by = d1;
        } catch (Exception e) {
            throw new CrystalException("Please change the input date format to yyyy-MMM-dThh:mm!");
        }

    }


    /**
     *  Returns the printed output shown in the list
     *
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";
    }

    /**
     *  Returns the String output when saving the list
     *
     */
    @Override
    public String toPrint() {

        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
