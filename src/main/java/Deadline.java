import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter IN_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUT_FORMAT = DateTimeFormatter.
                                                        ofPattern("dd LLL, h:mma");

    public Deadline(String desc, String deadline) {
        super(desc);
        setDeadline(deadline);
    }

    private void setDeadline(String deadline) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(deadline, IN_FORMAT);
            this.deadline = dateTime;
        } catch (DateTimeParseException d) {
            System.out.println("Invalid date/time format for Deadline.");
        }
    }
    
    private String deadline() {
        return " (by: " + this.deadline.format(OUT_FORMAT) + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.deadline();
    }
}
