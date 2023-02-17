package duke;

import duke.exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class that represents a duke.Deadline with deadline time
 */
public class Deadline extends Task {
    /** Deadline of the task */
    private LocalDate deadline;

    /**
     * Initializes a Deadline object with the given values.
     *
     * @param name The name of the deadline
     * @param deadline The deadline time of the task
     * @return A deadline instance
     * @throws InvalidDateFormatException If the deadline passed as String is not of format "YYYY-MM-DD"
     */
    public Deadline(String name, String deadline) throws InvalidDateFormatException {
        super(name);
        try {
            this.deadline = Parser.getLocalDateObject(deadline);
        } catch (DateTimeParseException err) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Initializes a Deadline object with the given values.
     *
     * @param name The name of the deadline
     * @param deadline The deadline time of the task
     * @param isDone The status of the task, whether it is done or not
     * @return A deadline instance
     * @throws InvalidDateFormatException If the deadline passed as String is not of format "YYYY-MM-DD"
     */
    public Deadline(String name, String deadline, boolean isDone) throws InvalidDateFormatException{
        super(name, isDone);
        try {
            this.deadline = Parser.getLocalDateObject(deadline);
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
        return "D | " + super.toString() + " | " + Parser.getParsedDate(deadline);
    }
}
