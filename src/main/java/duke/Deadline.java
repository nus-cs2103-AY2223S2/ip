package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * A Deadline represents a type of Task that can be added by the user. It has a date that represents a deadline.
 */

public class Deadline extends Task {

    String deadlineString;
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");


    /**
     * Constructor for a Deadline
     * @param description Brief description of the deadline
     * @param deadlineDate The date that the deadline falls on
     * @throws InvalidDateFormatException If the date entered by the user is not in dd-Mmm-yyyy format
     */
    public Deadline(String description, String deadlineDate) throws InvalidDateFormatException {
        super(description);
        this.deadlineString = deadlineDate.strip();
        try {
            this.deadline = LocalDate.parse(this.deadlineString, formatter);
        } catch (DateTimeParseException e) {
           throw new InvalidDateFormatException();
        }
    }

    @Override
    public String toString() {
        String formattedDate = deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". duke.Deadline: "  + formattedDate);
    }
}
