package crystal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import crystal.CrystalException;



/**
 * Represents the Event task.
 *
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description Task description
     * @param from Task starting date and time
     * @param to Task end date and time
     *
     */
    public Event(String description, String from, String to) throws CrystalException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim());
            this.to = LocalDateTime.parse(to.trim());
        } catch (Exception e) {
            throw new CrystalException("Please change the input date format to yyyy-MMM-dThh:mm!");
        }
        assert this.to.isAfter(this.from) : "to: date should not be before from: date";
    }

    /**
     *  Returns the printed output shown in the list
     *
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";
    }

    /**
     *  Returns the String output when saving the list
     *
     */
    @Override
    public String toPrint() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     *  Returns the printed output format on the text file.
     *
     */
    @Override
    public String toExport() {
        return "E" + super.toExport() + " | " + this.from + " > " + this.to;
    }
}
