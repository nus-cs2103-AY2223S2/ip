package Duke.Commands.Tasks;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    protected static String DATE_FORMAT = "MMM dd yyyy";

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    private String getTaskClass() {
        return "D";
    }

    private String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskClass(), this.getStatusIcon(), this.description, this.getDeadline());
    }
}
