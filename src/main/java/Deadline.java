import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Deadline class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Deadline extends Task {

    protected LocalDateTime dateTime;
    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
