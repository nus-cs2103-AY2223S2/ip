import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    String deadlineString;
    LocalDate deadline;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
    DateTimeFormatter formatter3= DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy/MMM/dd");

    DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("MMM/dd/yyyy");





    public Deadline(String description, String deadlineString) throws IOException {
        super(description);
        this.deadlineString = deadlineString.strip();
        try {
            this.deadline = LocalDate.parse(this.deadlineString, formatter);
        } catch (DateTimeParseException e) {
            try {
                this.deadline = LocalDate.parse(this.deadlineString, formatter2);
            } catch (DateTimeParseException e2) {
                try {
                    this.deadline = LocalDate.parse(this.deadlineString, formatter3);
                } catch (DateTimeParseException e3) {
                    try {
                        this.deadline = LocalDate.parse(this.deadlineString, formatter4);
                    } catch (DateTimeParseException e4) {
                        try {
                            this.deadline = LocalDate.parse(this.deadlineString, formatter5);
                        } catch (DateTimeParseException e5) {
                            try {
                                this.deadline = LocalDate.parse(this.deadlineString, formatter6);
                            } catch (DateTimeParseException e6) {
                                throw new IOException();
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public String toString() {
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". Deadline: "  + this.deadline);
    }
}
