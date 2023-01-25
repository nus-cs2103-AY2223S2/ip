import javax.swing.text.html.parser.Parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents a Deadline with deadline time
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Initialize an Event object with the given values.
     *
     * @param name The name of the deadline
     * @param deadline The deadline time of the task
     * @return A deadline instance
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the string representation of the Deadline task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}
