package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Class representing a Deadline task
 */
public class DeadlineTask extends Task {
    static final String DEADLINE_PREFIX_REPLACEMENT = "BY: ";
    private static final String INDICATOR = "[Deadline]";
    private static final String INVALID_DATE_EXCEPTION = "Incompatible date format given for Deadline";

    private static final String MONTH_DATE_YEAR_FORMAT = "MMM dd yyyy";
    private final LocalDate deadline;

    /**
     * Constructor for a Deadline task
     * @param name Task's name
     * @param deadline Deadline date for task
     * @throws DukeException If name is empty
     */
    DeadlineTask(String name, LocalDate deadline) throws DukeException {
        super(name);
        assert deadline != null;
        this.deadline = deadline;
    }

    /**
     * Creates Deadline task using String arguments
     *
     * @param name Task's name
     * @param deadline Deadline date for task in String
     * @return The created Deadline task
     * @throws DukeException If invalid date string is passed in
     */
    public static DeadlineTask createTask(String name, String deadline) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return new DeadlineTask(name, date);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_EXCEPTION);
        }
    }

    /**
     * Formats deadline date
     *
     * @param deadline To be formatted into String
     * @return Formatted deadline date string
     */
    private static String formattedDeadline(LocalDate deadline) {
        String formattedDate = deadline.format(DateTimeFormatter.ofPattern(MONTH_DATE_YEAR_FORMAT));
        return String.format(Task.EXTRAS_FORMAT_TEMPLATE, DEADLINE_PREFIX_REPLACEMENT + formattedDate);
    }

    @Override
    public String toString() {
        return INDICATOR + super.toString() + formattedDeadline(deadline);
    }
}
