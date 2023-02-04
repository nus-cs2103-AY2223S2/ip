package Duke.Commands.Tasks;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    protected static String DATE_FORMAT = "MMM dd yyyy";

    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Generates a letter representing the type of task
     *
     * @return a letter representing the type of this task
     */
    public String getTaskClass() {
        return "D";
    }

    private String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Generates a String to store this task in a local text file
     *
     * @return A representative String that contains data about the current task
     */
    public String generateStorageText() {
        return String.format("%s~%s~%s~%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.deadline);
    }

    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskClass(), this.getStatusIcon(), this.description, this.getDeadline());
    }
}
