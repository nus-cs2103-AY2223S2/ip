import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

    String deadlineString;
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    public Deadline(String description, String deadlineString)  {
        super(description);
        this.deadlineString = deadlineString.strip();
        try {
            this.deadline = LocalDate.parse(this.deadlineString, formatter);
        } catch (DateTimeParseException e) {
            try {
                this.deadline = LocalDate.parse(this.deadlineString, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Please enter the date in this format: dd-MMM-YYYY OR dd/MMM/YYYY");
            }
        }
        



    }

    @Override
    public String toString() {
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". Deadline: "  + this.deadline);
    }
}
