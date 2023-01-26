import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public Deadline(String name, String deadline) throws InvalidDateFormatException {
        super(name);
        try {
            this.deadline = Parser.getDate(deadline);
        } catch (DateTimeParseException err) {
            throw new InvalidDateFormatException();
        }
    }

    public Deadline(String name, String deadline, boolean isDone) throws InvalidDateFormatException {
        super(name, isDone);
        try {
            this.deadline = Parser.getDate(deadline);
        } catch (DateTimeParseException err) {
            throw new InvalidDateFormatException();
        }
    }


    /**
     * Returns the string representation of the Deadline task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + Parser.getString(deadline);
    }
}
