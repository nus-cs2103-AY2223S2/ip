import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime deadline;
    private DateTimeFormatter acceptingFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy HHmm");

    public Deadline(String task, String deadline) {
        super(task);
        try {
            LocalDateTime temp = LocalDateTime.parse(deadline, acceptingFormat);
            this.deadline = LocalDateTime.parse(temp.format(displayFormat), displayFormat);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(deadline, deadline, 0);
        }
    }

    @Override 
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline.format(displayFormat) + "Hrs" + ")";
    }
}
