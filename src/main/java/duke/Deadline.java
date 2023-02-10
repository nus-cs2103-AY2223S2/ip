package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    protected LocalTime time = null;

    /**
     * constructs a deadline task.
     *
     * @param description describe the deadline
     * @param by deadline of this task
     * @throws EmptyDescriptionException
     */
    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        String[] str = by.split(" ");
        this.date = LocalDate.parse(str[0], DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        if (str.length == 2) {
            this.time = LocalTime.parse(str[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    @Override
    public String getFileDescription() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        String timeString = time.format(DateTimeFormatter.ofPattern("HHmm"));
        return "D | " + super.getStatusIcon() + " | " + super.description + " | "  + dateString + " " + timeString;
    }

    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        String timeString = time.format(DateTimeFormatter.ofPattern("HHmm"));
        if (time != null) {
            return "[D]" + super.toString() + " (by: " + dateString + " " + timeString + ")";
        }
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }
}
