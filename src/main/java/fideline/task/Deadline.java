package fideline.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Representation of a type of task that has a deadline.
 *
 * @author Fun Leon
 */
public class Deadline extends Task {

    /** String representation of the task's deadline */
    private String timingString;

    /** LocalDate representation of the task's deadline if available */
    private LocalDate timingLocalDate;

    /**
     * Constructs a task object with a deadline.
     *
     * @param description Title given to the task.
     * @param deadlineTiming Deadline of the task.
     */
    public Deadline(String description, String deadlineTiming) {
        super(description);
        // Checks if deadlineTiming provided fits the correct format.
        try {
            // Stores deadline as a DateTime.
            timingLocalDate = LocalDate.parse(deadlineTiming);
            // Changes the format of the deadline.
            timingString = timingLocalDate.format(
                    DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            // Simply stores the given timing
            timingString = deadlineTiming;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + timingString + ")";
    }
}
