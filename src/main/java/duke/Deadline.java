package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


public class Deadline extends Task {

    private String deadlineString;
    private LocalDate deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    public Deadline(String description, String deadlineString) throws InvalidDateFormatException {
        super(description);
        this.deadlineString = deadlineString.strip();
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
