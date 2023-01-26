package duke;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private LocalDate deadlineDate;
    private Date deadlineTime;
    public Deadline(String description, LocalDate deadlineDate, Date deadlineTime, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public String getDeadline() {
        String formattedTime = new SimpleDateFormat("h:mm").format(deadlineTime);
        return deadlineDate + " " + formattedTime;
    }

    @Override
    public String toString() {
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = new SimpleDateFormat("h:mm a").format(deadlineTime);
        String deadlineCombined = formattedDate + " " + formattedTime;
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + deadlineCombined + ")";
    }
}
