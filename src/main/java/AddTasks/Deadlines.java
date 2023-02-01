package AddTasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadlines extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline task.
     * @param description Name of the Deadline task.
     * @param by The date representation of the Deadline task, must be in dd/mm/yyyy format.
     */
    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of the Deadline task.
     * @return String representation of the Deadline task with the date in the format of MMM d yyyy.
     */
    @Override
    public String toString() {
        String convert = null;
        try {
            convert = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
        }
        return " " + "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (By: " + convert + ")";
    }
}