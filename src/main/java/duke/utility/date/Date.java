package duke.utility.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a <code>Date</code> object that holds a Local Date Object together
 * with parsing
 * operations between a String and Date.
 * 
 * @author Brian Quek
 */
public class Date implements Serializable {
    private static String format = "MMM dd yyyy";
    private LocalDate date;

    /**
     * Constructor for the Date object.
     */
    public Date(String dateStr) throws DateTimeParseException {
        this.date = LocalDate.parse(dateStr);
    }

    /**
     * Returns a string of the Date object based on the <code>format</format> field.
     * 
     * @return a String from the date object.
     */
    public String dateToString() {
        return this.date.format(DateTimeFormatter.ofPattern(format));
    }
}
