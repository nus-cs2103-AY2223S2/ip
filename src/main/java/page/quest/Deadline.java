package page.quest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import page.PageException;

/**
 * Represents a deadline, i.e. a quest with a complete-by date/time.
 */
public class Deadline extends Quest {
    /** Formatter for converting inputs in the HHmm dd/MM/yy format into LocalDateTime objects. */
    private static DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("[HHmm dd/MM/yy][dd MMM yyyy hh:mma]");
    /** Formatter for converting LocalDateTime objects into the dd MMM yyyy hh:mma format for outputting. */
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
    /** Complete-by time for the deadline. */
    private LocalDateTime by;

    /**
     * Constructs a new Deadline with the given description and complete-by date/time.
     *
     * @param description description of the deadline.
     * @param by complete-by date/time, in the HHmm dd/MM/yy format.
     * @throws PageException If date is not in the required format.
     */
    public Deadline(String description, String by) throws PageException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new PageException("Please format the date and time like this: 2359 31/12/99");
        }

    }

    /**
     * Returns the String representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " by: " + by.format(outputFormatter);
    }
}
