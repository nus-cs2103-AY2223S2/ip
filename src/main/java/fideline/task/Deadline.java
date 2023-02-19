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
            final String dateFormat = "MMM dd yyyy";
            timingString = timingLocalDate.format(
                    DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException e) {
            // Simply stores the given timing
            timingString = deadlineTiming;
        }
    }

    @Override
    public String toString() {
        final String stringFormat = "[D]%s (by: %s)";
        return String.format(stringFormat, super.toString(), timingString);
    }

    @Override
    public String getStorageString() {
        String stringFormat = "D|%s|%s";
        return String.format(stringFormat, super.getStorageString(), timingString);
    }

    @Override
    public boolean equals(Task task) {
        boolean isDeadline = task instanceof Deadline;
        if (!isDeadline) {
            return false;
        }
        boolean isSameTiming = timingString.equals(((Deadline) task).timingString);
        return isSameTiming && super.equals(task);
    }
}
