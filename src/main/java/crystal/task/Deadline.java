package crystal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import crystal.CrystalException;



/**
 * Represents the deadline task.
 *
 */
public class Deadline extends Task {

    private LocalDateTime by;


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
            LocalDateTime date = LocalDateTime.parse(by.trim());
            this.by = date;
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
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";

    }

    /**
     *  Returns the String output when saving the list
     *
     */
    @Override
    public String toPrint() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     *  Returns the printed output format on the text file.
     *
     */
    @Override
    public String toExport() {
        return "D" + super.toExport() + " | " + this.by;
    }
}
