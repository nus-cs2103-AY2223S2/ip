import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.text.DateFormatter;

public class Deadline extends Task{
    private LocalDate by;
    private String output;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        try {
            this.by = LocalDate.parse(by);
            DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            this.output = this.by.format(newFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(("Invalid deadline format, please input as YYYY-MM-DD with a space after /by"));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.output  + ")";
    }
}
