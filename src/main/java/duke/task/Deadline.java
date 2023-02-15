package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that is used to handle a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Class constructor of Deadline.
     * @param input the title of the deadline task
     * @param deadline the due date and time of the deadline task
     */
    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns the string representation of the deadline task.
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the string representation of the deadline task in a format for saving a task into the local data file.
     * @return the string representation of the deadline task in a format for saving a task into the local data file
     */
    @Override
    public String toTxtString() {
        return "D" + super.toTxtString() + "|" + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
