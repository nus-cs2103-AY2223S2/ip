package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadlines
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructor for Deadline
     * @param description the task
     * @param deadline the deadline
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Process Deadline to String to store in duke.txt
     * @return Processed String
     */
    public String toFile() {
        return "D|" + this.isDone + "|" + this.desc + "|" + this.deadline;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String parsedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + statusIcon + "] " + this.desc + "(By: " + parsedDeadline + ")";
    }
}
