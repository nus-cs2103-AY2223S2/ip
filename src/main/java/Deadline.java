import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

    String deadlineString;
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    public Deadline(String description, String deadlineString) throws InvalidDateFormatException  {
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
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". Deadline: "  + formattedDate);
    }
}
