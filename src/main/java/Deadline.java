import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm");

    public Deadline(String desc, LocalDateTime end) {
        super(desc);
        this.end = end;
    }

    /**
     * overriding the toString function to contain the type of task being created
     * @return string of the deadline being created
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.end.format(FORMATTER) + ")";
    }
}
