package tasks;
import java.time.*;
import java.time.format.*;
/**
 * Deadlines are tasks which need to be done by a certain date/time
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDue;
    public Deadline(String description, LocalDateTime deadlineDue) {
        super(description);
        this.deadlineDue = deadlineDue;
    }

    @Override
    public String getDataString() {
        return "D | " + super.getDataString() + " | " + when;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDue.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }


}